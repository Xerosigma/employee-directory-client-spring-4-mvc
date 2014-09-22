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
package com.nestorledon.employeedirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan( basePackages = "com.provider.config" )
public class ControllerBase {

	protected static final String REL_SELF = "self";
	protected static final String REL_SEARCH = "search";
	protected static final String REL_EMPLOYEE = "employee";
	protected static final String RESOURCE_URI = "http://localhost:8080/employees";
	
	@Autowired protected ObjectMapper objectMapper;
	@Autowired protected RestTemplate restTemplate;
}
