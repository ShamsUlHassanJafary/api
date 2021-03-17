<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form action="createCustomer" method="post">

		<h1>Hey Dear Fill CustomerForm</h1>
		Customer Name <br> <input type="text" name="name" value="" required><br><br> 
		Customer SimNumber <br> <input type="text" name="rollnum" value="" required><br><br>
		Customer Date Of Birth <input type="date" id="birthday" name="birthday" required>  <br /> <br />
		<input type="submit" value="Create" />
		<p>${message}</p>
	</form>



</body>
</html>