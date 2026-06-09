package com.factoryflow.auth.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

}
