package org.tirana.lio.quarkus.data.repository;

import org.tirana.lio.quarkus.data.entity.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer>{
	
	@Transactional
	public void save(Customer customer) {
		persist(customer);
	}
	
	public Customer findByEmail(String email) {
		return find("email", email).firstResult();
	}

}
