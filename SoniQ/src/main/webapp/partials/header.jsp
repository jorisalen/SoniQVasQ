<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <script src="https://cdn.auth0.com/w2/auth0-widget-5.js"></script>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="logo 	navbar-brand" href="Controller?action=home"> SoniQ</a>
        </div>
        <center>
            <div  class="navbar-collapse collapse" id="navbar-main">
                <ul class="nav navbar-nav">
                    <li> 			
                    
                    </li>
                    
                  
                </ul>
                <c:if test="${empty user}">
                 <audio preload class="audio"></audio>
                    <button class="btn btn-primary pull-right" style="margin-top:-30px;" onclick="widget.signin({state: '${state}'})">Login</button>
					</c:if>
				<c:if test="${not empty user}">
   				<p class="greetings">welcome ${user.getName()}<p>
   						<a class="pull-right btn btn-primary" style="margin-top:5px;" href="Controller?action=logout">Logout</a>
					</c:if>
                </div>
        </center>
    </div>
</div>
