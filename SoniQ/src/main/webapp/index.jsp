<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<c:set var="title" value="index" scope="request" />
<jsp:include page="partials/heading.jsp" />

  <body>
  <script src="https://cdn.auth0.com/w2/auth0-widget-5.js"></script>
  	   <jsp:include page="partials/auth0.jsp" />
  
   
  
    	<jsp:include page="partials/header.jsp" />
    
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
