<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Games</title>
    	<%@ include file="../head.jsp"%>  
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<!-- Css -->
		<link href="${BOOTSTRAP_CSS}" rel="stylesheet" media="screen">
		<link href="${BOOTSTRAP_THEME_CSS}" rel="stylesheet" media="screen">
		<link href="${FONT_AWESOME_CSS}" rel="stylesheet" media="screen">
		
		<!-- Js -->
		<script type="text/javascript" src="${JQUERY}"></script>
		<script type="text/javascript" src="${BOOTSTRAP_JS}"></script>
	
		<script type='text/javascript' src='https://ssl-webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/jquery.min.js'></script>
		<script type="text/javascript">
		<!--
		var unityObjectUrl = "http://webplayer.unity3d.com/download_webplayer-3.x/3.0/uo/UnityObject2.js";
		if (document.location.protocol == 'https:')
			unityObjectUrl = unityObjectUrl.replace("http://", "https://ssl-");
		document.write('<script type="text\/javascript" src="' + unityObjectUrl + '"><\/script>');
		-->
		</script>
		<script type="text/javascript">
		<!--
			var config = {
				width: 960, 
				height: 600,
				params: { enableDebugging:"0" }
				
			};
			var u = new UnityObject2(config);

			jQuery(function() {

				var $missingScreen = jQuery("#unityPlayer").find(".missing");
				var $brokenScreen = jQuery("#unityPlayer").find(".broken");
				$missingScreen.hide();
				$brokenScreen.hide();
				
				u.observeProgress(function (progress) {
					switch(progress.pluginStatus) {
						case "broken":
							$brokenScreen.find("a").click(function (e) {
								e.stopPropagation();
								e.preventDefault();
								u.installPlugin();
								return false;
							});
							$brokenScreen.show();
						break;
						case "missing":
							$missingScreen.find("a").click(function (e) {
								e.stopPropagation();
								e.preventDefault();
								u.installPlugin();
								return false;
							});
							$missingScreen.show();
						break;
						case "installed":
							$missingScreen.remove();
						break;
						case "first":
						break;
					}
				});
				u.initPlugin(jQuery("#unityPlayer")[0], contextPath + "/resource/static/ROOT.unity3d");
			});
		-->
		</script>
		<style type="text/css">
		<!--
		body {
			font-family: Helvetica, Verdana, Arial, sans-serif;
			background-color: white;
			color: black;
			text-align: center;
		}
		a:link, a:visited {
			color: #000;
		}
		a:active, a:hover {
			color: #666;
		}
		p.header {
			font-size: small;
		}
		p.header span {
			font-weight: bold;
		}
		p.footer {
			font-size: x-small;
		}
		div.content {
			margin: auto;
			width: 960px;
		}
		div.broken,
		div.missing {
			margin: auto;
			position: relative;
			top: 50%;
			width: 193px;
		}
		div.broken a,
		div.missing a {
			height: 63px;
			position: relative;
			top: -31px;
		}
		div.broken img,
		div.missing img {
			border-width: 0px;
		}
		div.broken {
			display: none;
		}
		div#unityPlayer {
			cursor: default;
			height: 600px;
			width: 960px;
		}
		-->
		</style>
	</head>
	<body>
		<%@ include file="../nav_top.jsp" %>
		<p class="header"><span>Unity Web Player | </span>Test</p>
		<div class="content">
			<div id="unityPlayer">
				<div class="missing">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">
						<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />
					</a>
				</div>
				<div class="broken">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now! Restart your browser after install.">
						<img alt="Unity Web Player. Install now! Restart your browser after install." src="http://webplayer.unity3d.com/installation/getunityrestart.png" width="193" height="63" />
					</a>
				</div>
			</div>
		</div>
		<p class="footer">&laquo; created with <a href="http://unity3d.com/unity/" title="Go to unity3d.com">Unity</a> &raquo;</p>
	</body>
</html>
