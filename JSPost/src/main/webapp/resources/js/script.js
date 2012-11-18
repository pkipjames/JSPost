$(document).ready(function(){
	$("#reg_button").click(function(event){
	     $("#reg_form").slideToggle("slow");
	   });
	
	$("#sign_button").click(function(event){
	     $("#sign_form").slideToggle("slow");
	   });
	
//	$("nav div").click(function(event){
//		 $("nav div").each(function (i) {
//			$(this).removeClass("selected");
//		  });
//		 var index = $("nav div").index(this);
//		$("nav div").eq(index).addClass("selected");			
//	});
 });