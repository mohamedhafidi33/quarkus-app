package org.tirana.lio.quarkus.web.rest;

import java.util.Arrays;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestQuery;
import org.tirana.lio.quarkus.data.entity.Customer;
import org.tirana.lio.quarkus.data.repository.CustomerRepository;

import io.quarkus.runtime.util.StringUtil;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/rest/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerRest {

	private final CustomerRepository customerRepository;

	public CustomerRest(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GET
	public List<Customer> getCustomers(@RestQuery("email") String email) {
		if (StringUtil.isNullOrEmpty(email)) {
			return customerRepository.listAll();
		}
		return Arrays.asList(customerRepository.findByEmail(email));
	}

	@POST
	@ResponseStatus(201)
	public Customer addCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@GET
	@Path("/{id}")
	public Customer getCustomer(@PathParam("id") Long id) {
		Customer customer = customerRepository.findById(id);
		if (customer == null) {
			throw new NotFoundException();
		}
		return customer;
	}

	@PUT
	@ResponseStatus(204)
	@Path("/{id}")
	public void updateCustomer(@PathParam("id") Long id, Customer customer) {
		Customer entity = customerRepository.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		if (id != entity.getId()) {
			throw new BadRequestException();
		}
		customerRepository.save(customer);
	}

	@DELETE
	@ResponseStatus(205)
	@Path("/{id}")
	public void deleteCustomer(@PathParam("id") Long id) {
		Customer entity = customerRepository.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}
		customerRepository.delete(id);
	}
}
