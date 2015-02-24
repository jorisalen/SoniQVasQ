<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/heading.jsp" />
<body>

	<jsp:include page="partials/header.jsp" />
	<div class='container' style="margin-top:100px;">
		    
		Test page
		
		
		<p>email: ${email}</p>
	  
	  <a href="Controller?action=home" class="btn btn-primary btn-lg"> Home </a>
	</div>
	

 <jsp:include page="partials/footer.jsp" />

</body>
</html>