package com.tundra.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class ExhibitControllerTest extends AbstractControllerTest {

	private MockMvc mockMvc;
	private static final String URL = "/tag/7c:ec:79:fc:ed:34-80";


	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getExhibitTag() throws Exception {

		// object mapper that handles the json parsing
		ObjectMapper mapper = new ObjectMapper();

		String content = getResponseContent(mockMvc, URL);

		// object mapper that handles the json parsing
		JsonNode org = (JsonNode)mapper.readTree(content);

		assertThat(org.get("organizationName"), notNullValue());
		assertThat(org.get("locationName"), notNullValue());
		assertThat(org.get("itemTagName"), notNullValue());
		assertThat(org.get("itemTagId"), notNullValue());
		assertThat(org.get("itemTagTag"), notNullValue());

	}
}
