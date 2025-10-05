package org.tirana.lio.quarkus.web.graphql;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.tirana.lio.quarkus.data.entity.Service;
import org.tirana.lio.quarkus.service.ServiceService;
import org.tirana.lio.quarkus.web.graphql.input.ServiceInput;

@GraphQLApi
public class ServiceResource {
	private final ServiceService service;

	public ServiceResource(ServiceService service) {
		this.service = service;
	}

	@Query("allServices")
	@Description("Gets all services available in the system.")
	public List<Service> getAllServices() {
		return service.getAllServices();
	}

	@Mutation("addService")
	@Description("Adds service to the system.")
	public Service addService(ServiceInput service) {
		return this.service.addService(service.getEntity());
	}
	
	@Query("getService")
	  @Description("Gets an individual service by ID")
	  public Service getService(@Name("id")long id){
	    return this.service.getService(id);
	  }

	  @Mutation("updateService")
	  @Description("Updates an individual service")
	  public Service updateService(Service service){
	    return this.service.updateService(service);
	  }

	  @Mutation("deleteService")
	  @Description("Deletes an individual service")
	  public Service deleteService(@Name("id")long id){
	    Service service = this.service.getService(id);
	    this.service.deleteService(id);
	    return service;
	  }
}
