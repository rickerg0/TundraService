package com.tundra.controller;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.tundra.dao.RegisteredDeviceDAO;
import com.tundra.entity.RegisteredDevice;
import com.tundra.security.PublicAuthentication;
import com.tundra.security.service.SecurityService;

public class AbstractPublicControllerTest extends AbstractControllerTest {
	
	static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	RegisteredDeviceDAO registeredDeviceDAO;
	
	String getResponseContent(MockMvc mockMvc, String url) throws Exception {

		List<RegisteredDevice> devices = registeredDeviceDAO.findAll();
		
		String token = securityService.getToken(devices.get(0).getEmail());
		
		SecurityContext securityCtx = SecurityContextHolder.getContext();
		securityCtx.setAuthentication(new PublicAuthentication(devices.get(0).getEmail(), token));
		
		MvcResult result = mockMvc.perform(get(url).contentType(CONTENT_TYPE)
				.header(HEADER_SECURITY_TOKEN, token))
				.andExpect(status().isOk()).andReturn();
		

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		String newToken = result.getResponse().getHeader(HEADER_SECURITY_TOKEN);

		assertThat(content, notNullValue());
		assertThat(newToken, notNullValue());
		assertThat(newToken, not(token));

		return content;
	}
}
