package com.Audie.JavaECommWeb.svc;

import java.util.List;

import com.Audie.JavaECommWeb.entity.Form;
import com.Audie.JavaECommWeb.entity.WeatherEnt;
import com.Audie.JavaECommWeb.model.ProductList;

public interface WebSvc {

	public List<ProductList> productListCaller();
	
	public List<WeatherEnt> weatherCall(Form form);
}
