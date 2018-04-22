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

<!-- Added for audio player : start -->
<link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>
<!-- Added for audio player : end -->

  <style>
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  </style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Post</title>
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
  <a class="nav-item nav-link active" href="#">Add Post</a>
  <a class="nav-item nav-link " href="/viewFriends">View friends</a>
  <a class="nav-item nav-link" href="#">Notification</a>
  
</nav>
</div>

<div class="row-fluid" style="clear:  left;border:2px solid black">
<form id="postForm" action="/savePost" method="POST" style="border:2px black solid" enctype="multipart/form-data">
			
			
				<div class="form-group">
					<label for="desc">Upload image</label><br> 
					<input type="file" name="imagePost"  id="imagePost" required="required">
				</div>
				<div class="form-group">
					<label for="audio">Caption Audio</label> 
					<audio id="myAudio" class="video-js vjs-default-skin"></audio>
				</div>
				<div class="form-group">
					<label for="text">Caption Text</label> 
					<input type="text" name="text" class="form-control" id="text"
						placeholder="Enter short description">
				</div>
				
				<input type="hidden" id="audioDataId" name="audioData">
				
				<button id="submitBtn" type="submit" class="btn btn-primary">Save</button>
				
				
</form>
</div>
</div>

<!-- Script for audio recorder : start -->
<script>
var player = videojs("myAudio", {
    controls: true,
    width: 300,
    height: 160,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

// error handling
player.on('deviceError', function() {
    console.log('device error:', player.deviceErrorCode);
});

// user clicked the record button and started recording
player.on('startRecord', function() {
    console.log('started recording!');
});

// user completed recording and stream is available
player.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player.recordedData);
    var fileReader = new FileReader();
    fileReader.readAsDataURL(player.recordedData);
    fileReader.onloadend = function(){
    	var base64data= fileReader.result;
    	//console.log('hererere : ',base64data);
    	$('#audioDataId').val(base64data);
    }
});

$(document).ready(function(){
	$('#submitBtn').on("click",function(){
		$('#postForm').submit();	
		});
});
</script>
<!-- Script for audio recorder : end -->
</body>

</html>