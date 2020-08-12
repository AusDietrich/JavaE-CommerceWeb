$(function() {

	var goToCartIcon = function($addTocartBtn) {
		var $cartIcon = $(".my-cart-icon");
		var $image = $(
				'<img width="30px" height="30px" src="'
						+ $addTocartBtn.data("image") + '"/>').css({
			"position" : "fixed",
			"z-index" : "999"
		});
		$addTocartBtn.prepend($image);
		var position = $cartIcon.position();
		$image.animate({
			top : position.top,
			left : position.left
		}, 500, "linear", function() {
			$image.remove();
		});
	}

	$('.my-cart-btn').myCart({
		classCartIcon : 'my-cart-icon',
		classCartBadge : 'my-cart-badge',
		affixCartIcon : true,
		checkoutCart : function(products) {
			$.each(products, function() {
				console.log(this);
			});
		},
		clickOnAddToCart : function($addTocart) {
			goToCartIcon($addTocart);
		},
		getDiscountPrice : function(products) {
			var total = 0;
			$.each(products, function() {
				total += this.quantity * this.price;
			});
			return total * 0.5;
		}
	});
});
//build pop-up/expansion modal here
$(document).ready(function(){
	var IDs = [];
   	$(".divy").click(function(){
   		IDs = [];
   		$(this).find("p").each(function(){ IDs.push(this); });
   		 console.log($(IDs[0]).text());
   		 console.log($(IDs[1]).text());
   		$(this).find("img").each(function(){ IDs.push(this); });
   		console.log($(IDs[2]).attr('src'));
   		$("#modalTitle").text($(IDs[1]).text());
   		$("#modalPrice").text($(IDs[0]).text());
   		$("#modalImg").attr("src",$(IDs[2]).attr('src'));
   		$('#myModal').modal('show');
   	});
});