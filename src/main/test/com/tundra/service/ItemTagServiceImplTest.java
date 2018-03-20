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
import com.tundra.service.admin.AdminItemTagService;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional // so tests roll back
public class ItemTagServiceImplTest {

	private static final String TAG = "7c:ec:79:fc:ed:34-90";
	
	@Autowired 
	private ItemTagService itemTagService;

	@Autowired 
	private AdminItemTagService adminItemTagService;

	@Autowired
	private LocationDAO locationDAO;
	
	@Test
	public void getItemTagTest() {
		ItemTagSummaryResponse response = itemTagService.findSummaryByItemTag(TAG);
		assertThat(response, notNullValue());
		
	}
	
	@Test
	public void saveItemTagTest() {
		
		List<ItemTagSummaryResponse> before = itemTagService.findSummaryList();
		
		ItemTag tag = new ItemTag();
		tag.setTag("abcd-1234");
		tag.setName("test");
		tag.setDescription("test description");
		tag.setActive(true);
		
		// grab a location
		List<Location> locations = locationDAO.findAll();
		tag.setLocation(locations.get(0));
		
		adminItemTagService.save(tag);
		
		List<ItemTagSummaryResponse> after = itemTagService.findSummaryList();
		
		assertTrue((before.size() + 1) == after.size());
		
	}
	
}
