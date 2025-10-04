package org.tirana.lio.quarkus.data.repository;

import org.tirana.lio.quarkus.data.entity.Customer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer>{
	
	@Transactional
	public void save(Customer customer) {
		persistOrUpdate(customer);
	}
	
	@Transactional
	public void delete(Long id) {
		deleteById(id);
	}
	
	public Customer findByEmail(String email) {
		return find("email", email).firstResult();
	}
	
	public void persistOrUpdate(Customer customer) {
	    if (customer.getId() == null) {
	        persist(customer);
	    } else {
	        getEntityManager().merge(customer);
	    }
	}

}
