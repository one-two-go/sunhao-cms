/**
 * 
 */

$(function(){
		alert("页面加载完成");
		$(".menu li").click(function(){
			var url  = $(this).attr("data");
			//alert(url);
			location.href=url;
		})
	})
	
function showArticle(articleId){
	window.open("/article/showdetail?id="+articleId)
}