package com.tundra.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.dao.UserDAO;
import com.tundra.entity.RegisteredDevice;
import com.tundra.response.AdminValidationResponse;
import com.tundra.service.AdminSecurityService;
import com.tundra.service.SecurityService;
import com.tundra.test.AbstractTest;

public class AbstractControllerTest extends AbstractTest {
	
	static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	private AdminSecurityService adminSecurityService;
	
	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
	@Autowired
	UserDAO userDAO;
	
	String getResponseContent(MockMvc mockMvc, String url) throws Exception {

		List<RegisteredDevice> devices = registeredDeviceDAO.findAll();
		
		String token = securityService.getToken(devices.get(0).getEmail());
		
		MvcResult result = mockMvc.perform(get(url).contentType(CONTENT_TYPE)
				.header(AbstractController.HEADER_SECURITY_TOKEN, token))
				.andExpect(status().isOk()).andReturn();
		

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		assertThat(content, notNullValue());

		return content;
	}

	String getAdminResponseContent(MockMvc mockMvc, String url) throws Exception {

		// create a user and test auth
		userDAO.save(getUser());
		AdminValidationResponse response = adminSecurityService.login(USER_NAME, PASSWORD); 
		String token = response.getToken();
		MvcResult result = mockMvc.perform(get(url).contentType(CONTENT_TYPE)
				.header(AbstractController.HEADER_SECURITY_TOKEN, token)).andExpect(status().isOk()).andReturn();
		

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		assertThat(content, notNullValue());

		return content;
	}
	
	String postAdminResponseContent(MockMvc mockMvc, String url, Object payload) throws Exception {

		// create a user and test auth
		userDAO.save(getUser());
		AdminValidationResponse response = adminSecurityService.login(USER_NAME, PASSWORD); 
		String token = response.getToken();
		MvcResult result = mockMvc.perform(post(url).contentType(CONTENT_TYPE)
				.content(convertObjectToJsonBytes(payload))
				.header(AbstractController.HEADER_SECURITY_TOKEN, token)).andExpect(status().isOk()).andReturn();
		

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		assertThat(content, notNullValue());

		return content;
	}	

    protected byte[] convertObjectToJsonBytes(Object payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(payload);
    }
 
}
