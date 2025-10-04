package org.tirana.lio.quarkus.data.repository;

import org.tirana.lio.quarkus.data.entity.Service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

	@Transactional
	public void save(Service service) {
		persist(service);
	}
}
