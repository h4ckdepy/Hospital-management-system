<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- 
文件上传
前端页面：1.post请求   地址栏2M：get
		2.encytype 属性设置为 multipart-data
		3.文件类型为file类型
后端要求：
		1.文件需要的jar包，commons-fileupload\commod-io
		2.sevlet上标注@MultipartConfig
		

 -->
 <form action="${pageContext.request.contextPath }/Test" method="post" enctype="multipart/form-data"></form>
 	<input type = "hidden" method="add" >
 	<input type="file" name="picture" >
 	<input type="submit" value="提交">
 
 
</body>
</html>