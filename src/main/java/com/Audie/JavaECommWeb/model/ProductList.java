package com.Audie.JavaECommWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductList {

	public int id;
	public String colorImg;
	public String title;
	public Double price;
	public String description;
}
