package com.tundra.controller;



import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.entity.ExhibitTag;
import com.tundra.service.TundraService;

@Controller 

@RequestMapping("/tag")
public class ExhibitController implements  Serializable {

	private static final String ERROR_PREFIX = "Whoops : ";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private TundraService tundraService;
	
	
	
	@RequestMapping(value="/{tag}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getExhibitByTagId(HttpServletResponse httpResponse, @PathVariable(value="tag") String tag) {
		try {
			return new ResponseEntity<ExhibitTag>(tundraService.findByTag(tag),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}

}
