package com.Audie.JavaECommWeb.svc;

import org.springframework.stereotype.Service;

import com.Audie.JavaECommWeb.model.ProductList;

@Service
public class EcommWebSvcImpl implements EcommWebSvc {

	public ProductList getProductList() {
		ProductList productList= new ProductList();
		return productList;
	}
}
