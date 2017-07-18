package com.tundra.dao;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.entity.Location;
import com.tundra.entity.Organization;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class ItemTagDAOTest {

	@Autowired 
	private ItemTagDAO exhibitTagDAO;
	
	@Autowired 
	private OrganizationDAO organizationDAO;
	
	@Test
	public void saveItemTagTest() {
		
		List<Organization> orgs = organizationDAO.findAll();
		assertThat(orgs, notNullValue());
		
		Organization org = null;
		
		if (!orgs.isEmpty()) {
			org = orgs.get(0);
		}
		
		assertThat(org, notNullValue());
		assertThat(org.getLocationSet(), notNullValue());
		
		Location loc = null;
		
		if (!org.getLocationSet().isEmpty()) {
			loc = org.getLocationSet().iterator().next();
		}
		
		assertThat(loc, notNullValue());
		assertThat(loc.getItemTagSet(), notNullValue());
		
		ItemTag et = null;
		
		if (!loc.getItemTagSet().isEmpty()) {
			et = loc.getItemTagSet().iterator().next();
		}
		
		assertThat(et, notNullValue());
		
		ItemTagMedia media = new ItemTagMedia();
		et.getItemTagMediaSet().add(media);
		media.setItemTag(et);

		// set content here
		
		
		exhibitTagDAO.save(et);
	}
}
