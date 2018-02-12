package com.tundra.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional
public class ItemControllerTest extends AbstractPublicControllerTest {

	private static final String GET_TAG_URL = "/tag/7c:ec:79:fc:ed:34-80";


	@Test
	public void getItemTag() throws Exception {

		// object mapper that handles the json parsing
		ObjectMapper mapper = new ObjectMapper();

		String content = getResponseContent(mockMvc, GET_TAG_URL);

		// object mapper that handles the json parsing
		JsonNode org = (JsonNode)mapper.readTree(content);

		assertThat(org.get("organizationName"), notNullValue());
		assertThat(org.get("locationName"), notNullValue());
		assertThat(org.get("itemTagName"), notNullValue());
		assertThat(org.get("itemTagId"), notNullValue());
		assertThat(org.get("itemTagTag"), notNullValue());

	}
	

}
