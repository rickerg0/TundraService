package com.tundra.controller.admin;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional
public class AdminOrganizationControllerTest extends AbstractAdminControllerTest {

	private MockMvc mockMvc;
	private static final String URL = "/admin/org/list";


	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getOrganizations() throws Exception {

		// object mapper that handles the json parsing
		ObjectMapper mapper = new ObjectMapper();

		String content = getResponseContent(mockMvc, URL);

		JsonNode response = (JsonNode)mapper.readTree(content);

		assertThat(response.get("payload"), notNullValue());

		// object mapper that handles the json parsing
		ArrayNode root = (ArrayNode)response.get("payload");

		// or a list of stuff like
		assertThat(root, notNullValue());
		for (JsonNode org : root) {
			assertThat(org.get("name"), notNullValue());
			assertThat(org.get("address1"), notNullValue());
			assertThat(org.get("city"), notNullValue());
			assertThat(org.get("state"), notNullValue());
			assertThat(org.get("zip"), notNullValue());
			assertThat(org.get("phone"), notNullValue());
			assertThat(org.get("created"), notNullValue());
			assertThat(org.get("updated"), notNullValue());
		}
	}
}
