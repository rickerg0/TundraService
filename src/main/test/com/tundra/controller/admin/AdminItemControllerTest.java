package com.tundra.controller.admin;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
public class AdminItemControllerTest extends AbstractAdminControllerTest {

	private static final String GET_TAG_URL = "/admin/tag/7c:ec:79:fc:ed:34-80";
	private static final String SAVE_TAG_URL = "/admin/tag/add";


	@Autowired
	private LocationDAO locationDAO;
	
	@Test
	public void getItemTag() throws Exception {

		// object mapper that handles the json parsing
		ObjectMapper mapper = new ObjectMapper();

		String content = getResponseContent(mockMvc, GET_TAG_URL);

		JsonNode org = (JsonNode)mapper.readTree(content);

		assertThat(org, notNullValue());
		
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

		String content = postResponseContent(mockMvc, SAVE_TAG_URL, tag);
		
		assertThat(content, notNullValue());
	}
	

}
