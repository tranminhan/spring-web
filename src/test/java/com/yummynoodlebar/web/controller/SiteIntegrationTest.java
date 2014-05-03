package com.yummynoodlebar.web.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.yummynoodlebar.core.services.MenuService;
import com.yummynoodlebar.events.menu.RequestAllMenuItemsEvent;
import com.yummynoodlebar.web.controller.fixture.WebDataFixture;

public class SiteIntegrationTest {

	final String RESPONSE_BODY = "Yummy Noodles,Special Yummy Noodles,Low cal Yummy Noodles";

	MockMvc mockMvc;

	@InjectMocks
	SiteController siteController;

	@Mock
	MenuService menuService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(siteController).build();
		when(
				menuService
						.requestAllMenuItems(any(RequestAllMenuItemsEvent.class)))
				.thenReturn(WebDataFixture.allMenuItems());
	}

	@Test
	public void thatTextReturned() throws Exception {
		mockMvc.perform(get("/")).andDo(print())
				.andExpect(content().string(RESPONSE_BODY));
	}

}
