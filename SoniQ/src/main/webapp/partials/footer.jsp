 <div id="footer">
      <div class="container">

<ul id="a">
			<li><a href="">Disclaimer</a></li>
			<li><a href="">Terms of agreement</a></li>
			<li><a href="">API</a></li>
			<li><a href="">Contact</a></li>
		</ul>
      </div>
 </div>
 
 <script src="./js/jquery.js"></script>
 <script src="./js/bootstrap.js"></script>
 <script src="./js/cheet.js"></script>
 <script src="./js/audio.min.js"></script>
 <script>
	 cheet('b a a s k a t', function () {
		 var img = document.createElement("IMG");
  		 img.src = "./img/icons/a.gif";
  		 document.getElementById('a').appendChild(img);
	});
	
	cheet('v a s q', function () {
  		 var img = document.createElement("IMG");
  		 img.src = "./img/icons/b.gif";
  		 document.getElementById('sortable').appendChild(img);
	});

  	audiojs.events.ready(function() {
   		var as = audiojs.createAll();
  	});
</script>