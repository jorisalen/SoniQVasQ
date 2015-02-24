<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<c:set var="title" value="comment" scope="request" />

<jsp:include page="partials/heading.jsp" />

<body>
	
	<jsp:include page="partials/header.jsp" />
	
	<p>comments </p>
	<div id="contentSearch">
	<div class=" container">

			
<div class="container comments">
    <div class="col-lg-8 col-sm-6">
    <div class="well">
        <h4>Comments</h4>
    <div class="input-group">
    <form method="post" action="Controller?action=addComment&id=${recordId}" >
        <input type="text" name="comment" class="form-control input-sm chat-input" placeholder="Write your message here..." />
	    <span class="input-group-btn">     
            <input value="Add comment" type="submit" class="btn btn-primary btn-sm">
        </span>
    </form>
        
    </div>
    <hr>
    <ul id="sortable" class="list-unstyled ui-sortable">
     <c:forEach var="comment" items="${comments}">
    
        <strong class="pull-left primary-font">James</strong>
        </br>
        <li class="ui-state-default">${comment.content} </li>
        </br>
     </c:forEach>
        
    </ul>
    </div>
</div>
			
	
		

</div><!-- /container -->


	</div>
 <jsp:include page="partials/footer.jsp" />
</body>
</html>