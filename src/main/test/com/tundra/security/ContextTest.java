package com.tundra.security;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tundra.springconfig.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
public class ContextTest {
	
	private static final String USER_1 = "USER ONE";
	private static final String USER_2 = "USER TWO";

	private static final String FAUX_TOKEN_FOR_USER_1 = "TOKEN FOR USER ONE";
	private static final String FAUX_TOKEN_FOR_USER_2 = "TOKEN FOR USER TWO";

	@Test
	public void contextTest() throws InterruptedException {
		

		Runnable one = new Runnable() {
		    public void run() {
		        try {

		        	System.err.println("Setting context for " + USER_1);
		    		SecurityContextHolder.getContext().setAuthentication(new PublicAuthentication(USER_1, FAUX_TOKEN_FOR_USER_1));

		    		Thread.sleep(2000);
		    		
		    		System.err.println("Thread 1 " + USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		
		            Thread.sleep(5000);
		            
		    		Runnable littleOne = new Runnable() {
		    		    public void run() {
		    		        try {

		    		    		System.err.println("Thread 1 Child " + USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		    		
		    		            Thread.sleep(5000);
		    		            
		    		    		System.err.println("Thread 1 Child " + USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		    		
		    		    		assertTrue(USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));

		    		        } catch(InterruptedException v) {
		    		            System.out.println(v);
		    		        }
		    		    }  
		    		};

		    		Thread t11 = new Thread(littleOne);
		    		t11.start();		
		    		t11.join();		
		            
		    		System.err.println("Thread 1 " + USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		
		    		assertTrue(USER_1.equals(SecurityContextHolder.getContext().getAuthentication().getName()));

		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};

		Runnable two = new Runnable() {
		    public void run() {
		        try {
		        	System.err.println("Setting context for " + USER_2);
		    		SecurityContextHolder.getContext().setAuthentication(new PublicAuthentication(USER_2, FAUX_TOKEN_FOR_USER_2));
		    		
		            Thread.sleep(5000);

		    		System.err.println("Thread 2 " + USER_2.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		
		            Thread.sleep(5000);

		    		System.err.println("Thread 2 " + USER_2.equals(SecurityContextHolder.getContext().getAuthentication().getName()));

		    		assertTrue(USER_2.equals(SecurityContextHolder.getContext().getAuthentication().getName()));
		    		
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};

		Thread t1 = new Thread(one);
		Thread t2 = new Thread(two);
		
		t1.start();		
		t2.start();		

		t1.join();		
		t2.join();		
	}
}
