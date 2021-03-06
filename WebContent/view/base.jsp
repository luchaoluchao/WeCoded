<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="aa" uri="http://ajaxanywhere.sourceforge.net/" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jn" uri="/jn" %>

<c:set var="CONTEXT_PATH" value="${pageContext.request.contextPath}" />
<c:set var="RESOURCE" value="${CONTEXT_PATH}/resource" />

<c:set var="BOOTSTRAP_CSS" value="${RESOURCE}/framework/bootstrap/css/bootstrap.min.css" />
<c:set var="BOOTSTRAP_THEME_CSS" value="${RESOURCE}/framework/bootstrap/css/bootstrap-theme.min.css" />
<c:set var="BOOTSTRAP_JS" value="${RESOURCE}/framework/bootstrap/js/bootstrap.min.js" />

<c:set var="FONT_AWESOME_CSS" value="${RESOURCE}/framework/bootstrap/css/font-awesome.min.css" />

<c:set var="BOOTSTRAP_DATETIMEPICKER_CSS" value="${RESOURCE}/framework/bootstrap/css/datetimepicker.css" />
<c:set var="BOOTSTRAP_DATETIMEPICKER_JS" value="${RESOURCE}/framework/bootstrap/js/bootstrap-datetimepicker.min.js" />

<c:set var="BOOTSTRAP_SWITCH_CSS" value="${RESOURCE}/framework/bootstrap/css/bootstrap-switch.css" />
<c:set var="BOOTSTRAP_SWITCH_JS" value="${RESOURCE}/framework/bootstrap/js/bootstrap-switch.min.js" />

<c:set var="BOOTSTRAP_BUTTONS_CSS" value="${RESOURCE}/framework/bootstrap-buttons/css/buttons.css" />
<c:set var="BOOTSTRAP_BUTTONS_JS" value="${RESOURCE}/framework/bootstrap-buttons/js/buttons.js" />

<c:set var="THREE" value="${RESOURCE}/framework/three/three.min.js" />
<c:set var="JQUERY" value="${RESOURCE}/framework/jquery/jquery-2.0.3.min.js" />
<c:set var="AJAXANYWHERE" value="${RESOURCE}/framework/other/aa.js" />
<c:set var="MATH_UUID" value="${RESOURCE}/framework/other/Math.uuid.js" />
<c:set var="CHART_JS" value="${RESOURCE}/framework/chart/Chart.min.js" />
<c:set var="JN_UTIL" value="${RESOURCE}/js/util/JnUtil.js" />

<c:set var="JQUERY_EASY_DRAG" value="${RESOURCE}/framework/jquery/plugin/jquery.easydrag.handler.beta2.js" />
<c:set var="JQUERY_JCROP_JS" value="${RESOURCE}/framework/jcrop/js/jquery.Jcrop.min.js" />
<c:set var="JQUERY_JCROP_CSS" value="${RESOURCE}/framework/jcrop/css/jquery.Jcrop.min.css" />

<c:set var="JQUERY_GRIDLY_JS" value="${RESOURCE}/framework/gridly/jquery.gridly.js" />
<c:set var="JQUERY_GRIDLY_CSS" value="${RESOURCE}/framework/gridly/jquery.gridly.css" />

<c:set var="JQUERY_MD5" value="${RESOURCE}/framework/jquery/plugin/jquery.md5.js" />
<c:set var="JQUERY_FORM" value="${RESOURCE}/framework/jquery/plugin/jquery.form.js" />
<c:set var="JQUERY_JSON" value="${RESOURCE}/framework/jquery/plugin/jquery.json-2.4.js" />
<c:set var="JQUERY_COOKIE" value="${RESOURCE}/framework/jquery/plugin/jquery.cookie.js" />
<c:set var="JQUERY_UPLOAD" value="${RESOURCE}/framework/jquery/plugin/jquery.upload.js" />
