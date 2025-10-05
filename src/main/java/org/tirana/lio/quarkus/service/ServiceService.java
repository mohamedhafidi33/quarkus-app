package org.tirana.lio.quarkus.service;

import java.util.List;

import org.tirana.lio.quarkus.data.entity.Service;
import org.tirana.lio.quarkus.data.repository.ServiceRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ServiceService {

	private final ServiceRepository serviceRepository;

	public ServiceService(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	public List<Service> getAllServices() {
		return serviceRepository.listAll();
	}

	@Transactional
	public Service addService(Service service) {
		serviceRepository.persist(service);
		return service;
	}

	public Service getService(long id) {
		Service service = serviceRepository.findById(id);
		if (service == null) {
			throw new NotFoundException();
		}
		return service;
	}

	@Transactional
	public Service updateService(Service service) {

		Service entity = serviceRepository.findById(service.getId());
		if (entity == null) {
			throw new NotFoundException();
		}
		entity.setPrice(service.getPrice());
		entity.setName(service.getName());
		serviceRepository.persist(entity);
		return entity;
	}

	@Transactional
	public void deleteService(long id) {
		serviceRepository.deleteById(id);
	}
}