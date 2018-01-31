<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
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