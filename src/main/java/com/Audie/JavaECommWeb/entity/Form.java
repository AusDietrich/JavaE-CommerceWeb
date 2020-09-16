package com.Audie.JavaECommWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Form {

	public String city1;
	public String city2;
	public String city3;
	public String city4;
	public String city5;
	public String city6;
	public Boolean async;
}
