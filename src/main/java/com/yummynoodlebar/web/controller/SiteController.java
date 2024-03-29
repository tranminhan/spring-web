package com.yummynoodlebar.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yummynoodlebar.core.services.MenuService;
import com.yummynoodlebar.events.menu.AllMenuItemsEvent;
import com.yummynoodlebar.events.menu.MenuItemDetails;
import com.yummynoodlebar.events.menu.RequestAllMenuItemsEvent;

@Controller
@RequestMapping("/")
public class SiteController {

	private static final Logger LOG = LoggerFactory
			.getLogger(SiteController.class);

	@Autowired
	MenuService menuService;

	@RequestMapping
	@ResponseBody
	public String getCurrentMenu() {
		LOG.debug("Yummy Menu directly to ResponseBody");
		return prettyPrint(menuService
				.requestAllMenuItems(new RequestAllMenuItemsEvent()));
	}

	private String prettyPrint(AllMenuItemsEvent requestAllMenuItems) {
		StringBuilder stringBuilder = new StringBuilder();

		String delim = "";
		for (MenuItemDetails menuItemDetails : requestAllMenuItems
				.getMenuItemDetails()) {

			stringBuilder.append(delim).append(menuItemDetails.getName());
			delim = ",";
		}

		return stringBuilder.toString();
	}

}
