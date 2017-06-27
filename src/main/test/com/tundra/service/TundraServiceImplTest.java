package com.tundra.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tundra.response.ExhibitTagSummaryResponse;
import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class TundraServiceImplTest {

	private static final String EXHIBIT_TAG = "7c:ec:79:fc:ed:34-90";
	
	@Autowired 
	private TundraService tundraService;
	
	@Test
	public void getExhibitTagTest() {
		ExhibitTagSummaryResponse response = tundraService.findSummaryByExhibitTag(EXHIBIT_TAG);
		assertThat(response, notNullValue());
		
	}
	
}
