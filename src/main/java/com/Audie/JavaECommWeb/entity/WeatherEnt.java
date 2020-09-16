package com.Audie.JavaECommWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherEnt {

	private Weather[] weather;
	private Main main;
	private String name;
	private Integer red;
	private Integer blue;
}
