package com.Audie.JavaECommWeb.api;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Audie.JavaECommWeb.entity.Form;
import com.Audie.JavaECommWeb.entity.WeatherEnt;
import com.Audie.JavaECommWeb.model.Buying;
import com.Audie.JavaECommWeb.model.Cart;
import com.Audie.JavaECommWeb.model.ProductList;
import com.Audie.JavaECommWeb.svc.WebSvc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class WebController {

	@Autowired
	WebSvc ecomSvc;

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
	@RequestMapping(value="/weather", method=RequestMethod.GET)
	public ModelAndView weatherIndex(Model model) throws InterruptedException, ExecutionException 
	{
	  	Form form = new Form();
	   	model.addAttribute("form", form);
	   	ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("weather");
		return modelAndView;
	}
	@RequestMapping(value="/weatherTime")
    public ModelAndView weatherReturn(Form form,Model model) throws InterruptedException, ExecutionException 
    {
		Map<String, String> map = convertObjToMap(form, 
			    new TypeReference<Map<String, String>>(){});
		List<String> formList = new ArrayList<>();
		int empty = 0;
		for(Map.Entry<String, String> entry : map.entrySet()) {
			if(StringUtils.isBlank(entry.getValue())) {
				entry.setValue(emptyCityInput(empty));
				empty++;
			}
		}
		form = convertMapToObj(map);
		System.out.println("form: "+form);
    	List<WeatherEnt> weatherList = new ArrayList<>(); 
    	Long start = System.currentTimeMillis();
   		weatherList = ecomSvc.weatherCall(form);
		Long end = System.currentTimeMillis();
        Long time = end - start;
        System.out.println((Double.parseDouble(time.toString())/1000)+" seconds long");
        System.out.println(weatherList); 
        model.addAttribute("time", (Double.parseDouble(time.toString())/1000)+" seconds long");
        model.addAttribute("weather", weatherList);
     	ModelAndView modelAndView = new ModelAndView();
 		modelAndView.setViewName("weatherReturn");
 		return modelAndView;
    }
	public String emptyCityInput(int checked) {
		String returnedCity;
		switch(checked) {
			case 0:
				returnedCity = "Phoenix";
				break;
			case 1:
				returnedCity = "Tucson";
	    		break;
			case 2:
				returnedCity = "Willcox";
	    		break;
			case 3:
				returnedCity = "Yuma";
	    		break;
			case 4:
				returnedCity = "Flagstaff";
	    		break;
			case 5:
				returnedCity = "Page";
	    		break;
	    	default:
	    		returnedCity = "Phoenix";
	    		break;
		}
		return returnedCity;
	}
	public <T> T convertObjToMap(Object o, TypeReference<T> ref) {
	    ObjectMapper mapper = new ObjectMapper();
	    return mapper.convertValue(o, ref);
	}
	public Form convertMapToObj(Map<String, String> map) {
		ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		Form form = mapper.convertValue(map, Form.class);
		return form;
	}
}