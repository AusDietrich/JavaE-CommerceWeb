package com.Audie.JavaECommWeb.api;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Audie.JavaECommWeb.model.Buying;
import com.Audie.JavaECommWeb.model.Cart;
import com.Audie.JavaECommWeb.model.ProductList;
import com.Audie.JavaECommWeb.svc.EcommWebSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EcommWebController {

	@Autowired
	EcommWebSvc ecomSvc;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("/products")
	public ModelAndView colores(Model model) {
		List<ProductList> prodList = ecomSvc.productListCaller();
		model.addAttribute("prodList", prodList);
		Buying obj = new Buying();
		model.addAttribute("obj", obj);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("products");
		return modelAndView;
	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(Buying obj, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		Double total = 0.00;
		try {
			Cart[] cart = mapper.readValue(obj.getItems(), Cart[].class);
			for(int i = 0; i < cart.length; i ++) {
				cart[i].setTotal(cart[i].getPrice()*Double.parseDouble(cart[i].getQuantity()));
				total+= cart[i].getTotal();
			}
			model.addAttribute("cart", cart);
			model.addAttribute("total", df2.format(total));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("checkout");
		return modelAndView;
	}

	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public Buying total(@RequestBody Buying obj, Model model) {
		return obj;
	}
}
