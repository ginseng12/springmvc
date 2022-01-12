<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- foreach test-- </br>
<c:forEach begin="1" end="100" var="i" step="10">
${i}  </br>
</c:forEach> --%>

<table border="2" height="100" width="400">
	     <caption>用户信息</caption>
		<tr>
			<th>id</th>
			<th>用户名</th>
			<th>用户密码</th>
		</tr>
		
		 <c:forEach items="${userList}" var="user">
    <%--   ${user.uid}---${user.loginname}<br/> --%>
      
      <tr>
			<td>${user.uid}</td>
			<td>${user.loginname}</td>
			<td>${user.loginpass}</td>
		</tr>
 </c:forEach>
 
		
	</table>


 <%-- <c:forEach items="${userList}" var="user">
      ${user.uid}---${user.loginname}<br/>
 </c:forEach> --%>
<!-- 样式框架 web -->
</body>
</html>