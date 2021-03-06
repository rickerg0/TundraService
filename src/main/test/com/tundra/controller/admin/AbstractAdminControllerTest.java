package com.tundra.controller.admin;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.tundra.controller.AbstractControllerTest;
import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.dao.UserDAO;
import com.tundra.response.AdminValidationResponse;
import com.tundra.security.service.AdminSecurityService;

public class AbstractAdminControllerTest extends AbstractControllerTest {
	
	static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
	private AdminSecurityService securityService;
	
	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
	@Autowired
	UserDAO userDAO;
	
	String getResponseContent(MockMvc mockMvc, String url) throws Exception {

		adminUser = getUser();
		
		setSecurityContext(adminUser);
		
		// create a user and test auth
		userDAO.save(getUser());
		AdminValidationResponse response = securityService.login(USER_NAME, PASSWORD); 
		String token = response.getToken();
		
		MvcResult result = mockMvc.perform(get(url).contentType(CONTENT_TYPE)
				.header(HEADER_SECURITY_TOKEN, token)).andExpect(status().isOk()).andReturn();

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		String newToken = result.getResponse().getHeader(HEADER_SECURITY_TOKEN);

		assertThat(content, notNullValue());
		assertThat(newToken, notNullValue());
		assertThat(newToken, not(token));

		return content;
	}
	
	String postResponseContent(MockMvc mockMvc, String url, Object payload) throws Exception {

		adminUser = getUser();
		
		setSecurityContext(adminUser);
		
		// create a user and test auth
		userDAO.save(getUser());

		AdminValidationResponse response = securityService.login(USER_NAME, PASSWORD); 
		String token = response.getToken();

		MvcResult result = mockMvc.perform(post(url).contentType(CONTENT_TYPE)
				.content(convertObjectToJsonBytes(payload))
				.header(HEADER_SECURITY_TOKEN, token)).andExpect(status().isOk()).andReturn();
		

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		String newToken = result.getResponse().getHeader(HEADER_SECURITY_TOKEN);
		
		assertThat(content, notNullValue());
		assertThat(newToken, notNullValue());
		assertThat(newToken, not(token));

		return content;
	}
	
}
