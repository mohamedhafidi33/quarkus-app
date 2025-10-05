package org.tirana.lio.quarkus.web.graphql.input;

import java.math.BigDecimal;

import org.tirana.lio.quarkus.data.entity.Service;

public class ServiceInput {
	private String name;
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Service getEntity() {
		Service service = new Service();
		service.setName(this.name);
		service.setPrice(this.price);
		return service;
	}
}
