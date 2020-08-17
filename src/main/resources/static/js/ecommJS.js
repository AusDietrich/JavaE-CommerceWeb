$(function() {

	var total = 0.00;
	var goToCartIcon = function($addTocartBtn) {
		total = 0.00;
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
			var cart = [];
			$.each(products, function() {
				var item ={id: this.id, img: this.image, name: this.name, price: this.price,
				quantity: this.quantity, summary: this.summary};
				cart.push(item);
				total += this.quantity * this.price;
			});
			var obj = {};
			obj["total"] = total;
			obj["cart"] = cart;
			console.log(obj);
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "total",
				data : JSON.stringify(obj),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		},
		clickOnAddToCart : function($addTocart) {
			goToCartIcon($addTocart);
		}
//		,
//		getDiscountPrice : function(products) {
//			var total = 0;
//			$.each(products, function() {
//				total += this.quantity * this.price;
//			});
//			return total * 0.5;
//		}
	});
});
//build pop-up/expansion modal here
$(document).ready(function(){
	var IDs = [];
   	$(".divyDetails").click(function(){
   		IDs = [];
   		$(this).find("p").each(function(){ IDs.push(this); });
   		$(this).find("img").each(function(){ IDs.push(this); });
   		$("#modalTitle").text($(IDs[1]).text());
   		$("#modalPrice").text($(IDs[0]).text());
   		$("#modalImg").attr("src",$(IDs[2]).attr('src'));
   		$('#myModal').modal('show');
   	});
});