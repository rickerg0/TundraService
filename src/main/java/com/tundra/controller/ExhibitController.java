package com.tundra.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.entity.ExhibitTagMedia;
import com.tundra.response.ExhibitTagSummaryResponse;
import com.tundra.service.TundraService;

@Controller 

@RequestMapping("/tag")
public class ExhibitController extends AbstractController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TundraService tundraService;
	
	@RequestMapping(value="/{tag}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getExhibitTagByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="tag") String tag) {
		try {
			getSecurityService().validate(token);
			return new ResponseEntity<ExhibitTagSummaryResponse>(tundraService.findSummaryByExhibitTag(tag),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/media/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getExhibitMediaByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {
		try {
			getSecurityService().validate(token);
			return new ResponseEntity<ExhibitTagMedia>(tundraService.findMediaById(id),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getExhibits(HttpServletResponse httpResponse, @RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {
		try {
			getSecurityService().validate(token);
			return new ResponseEntity<List<ExhibitTagSummaryResponse>>(tundraService.findSummaryList(),HttpStatus.OK);
		} catch (Throwable t) {
			return new ResponseEntity<String>(ERROR_PREFIX + t.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
