/*
 * Copyright 2014 Nestor E. Ledon (nestorledon@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nestorledon.employeedirectory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nestorledon.employeedirectory.http.HttpComponentsClientHttpRequestFactoryBasicAuth;

@Configuration
//@ImportResource( { "classpath*:/rest_config.xml" } )
@ComponentScan( basePackages = "com.nestorledon.employeedirectory" )
//@PropertySource({ "classpath:rest.properties", "classpath:web.properties" })
public class ApplicationConfig {
	
	private final static String BASIC_AUTH_USER = "user";
	private final static String BASIC_AUTH_PASS = "password";
	
	/**
	 * Needed for Jackson2 to read HATEOAS/HAL JSON.
	 * 
	 * Example 1:
	 * String resultBody = restTemplate.getForObject(link.getHref(), String.class);
	 * Resource<EmployeeDTO> resource = objectMapper.readValue(resultBody, EmployeeDTO.class);
	 * 
	 * Example 2:
	 * MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
	 * converter.setObjectMapper(objectMapper);
	 * restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>> singletonList(converter));
	 * Resource<EmployeeDTO> resource = restTemplate.getForObject(link.getHref(), EmployeeDTO.class);
	 * 
	 * @see http://stackoverflow.com/questions/25812776/proper-way-to-convert-spring-hateoas-link-to-object/25922483#25922483
	 * 
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jackson2HalModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	
	/**
	 * FIXME: objectMapper() call below sane???
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactoryBasicAuth(BASIC_AUTH_USER, BASIC_AUTH_PASS));
		return restTemplate;
	}
	
	/*
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}*/
}
