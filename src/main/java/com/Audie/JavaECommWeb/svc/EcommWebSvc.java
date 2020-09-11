package com.Audie.JavaECommWeb.svc;

import java.util.List;

import com.Audie.JavaECommWeb.model.ColorAngularEntity;
import com.Audie.JavaECommWeb.model.ProductList;

public interface EcommWebSvc {

	public List<ProductList> productListCaller();
	
	public List<ColorAngularEntity> getAngularList();
}
