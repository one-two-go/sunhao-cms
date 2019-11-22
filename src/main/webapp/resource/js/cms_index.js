/**
 * 
 */

$(function(){
		$(".menu li").click(function(){
			var url  = $(this).attr("data");
			location.href=url;
		})
	})
	
function showArticle(articleId){
	window.open("/article/showdetail?id="+articleId)
}