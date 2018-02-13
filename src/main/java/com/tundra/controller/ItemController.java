package com.tundra.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.entity.ItemTagMedia;
import com.tundra.response.ItemTagSummaryResponse;
import com.tundra.service.ItemTagService;


@Controller 
@RequestMapping("/tag/")
public class ItemController extends AbstractPublicController {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(ItemController.class);

	@Autowired
	private ItemTagService itemTagService;
	
	@RequestMapping(value="{tag}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemTagByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="tag") String tag) {

		return new ResponseEntity<ItemTagSummaryResponse>(itemTagService.findSummaryByItemTag(tag),HttpStatus.OK);
	}
	
	@RequestMapping(value="media/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemMediaByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {

		return new ResponseEntity<ItemTagMedia>(itemTagService.findMediaById(id),HttpStatus.OK);
	}
		
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItems(HttpServletResponse httpResponse, @RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {

		return new ResponseEntity<List<ItemTagSummaryResponse>>(itemTagService.findSummaryList(),HttpStatus.OK);
	}
	
}
