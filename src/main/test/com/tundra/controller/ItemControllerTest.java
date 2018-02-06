package com.tundra.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

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
import com.tundra.dao.LocationDAO;
import com.tundra.entity.ItemTag;
import com.tundra.entity.Location;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional
public class ItemControllerTest extends AbstractControllerTest {

	private MockMvc mockMvc;
	private static final String GET_TAG_URL = "/tag/7c:ec:79:fc:ed:34-80";
	private static final String SAVE_TAG_URL = "/tag/save";


	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private LocationDAO locationDAO;
	
	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

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
	
	@Test
	public void saveItemTag() throws Exception {
		
		ItemTag tag = new ItemTag();
		tag.setTag("abcd-1234");
		tag.setName("test");
		tag.setDescription("test description");
		tag.setActive(true);
		
		// grab a location
		List<Location> locations = locationDAO.findAll();
		tag.setLocation(locations.get(0));

		String content = postAdminResponseContent(mockMvc, SAVE_TAG_URL, tag);
		
		assertThat(content, notNullValue());
	}
	

}
