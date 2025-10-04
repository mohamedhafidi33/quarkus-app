package org.tirana.lio.quarkus.data.repository;

import org.tirana.lio.quarkus.data.entity.Vendor;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VendorRepository implements PanacheRepository<Vendor>{
	
	@Transactional
	public void save(Vendor vendor) {
		persist(vendor);
	}
	
	public Vendor findByEmail(String email) {
		return find("email", email).firstResult();
	}
	
	public Vendor findByName(String name) {
		return find("lowaer(name)", name.toLowerCase()).firstResult();
	}
}
