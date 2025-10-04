package org.tirana.lio.quarkus;

import java.math.BigDecimal;

import org.tirana.lio.quarkus.data.entity.Customer;
import org.tirana.lio.quarkus.data.entity.Service;
import org.tirana.lio.quarkus.data.entity.Vendor;
import org.tirana.lio.quarkus.data.repository.CustomerRepository;
import org.tirana.lio.quarkus.data.repository.ServiceRepository;
import org.tirana.lio.quarkus.data.repository.VendorRepository;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	private final ServiceRepository serviceRepository;

	private final VendorRepository vendorRepository;

	private final CustomerRepository customerRepository;

	public QuarkusApp(ServiceRepository serviceRepository, VendorRepository vendorRepository,
			CustomerRepository customerRepository) {
		this.serviceRepository = serviceRepository;
		this.vendorRepository = vendorRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	@ActivateRequestContext
	public int run(String... args) throws Exception {
		for (int i = 1; i < 6; i++) {
			Service service = new Service();
			service.setName("SE" + i);
			service.setPrice(new BigDecimal(40 * i));
			serviceRepository.save(service);
			;
		}
		serviceRepository.listAll().forEach(System.out::println);
		System.out.println(serviceRepository.findById(2L));

		Vendor vendor = new Vendor();
		vendor.setFirstName("Rafael");
		vendor.setAddress("Rafael@gmail.com");
		vendor.setPhone("+49***23");
		vendorRepository.save(vendor);
		vendorRepository.listAll().forEach(System.out::println);

		Customer customer = new Customer();
		customer.setName("Nana");
		customer.setEmail("nana@yahoo.com");
		customerRepository.save(customer);
		System.out.println("Customer by Email:" +customerRepository.findByEmail("nana@yahoo.com"));
		return 0;
	}

}
