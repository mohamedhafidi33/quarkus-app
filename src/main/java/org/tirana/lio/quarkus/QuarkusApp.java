package org.tirana.lio.quarkus;

import java.math.BigDecimal;

import org.tirana.lio.quarkus.data.entity.Service;
import org.tirana.lio.quarkus.data.repository.ServiceRepository;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	private final ServiceRepository serviceRepository;

	public QuarkusApp(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	@ActivateRequestContext
	public int run(String... args) throws Exception {
		Service service = new Service();
		service.setName("SE1");
		for (int i = 1; i < 6; i++) {
			service.setPrice(new BigDecimal(40 * i));
			serviceRepository.saveService(service);
		}
		this.serviceRepository.getAllServices().forEach(System.out::println);
		return 0;
	}

}
