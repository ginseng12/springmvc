<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传实例 </title>
</head>
<body>
<h1>文件上传实例 </h1>
<form method="post" action="<%=request.getContextPath() %>/upload/filesUpload" enctype="multipart/form-data">
    选择一个文件:
    <input type="file" name="myfiles" />
    <br/><br/>
<!--    第二个文件: <input type="file" name="myfiles" /><br/>
    第三个文件：
      <input type="file" name="myfiles" /><br/>
    第四个文件    <input type="file" name="myfiles" /><br/> -->
    <input type="submit" value="上传" />
</form>
</body>
</html>