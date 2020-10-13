package com.Audie.JavaECommWeb.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Audie.JavaECommWeb.entity.ErrorMessage;
import com.Audie.JavaECommWeb.entity.Form;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public final String handleNull(Exception ex) {
		System.out.println("EXCEPTION: "+ex.getClass().getName());
		return ex.getClass().getName();
	}
	@ExceptionHandler(HttpServerErrorException.class)
	public final ModelAndView handleHttpClientException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		String details=ex.getMessage().substring(ex.getMessage().indexOf(":[")+2);
		details = details.replaceAll("\"", "").replaceAll("\\\\", "");
		details = details.substring(0, details.lastIndexOf("}")-1);
		String[] det = details.split("],");
		det[0] = det[0]+"]";
		modelAndView.setViewName("error");
		modelAndView.addObject("message", det[0]);
		modelAndView.addObject("input", det[1]);
		return modelAndView;
	}
	@ExceptionHandler(Exception.class)
	public final void handleAllExceptions(Exception ex, WebRequest req){
		System.out.println("EXCEPTION CAUGHT: "+ex.getClass().getName());
	}
	
	private final ErrorMessage errorReader(String errorMessage, String errorCode) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(errorCode);
		error.setErrorMessage(errorMessage);
		return error;
	}
}
