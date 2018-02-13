package com.tundra.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@Transactional
public class LoginControllerTest extends AbstractPublicControllerTest {

	private static final String LOGIN_URL = "/login?email=a";


	@Test
	public void loginTest() throws Exception {

		String content = getResponseContent(mockMvc, LOGIN_URL);

	}
	

}
