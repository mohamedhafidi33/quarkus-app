package org.tirana.lio.quarkus.web.rest;

import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.tirana.lio.quarkus.data.entity.Service;
import org.tirana.lio.quarkus.service.ServiceService;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;

public class ServiceRest {

	private final ServiceService serviceService;

	public ServiceRest(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@GET
	public List<Service> getAllServices() {
		return serviceService.getAllServices();
	}

	@POST
	@ResponseStatus(201)
	public Service addService(Service service) {
		return serviceService.addService(service);
	}

	@GET
	@Path("/{id}")
	public Service getService(@RestPath("id") long id) {
		return serviceService.getService(id);
	}

	@PUT
	@Path("/{id}")
	@ResponseStatus(204)
	public void updateService(@RestPath("id") long id, Service service) {
		if (id != service.getId()) {
			throw new WebApplicationException(400);
		}
		serviceService.updateService(service);
	}

	@DELETE
	@Path("/{id}")
	@ResponseStatus(205)
	public void deleteService(@RestPath("id") long id) {
		serviceService.deleteService(id);
	}
}
