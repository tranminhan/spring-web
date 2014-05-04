package com.yummynoodlebar.config;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfig.class, CoreConfig.class,
		WebConfig.class })
public class WebDomainIntegrationTest {

	private static final String STANDARD = "Yummy Noodles";
	private static final String SPECIAL = "Special Yummy Noodles";
	private static final String LOW_CAL = "Low cal Yummy Noodles";

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void thatTextReturned() throws Exception {
		mockMvc.perform(get("/")).andDo(print())
				.andExpect(content().string(containsString(STANDARD)))
				.andExpect(content().string(containsString(SPECIAL)))
				.andExpect(content().string(containsString(LOW_CAL)));
	}

}
