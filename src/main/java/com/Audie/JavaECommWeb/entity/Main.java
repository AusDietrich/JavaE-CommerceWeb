package com.Audie.JavaECommWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

	private Integer temp;
	private Integer temp_min;
	private Integer temp_max;
}
