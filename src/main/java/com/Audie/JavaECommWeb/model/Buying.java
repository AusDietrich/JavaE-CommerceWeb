package com.Audie.JavaECommWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Buying {
	
	public String total;
	public Cart[] cart;
}
