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
package com.nestorledon.employeedirectory.employees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nestorledon.employeedirectory.ControllerBase;
import com.nestorledon.employeedirectory.rest.Resource;

/**
 * http://localhost:8079/provider-client/employees
 * @author Nestor E. Ledon (nestorledon@gmail.com)
 *
 */
@Controller
public class EmployeeController extends ControllerBase {
	
	private List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
	
	@RequestMapping("/employees")
	public String getAllEmployees() {
		
		String result = null;
		try {
			Resource<EmployeeDTO> resource = restTemplate.getForObject(RESOURCE_URI, Resource.class);
			
			// Get objects with relation "employee"
			for(Link l : resource.getLinks()) {
				if(l.getRel().equals(REL_EMPLOYEE)) {
					EmployeeDTO employee = (EmployeeDTO)linkToObject(l);
					
					if(employee != null) {
						employees.add(employee);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "error";
			return result;
		}
		return result;
	}
	
	/**
	 * Converts Link to object <T>.
	 * TODO: Make generic.
	 * TODO: Move to ControllerBase.
	 * @param link - A Spring HATEOAS Link.
	 * @return object <T>
	 */
	public EmployeeDTO linkToObject(Link link) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>> singletonList(converter));
		EmployeeDTO resource = restTemplate.getForObject(link.getHref(), EmployeeDTO.class);
		return resource;
	}
	
	@ModelAttribute("employees")
	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
}