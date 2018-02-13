package com.tundra.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.response.AdminValidationResponse;
import com.tundra.response.ItemTagSummaryResponse;
import com.tundra.service.ItemTagService;

@Controller 

@RequestMapping("/admin/tag/")
public class AdminItemController extends AbstractAdminController {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AdminItemController.class);

	@Autowired
	private ItemTagService itemTagService;

	@RequestMapping(value="{tag}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemTagByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="tag") String tag) {

		validateAndAddToken(httpResponse, token);
		
		return new ResponseEntity<ItemTagSummaryResponse>(itemTagService.findSummaryByItemTag(tag),HttpStatus.OK);
	}
	
	@RequestMapping(value="media/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemMediaByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {

		validateAndAddToken(httpResponse, token);
		
		return new ResponseEntity<ItemTagMedia>(itemTagService.findMediaById(id),HttpStatus.OK);
	}
		
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItems(HttpServletResponse httpResponse, @RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {

		validateAndAddToken(httpResponse, token);
				
		return new ResponseEntity<List<ItemTagSummaryResponse>>(itemTagService.findSummaryList(),HttpStatus.OK);
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> save(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @RequestBody ItemTag tag) {

		AdminValidationResponse response = validateAndAddToken(httpResponse, token);
		itemTagService.save(tag, response.getUser());

		// nothing to return here
		return new ResponseEntity<String>((String)null,HttpStatus.OK);
		
	}
	
}
