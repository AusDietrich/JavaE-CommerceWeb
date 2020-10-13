package com.Audie.JavaECommWeb.ApiCaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.Audie.JavaECommWeb.entity.Form;
import com.Audie.JavaECommWeb.entity.WeatherEnt;
import com.Audie.JavaECommWeb.model.ProductList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApiCaller {

	@Value("${productList}")
	public String url;
	@Value("${weatherList}")
	public String syncUrl;
	@Value("${authId}")
	public String basicAuthId;
	@Value("${authPassword}")
	public String basicAuthPassword;

	public List<ProductList> productListCaller() {
		List<ProductList> productList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		try {
			 HttpHeaders headers = new HttpHeaders();
			 headers.setBasicAuth(basicAuthId, basicAuthPassword);
			 headers.setContentType(MediaType.APPLICATION_JSON);
			 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			 HttpEntity request = new HttpEntity(headers);
			ResponseEntity<String> response = restTemplate.exchange(
					url,HttpMethod.GET, request, String.class);
			productList = objectMapper.readValue(response.getBody(), new TypeReference<List<ProductList>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	public List<WeatherEnt> weatherListCaller(Form form){
		List<WeatherEnt> weatherList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(basicAuthId, basicAuthPassword);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> request = new HttpEntity<String>(headers);
			System.out.println(syncUrl);
			ResponseEntity<String> response = restTemplate.exchange(
					syncUrl+"/{id}",HttpMethod.GET, request, String.class, objectMapper.writeValueAsString(form));
			System.out.println("Response: "+response);
			weatherList = objectMapper.readValue(response.getBody(), new TypeReference<List<WeatherEnt>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherList;
	}
}
