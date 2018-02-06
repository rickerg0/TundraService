package com.tundra.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.dao.LocationDAO;
import com.tundra.entity.ItemTag;
import com.tundra.entity.Location;
import com.tundra.response.ItemTagSummaryResponse;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional // so tests roll back
public class ExhibitTagServiceImplTest {

	private static final String TAG = "7c:ec:79:fc:ed:34-90";
	
	@Autowired 
	private ItemTagService itemTagService;

	@Autowired
	LocationDAO locationDAO;
	
	@Test
	public void getExhibitTagTest() {
		ItemTagSummaryResponse response = itemTagService.findSummaryByItemTag(TAG);
		assertThat(response, notNullValue());
		
	}
	
	@Test
	public void saveExhibitTagTest() {
		
		List<ItemTag> before = itemTagService.findAllTags();
		
		ItemTag tag = new ItemTag();
		tag.setTag("abcd-1234");
		tag.setName("test");
		tag.setDescription("test description");
		tag.setActive(true);
		
		// grab a location
		List<Location> locations = locationDAO.findAll();
		tag.setLocation(locations.get(0));
		
		itemTagService.save(tag, "me");
		
		List<ItemTag> after = itemTagService.findAllTags();
		
		assertTrue((before.size() + 1) == after.size());
		
	}
	
}
