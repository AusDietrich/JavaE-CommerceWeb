package com.Audie.JavaECommWeb.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Audie.JavaECommWeb.ApiCaller.ApiCaller;
import com.Audie.JavaECommWeb.model.ColorAngularEntity;
import com.Audie.JavaECommWeb.model.ProductList;

@Service
public class EcommWebSvcImpl implements EcommWebSvc {

	@Autowired
	ApiCaller svc;
	
	@Override
	public List<ProductList> productListCaller() {
		List<ProductList> prodList = svc.productListCaller();
		return prodList;
	}
	@Override
	public List<ColorAngularEntity> getAngularList(){
		List<ColorAngularEntity> prodList = svc.getAngularList();
		return prodList;
	}
}
