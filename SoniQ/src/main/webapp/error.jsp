<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<jsp:include page="partials/heading.jsp" />
<body>

	<jsp:include page="partials/header.jsp" />
	<div class='container' style="margin-top:100px;">
		    
		<p> oh noes, a ${status} happened</p>
		<p> exception type: ${name} </p>
		<p> exception message:  ${message} </p>
	  
	  <iframe width="100%" height="166" scrolling="no" frameborder="no" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/36955495&amp;color=ff5500&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=false&amp;show_reposts=false"></iframe>
	  
	  <a href="Controller?action=home" class="btn btn-primary btn-lg"> Home </a>
	</div>

 <jsp:include page="partials/footer.jsp" />

</body>
</html>