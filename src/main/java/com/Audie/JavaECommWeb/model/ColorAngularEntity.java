package com.Audie.JavaECommWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorAngularEntity {

	public int id;
	public String color;
	public String descript;
    public String price;
}
