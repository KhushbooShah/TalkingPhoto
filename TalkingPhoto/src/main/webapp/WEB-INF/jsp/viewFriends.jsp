<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet"  href="friendlist.css">
<script src="temp.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friend List</title>
<script>
$(document).ready(function() {
	$(".nav-item").click(function () {
	    $(".nav-item").removeClass("nav-link active").addClass("nav-link");
	    // $(".tab").addClass("active"); // instead of this do the below 
	    $(this).addClass("nav-link active");   
	});
	});
</script>
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      //testAPI();
    } else {
      // The person is not logged into your app or we are unable to tell.
      /* document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
       */window.location.href="/index"

    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '1951365641843851',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  /* function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=id,email,name', function(response) {
      console.log(response);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
       });	
    
  } */
</script>

<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="#">
    <img src="/images/Boostrap_logo.PNG" width="30" height="30" class="d-inline-block align-top" alt="">
    Talking Photo
  </a>
  <fb:login-button scope="public_profile,email" onlogin="checkLoginState();" data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false">
	</fb:login-button>
  
</nav>

</head>
<body>


<div class="container-fluid">
<div class="row-fluid" style="border:2px solid red">
<nav class="nav nav-pills nav-justified">
  <a class="nav-item nav-link " href="/viewProfile">View My Profile</a>
  <a class="nav-item nav-link" href="/createPost">Add Post</a>
  <a class="nav-item nav-link active" href="/viewFriends">View friends</a>
  <a class="nav-item nav-link" href="#">Notification</a>
  
</nav>
</div>

<!-- This part render post user selected from  his profile : start -->
<c:if test="${not empty  viewPostObj}">
<div class="row-fluid" style="clear:  left;border:2px solid black">
	<div>
		<img alt="N/A" src="${viewPostObj.postImageURL}" width="250px" height="250px" 
					style="border : 2px solid black" onclick="viewPost(${postVar.postId })">
	</div>
	<div>
		<audio style="width:250px;" controls controlsList="nodownload" >
			<source src="${viewPostObj.postAudioURL }" type="audio/webm">
		</audio>
		<c:out value="${viewPostObj.postCaption }"></c:out>
	</div>

</div>
</c:if>
<!-- This part render post user selected from  his profile : end -->


<!-- Renders all the friends of the user : start -->
<c:if test="${not empty friends }">
<div class="row-fluid" style="clear:  left;border:2px solid black">
<c:forEach var="i" items="${friends}">
<div class="square">
   <div class="content">
        <div class="table">
            <div class="table-cell">
                <form id="formId" method="POST" action="/viewFriendProfile">
                <img style="cursor: pointer;" onclick="getProfile('${i.fbIDUser}')" class="rs" width="200px" height="200px" src="${i.displayImageURL }"/>
                <c:out value="${i.name }"></c:out>
                <input type="hidden" name="friendId">
                </form>
            </div>
            
        </div>
    </div>
</div>
</c:forEach>	
</div>
</c:if>
<!-- Renders all the friends of the user : end -->


<!-- This part render profile info only if user clicks on friend to see his profile : start -->
<c:if test="${not empty  userObj}">
<c:set var="userVar" value="${userObj}"></c:set>
<div class="row-fluid" style="clear:  left;border:2px solid black">

	<div class="col-md-4" style="float:left;border:2px solid yellow">
		<img alt="Image N/A" src="${userVar.displayImageURL}" width="200px" height="200px" style="border : 2px solid black">
	</div>
	<div class="col-md-8" style="float:left;height: 50px;width: 500px;margin-left: 30px; border:2px solid blue">
		<p>Name : <c:out value="${userVar.name }"> : name :</c:out></p>	
		<p>Description : <c:out value="${userVar.description }"></c:out></p>
	</div>

</div>
</c:if>
<!-- This part render profile info only if user clicks on friend to see his profile : start -->
</div>

<!-- This part render post only if user clicks on friend to see his profile : start -->
<c:if test="${not empty  postObj}">
<div class="container-fluid">
<div class="row-fluid" style="clear:  left;border:2px solid red">
	<form id="viewPostForm" action="/viewPost" method="POST">
	<c:forEach var="postVar" items="${postObj }" varStatus="count">
	<div class="col-md-3" style="float:left;border:2px solid yellow">
		<input type="hidden" name="selectedPostId" value="${postVar.postId }">
		<img alt="N/A" src="${postVar.postImageURL}" width="250px" height="250px" 
					style="border : 2px solid black;cursor: pointer;" onclick="viewPost(${postVar.postId })">
		<audio style="width:250px;" controls controlsList="nodownload" >
			<source src="${postVar.postAudioURL }" type="audio/webm">
		</audio>
	</div>
	</c:forEach>
		<input type="hidden" name="postIdForViewPost">
		<input type="hidden" name="comingFromScreenName">
		
	</form>
</div>
</div>
</c:if>
<!-- This part render post only if user clicks on friend to see his profile : start -->
</body>
<script>
function getProfile(id){
	$('[name="friendId"]').val(id);
	$('#formId').submit();
}

function viewPost(id){
	$('[name="postIdForViewPost"]').val(id);
	$('[name="comingFromScreenName"]').val('viewFriends');
	$('#viewPostForm').submit();
}
</script>
</html>