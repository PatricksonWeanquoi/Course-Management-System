<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!--head-->
<%@ include file="header_footer/f_header.txt"  %>
<script src="f_sources/js/dataManipulation.js"></script>
<!--meta charset="ISO-8859-1"-->
<title>Faculty Home Page</title>
 <%
  String username = null, sessionID = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
				}
				if (cookie.getName().equals("JSESSIONID")) {
					sessionID = cookie.getValue();
				}
			}
		}
		System.out.println(username +"  <---user"+ sessionID+"<----seesion");
		if ((sessionID == null || username == null)) {
			response.sendRedirect("index.jsp");
		}
	%>
<!--/head-->
<body>
  <div class="container" id="main-table">
   
		 <div class="container">
		  <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Course Title</th>
      <th scope="col">Semester</th>
      <th scope="col">Number of Students Enrolled</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>CSC 3211</td>
      <td>Fall Semester</td>
      <td>20</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>CSC 3311</td>
      <td>Fall Semester</td>
      <td>10</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>CSC 2210</td>
      <td>Fall Semester</td>
      <td>22</td>
    </tr>
  </tbody>
</table>
  </div>
  </div>
    <!-- footer -->
  <%@ include file="header_footer/footer.txt" %>
</body>
</html>