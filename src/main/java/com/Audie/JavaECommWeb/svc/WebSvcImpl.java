package com.Audie.JavaECommWeb.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Audie.JavaECommWeb.ApiCaller.ApiCaller;
import com.Audie.JavaECommWeb.entity.Form;
import com.Audie.JavaECommWeb.entity.WeatherEnt;
import com.Audie.JavaECommWeb.model.ProductList;

@Service
public class WebSvcImpl implements WebSvc {

	@Autowired
	ApiCaller apiSvc;
	
	@Override
	public List<ProductList> productListCaller() {
		List<ProductList> prodList = apiSvc.productListCaller();
		return prodList;
	}

	@Override
	public List<WeatherEnt> weatherCall(Form form) {
		List<WeatherEnt> listWeather = apiSvc.weatherListCaller(form);
		return listWeather;
	}
}
