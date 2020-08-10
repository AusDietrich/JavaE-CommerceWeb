package com.Audie.JavaECommWeb.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Audie.JavaECommWeb.ApiCaller.ApiCaller;
import com.Audie.JavaECommWeb.svc.EcommWebSvc;

@RestController
public class EcommWebController {

	@Autowired
	EcommWebSvc ecomSvc;
	@Autowired
	ApiCaller svc;
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
	    return modelAndView;
	}
	
	@GetMapping("/co")
	public void colores() {
		svc.productListCaller();
	}
}
