<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<c:set var="title" value="login" scope="request" />

	   <jsp:include page="partials/heading.jsp" />
	   <jsp:include page="partials/auth0.jsp" />

	
	
  <body>
  
    

    	<jsp:include page="partials/header.jsp" />
        <p><c:out value="${var1}" /></p>
    
    <div class='container'>
	<p class="logoMain">SoniQ</p>
    <div class='row'>
        <form  method="post" action="Controller?action=search">
        <div class='input-append' style="margin-top:30%; margin-left:10%">
            <input name="query" class="form-control" id="main_search" type="text" >
        </div>
       
        </form>
    </div>
</div>
    
    	<jsp:include page="partials/footer.jsp" />
    
  </body>
</html>
