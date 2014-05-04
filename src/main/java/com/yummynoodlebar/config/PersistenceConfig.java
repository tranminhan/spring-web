package com.yummynoodlebar.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yummynoodlebar.persistence.domain.MenuItem;
import com.yummynoodlebar.persistence.domain.Order;
import com.yummynoodlebar.persistence.repository.MenuItemMemoryRepository;
import com.yummynoodlebar.persistence.repository.MenuItemRepository;
import com.yummynoodlebar.persistence.repository.OrderStatusMemoryRepository;
import com.yummynoodlebar.persistence.repository.OrderStatusRepository;
import com.yummynoodlebar.persistence.repository.OrdersMemoryRepository;
import com.yummynoodlebar.persistence.repository.OrdersRepository;
import com.yummynoodlebar.persistence.services.MenuPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.MenuPersistenceService;
import com.yummynoodlebar.persistence.services.OrderPersistenceEventHandler;
import com.yummynoodlebar.persistence.services.OrderPersistenceService;

@Configuration
public class PersistenceConfig {

	@Bean
	public OrdersRepository orderRepo() {
		final Map<UUID, Order> orders = new HashMap<UUID, Order>();
		return new OrdersMemoryRepository(orders);
	}

	@Bean
	public OrderStatusRepository orderStatusRepo() {
		return new OrderStatusMemoryRepository();
	}

	@Bean
	public OrderPersistenceService orderPersistenceService() {
		return new OrderPersistenceEventHandler(orderRepo(), orderStatusRepo());
	}

	@Bean
	public MenuItemRepository menuItemRepo() {
		return new MenuItemMemoryRepository(defaultMenu());
	}

	@Bean
	public MenuPersistenceService menuPersistenceService() {
		return new MenuPersistenceEventHandler(menuItemRepo());
	}

	private Map<String, MenuItem> defaultMenu() {
		Map<String, MenuItem> items = new HashMap<String, MenuItem>();
		items.put("YM1",
				menuItem("YM1", new BigDecimal("1.99"), 11, "Yummy Noodles"));
		items.put(
				"YM2",
				menuItem("YM2", new BigDecimal("2.99"), 12,
						"Special Yummy Noodles"));
		items.put(
				"YM3",
				menuItem("YM3", new BigDecimal("3.99"), 13,
						"Low cal Yummy Noodles"));
		return items;
	}

	private MenuItem menuItem(String id, BigDecimal cost,
			int minutesToPrepapre, String name) {
		MenuItem item = new MenuItem();
		item.setId(id);
		item.setCost(cost);
		item.setMinutesToPrepare(minutesToPrepapre);
		item.setName(name);
		return item;
	}
}
