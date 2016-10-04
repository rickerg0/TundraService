package com.tundra.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public class AbstractControllerTest {
	
	static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	String getResponseContent(MockMvc mockMvc, String url) throws Exception {

		MvcResult result = mockMvc.perform(get(url).contentType(CONTENT_TYPE)).andExpect(status().isOk()).andReturn();

		// the request should successfully complete
		String content = result.getResponse().getContentAsString();
		assertThat(content, notNullValue());

		return content;
	}

}
