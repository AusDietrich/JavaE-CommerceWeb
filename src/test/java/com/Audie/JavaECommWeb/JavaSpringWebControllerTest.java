package com.Audie.JavaECommWeb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.Audie.JavaECommWeb.ApiCaller.ApiCaller;
import com.Audie.JavaECommWeb.api.EcommWebController;
import com.Audie.JavaECommWeb.model.ProductList;
import com.Audie.JavaECommWeb.svc.EcommWebSvc;

@RunWith(MockitoJUnitRunner.class)
public class JavaSpringWebControllerTest {
	
	MockMvc mockMvc;	
	@Mock
	EcommWebSvc mockSvc;
	@Mock
	ApiCaller callerSvc;
	@InjectMocks
	EcommWebController classUnderTest;
	
	@Test
	public void TestIndex() {
		ModelAndView modelAndView = Mockito.mock(ModelAndView.class);
		modelAndView.setViewName("index");
		ModelAndView returnedModelAndView = Mockito.mock(ModelAndView.class);
		returnedModelAndView = classUnderTest.index();
		assertEquals(modelAndView.getViewName(),returnedModelAndView.getViewName());
	}
	@Test
	public void TestColores() {
		ModelAndView modelAndView = Mockito.mock(ModelAndView.class);
		modelAndView.setViewName("products");
		ModelAndView returnedModelAndView = Mockito.mock(ModelAndView.class);
//		returnedModelAndView = classUnderTest.colores(model);
		List<ProductList> prodList = new ArrayList();
		when(callerSvc.productListCaller()).thenReturn(prodList);
		assertEquals(modelAndView,returnedModelAndView);
	}
}
