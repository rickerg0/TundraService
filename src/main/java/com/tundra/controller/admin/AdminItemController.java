package com.tundra.controller.admin;

import static com.tundra.security.SecurityConstants.HEADER_SECURITY_TOKEN;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tundra.controller.AbstractController;
import com.tundra.entity.ItemTag;
import com.tundra.entity.ItemTagMedia;
import com.tundra.response.ItemTagSummaryResponse;
import com.tundra.service.admin.AdminItemTagService;

@Controller 
@RequestMapping("/admin/tag/")
public class AdminItemController extends AbstractController {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(AdminItemController.class);

	@Autowired
	private AdminItemTagService itemTagService;

	
	@PreAuthorize("@adminSecurityManager.isValidAdminUser(#httpResponse, #token)")
	@RequestMapping(value="{tag}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemTagByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="tag") String tag) {

		return new ResponseEntity<ItemTagSummaryResponse>(itemTagService.findSummaryByItemTagForUser(tag),HttpStatus.OK);
	}
	
	@PreAuthorize("@adminSecurityManager.isValidAdminUser(#httpResponse, #token)")
	@RequestMapping(value="media/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItemMediaByTagId(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @PathVariable(value="id") Integer id) {

		return new ResponseEntity<ItemTagMedia>(itemTagService.findMediaById(id),HttpStatus.OK);
	}
		
	@PreAuthorize("@adminSecurityManager.isValidAdminUser(#httpResponse, #token)")
	@RequestMapping(value="list", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getItems(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token) {

		return new ResponseEntity<List<ItemTagSummaryResponse>>(itemTagService.findSummaryListForUser(),HttpStatus.OK);
	}
	
	@PreAuthorize("@adminSecurityManager.isValidAdminUser(#httpResponse, #token)")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> update(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @RequestBody ItemTag tag) {

		itemTagService.save(tag);

		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@PreAuthorize("@adminSecurityManager.isValidAdminUser(#httpResponse, #token)")
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="add", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> add(HttpServletResponse httpResponse, 
			@RequestHeader(value=HEADER_SECURITY_TOKEN) String token, @RequestBody ItemTag tag) {

		itemTagService.save(tag);

		return new ResponseEntity(HttpStatus.OK);

	}
}
