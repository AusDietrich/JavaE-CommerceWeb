package com.Audie.JavaECommWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart {
	public Integer id;
	public String img;
	public String name;
	public Double price;
	public String quantity;
	public String Summary;
}
