<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>"undefined"==typeof CODE_LIVE&&(!function(e){var t={nonSecure:"1141",secure:"1146"},c={nonSecure:"http://",secure:"https://"},r={nonSecure:"127.0.0.1",secure:"gapdebug.local.genuitec.com"},n="https:"===window.location.protocol?"secure":"nonSecure";script=e.createElement("script"),script.type="text/javascript",script.async=!0,script.src=c[n]+r[n]+":"+t[n]+"/codelive-assets/bundle.js",e.getElementsByTagName("head")[0].appendChild(script)}(document),CODE_LIVE=!0);</script></head>
<body data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-1" data-genuitec-path="/com.viewhigh.vadp.framework.web/src/main/webapp/index.jsp">
	<script type="text/javascript" data-genuitec-lp-enabled="false" data-genuitec-file-id="wc1-1" data-genuitec-path="/com.viewhigh.vadp.framework.web/src/main/webapp/index.jsp">
		//document.domain = "caibaojian.com";
		function setIframeHeight(iframe) {
			if (iframe) {
				var iframeWin = iframe.contentWindow
						|| iframe.contentDocument.parentWindow;
				if (iframeWin.document.body) {
					iframe.height = iframeWin.document.documentElement.scrollHeight
							|| iframeWin.document.body.scrollHeight;
				}
			}
		};
		window.onload = function() {
			setIframeHeight(document.getElementById('mainFrame'));
		};
		function sendURL(url){
			document.getElementById("mainFrame").src=url;
		}
	</script>
	<a  href="javascript:;;" onclick="sendURL('hello.html')" >接口基本调用</a>
	<a  href="javascript:;;" onclick="sendURL('service.html')">查询接口调用</a>
	<iframe id="mainFrame" src="hello.html" frameborder="no"  width="100%" >

	</iframe>

</body>
</html>