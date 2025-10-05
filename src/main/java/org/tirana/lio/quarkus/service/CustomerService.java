package org.tirana.lio.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import org.tirana.lio.quarkus.data.entity.Customer;
import org.tirana.lio.quarkus.data.repository.CustomerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.listAll();
	}

	public List<Customer> getCustomersByEmail(String email) {
		Customer customer = customerRepository.findByEmail(email);
		List<Customer> customers = new ArrayList<>();
		customers.add(customer);
		return customers;
	}

	public Customer addCustomer(Customer customer) {
		this.customerRepository.save(customer);
		return customer;
	}

	public Customer getCustomer(long id) {
		Customer customer = this.customerRepository.findById(id);
		if (customer == null) {
			throw new NotFoundException();
		}
		return customer;
	}

	public Customer updateCustomer(Customer customer) {
		Customer entity = this.customerRepository.findById(customer.getId());
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.setAddress(customer.getAddress());
		entity.setEmail(customer.getEmail());
		entity.setName(customer.getName());
		entity.setPhone(customer.getPhone());
		customerRepository.save(entity);
		return entity;
	}

	public void deleteCustomer(long id) {
		customerRepository.delete(id);
	}
}
