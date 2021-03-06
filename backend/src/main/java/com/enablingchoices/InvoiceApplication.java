package com.enablingchoices;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class InvoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
	}
	
	@Bean
	ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
	    return new ErrorViewResolver() {
	        @Override
	        public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
	            return status == HttpStatus.NOT_FOUND
	                    ? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
	                    : null;
	        }
	    };
	}
}
