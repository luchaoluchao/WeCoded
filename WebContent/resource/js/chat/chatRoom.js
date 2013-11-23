Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var ws = null;
var ctrl = false;
$(document).ready(function() {
    ws_connect();
    bindEvent();
    resizeOutput();
    onFocus();
});
$(window).resize(function(){
    resizeOutput();
});
$(window).focus(function(){
    onFocus();
});
function onFocus(){
    $("#input").focus();
}
function resizeOutput(){
    $(".output").height($(window).height() - 255);
}
function bindEvent() {
    $("#send").click(function() {
        sendInput();
    });
    $("#input").keydown(function(e){
        if(e.keyCode == 17){
            ctrl = true;
        }else if(ctrl && e.keyCode == 13){
            sendInput();
        }
    });
    $("#input").keyup(function(e){
        if(e.keyCode == 17){
            ctrl = false;
        }
    });
    $("#clearMsg").click(function(){
        $("#output").empty();
    });
    $("#sendPic").click(function(){
        $("#fileChooser").click();
    });
    
    var fileReader = new FileReader();
    fileReader.onload = function(progressEvent){
        var dataURL = progressEvent.target.result;
        
        var params = { image : dataURL };
        var message = buildMessage(params);
        
        ws_send(message);
        appendMessage(message);
    };
    fileReader.onerror = function(){
        alert("Load file error.");
    };
    $("#fileChooser").change(function(){
        var files = $(this).get(0).files;
        var file = files[0];
        fileReader.readAsDataURL(file);
        $(this).val("");
    });
}
function appendMessage(message){
    var userid = message.userid;
    var username = message.username;
    var date = new Date(message.date);
    date = date.format("yyyy-MM-dd HH:mm:ss");
    
    var id = Math.uuid();
    var content= null;
    if(message.text != undefined){
        content = J.encodeHTML(message.text).replace(/\n/gi, "<br/>");
    }else if(message.image != undefined){
        content = "<img src='" + message.image + "' class='image' />";
    }
    
	var template = $("#template").clone().attr("id", id);
	template.children("img").attr("src", contextPath + "/getImage/1/" + userid).attr("title", username);
	template.find("div.name").html(username);
	template.find("div.date").html(date);
	template.find("div.content").html(content);
	
    template.appendTo($("#output")).slideDown("fast");
    checkVideo(content, template);
    scrollMessage();
    
    var chatboxs = $("#output").children(".chatbox");
    if(chatboxs.length > 24){
        chatboxs.first().slideUp("fast", function(){
            $(this).remove();
        });
    }
}
function scrollMessage(){
    $("#output").parent().animate({scrollTop : $("#output").height()});
}
function checkVideo(message, chatbox){
    var url = null;
    if(message.indexOf("http") != -1){
        url = J.substring(message, "http", " ");
    } else if(message.indexOf("www") != -1){
        url = J.substring(message, "www", " ");
    }
    if(url && url.indexOf("youku.com") != -1){
        getYoukuInfo(url, chatbox);
    }else if(url && url.indexOf("tudou.com") != -1){
        getTudouInfo(url, chatbox);
    }
}
//http%3A%2F%2Fv.youku.com%2Fv_show%2Fid_XNjM4NjczMzI4_ev_3.html
function getYoukuInfo(url, chatbox){
    var api = "https://openapi.youku.com/v2/videos/show_basic.json?client_id=683e07f6f96dd3a0&video_url=";
    api += encodeURIComponent(url);
    $.getJSON(api, function(data, textStatus, jqXHR) {
        if (!data.error) {
            var target = chatbox.find("div.content");
            target.append("<br>").append($("#youku").clone().attr("src", data.player).show());
            target.append("<br>视频标题：").append(data.title);
            target.append("<br>视频描述：").append(data.description);
        }else{
            console.log(data.error);
        }
    });
}
//http://www.tudou.com/v/Nxm2zaSwqRc/&bid=05&rpid=324313167&resourceId=324313167_05_05_99/v.swf
function getTudouInfo(url, chatbox){
    var api = "http://api.tudou.com/v6/video/info?app_key=6206acbc39bf0491&itemCodes=";
    if(url.substring(url.length-1, url.length) == "/"){
        url = url.substring(0, url.lentgh - 1);
    }
    var videoId = url.substring(url.lastIndexOf("/")+1, url.length);
    if(videoId.indexOf(".") != -1){
        videoId = videoId.substring(0, videoId.indexOf("."));
    }
    api += videoId;
    $.getJSON(api, function(data, textStatus, jqXHR) {
        if (data.result) {
            var target = chatbox.find("div.content");
            target.append("<br>").append($("#tudou").clone().attr("src", data.result.outerPlayerUrl).show());
            target.append("<br>视频标题：").append(data.result.title);
            target.append("<br>视频标签：").append(data.result.tags);
        }
    });
}
function buildMessage(params){
    var message = {
        userid : $("#userid").val(),
        username : $("#username").val(),
        date : new Date()
    };
    if(params){
        $.extend(message, params);
    }
    return message;
}
function sendInput() {
    var text = $("#input").val();
    if ($.trim(text).length > 0) {
    	var userid = $("#userid").val();
    	var username = $("#username").val();
    	var message = buildMessage({ text : text }); 
    	$("#input").val("").focus();
        ws_send(message);
        appendMessage(message);
    }
}
function receiveMessage(message){
    appendMessage(JSON.parse(message));
    $("#alertAudio").get(0).play();
}
function ws_connect() {
    var url = J.getIndexUrl(contextPath, "ws") + "/wsChatRoom?id=" + $("#userid").val();
    ws = new WebSocket(url);
    ws.onopen = function() {
        console.log("open");
    }
    ws.onmessage = function(e) {
        receiveMessage(e.data);
    }
    ws.onclose = function(e) {
        console.log("closed");
    }
    ws.onerror = function(e) {
        console.log("error");
    }
}
function ws_send(msg) {
    if(typeof msg == "object"){
        msg = JSON.stringify(msg);
    }
    ws.send(msg);
}
function ws_close() {
    if (ws != null) {
        ws.close();
        ws = null;
    }
}
window.onbeforeunload = function() {
    if (ws) {
        ws_close();
    }
};