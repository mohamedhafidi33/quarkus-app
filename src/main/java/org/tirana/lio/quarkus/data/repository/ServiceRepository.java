package org.tirana.lio.quarkus.data.repository;

import java.util.List;

import org.tirana.lio.quarkus.data.entity.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ServiceRepository {

	private final EntityManager em;

	public ServiceRepository(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void saveService(Service service) {
		if (service.getId() == null) {
			em.persist(service);
		} else {
			em.merge(service);
		}
	}

	public List<Service> getAllServices() {
		return em.createQuery("select service from Service service", Service.class).getResultList();
	}

}
