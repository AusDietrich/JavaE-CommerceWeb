package com.Audie.JavaECommWeb.Exceptions;

import lombok.Data;

@Data
public class SpringException extends RuntimeException{
	private String exceptionMsg;
}
