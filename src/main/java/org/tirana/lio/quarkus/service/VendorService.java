package org.tirana.lio.quarkus.service;

import java.util.ArrayList;
import java.util.List;

import org.tirana.lio.quarkus.data.entity.Vendor;
import org.tirana.lio.quarkus.data.repository.VendorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class VendorService {
    
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    
    public List<Vendor> getAllVendors(){
        return vendorRepository.listAll();
    }
    
    public List<Vendor> getVendorsByName(String name){
        Vendor vendor = vendorRepository.findByName(name);
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor);
        return vendors;
    }
    
    public List<Vendor> getVendorsByEmail(String email){
        Vendor vendor = vendorRepository.findByEmail(email);
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor);
        return vendors;
    }
    
    public List<Vendor> getVendorsByEmailAndName(String email, String name){
        Vendor vendor = vendorRepository.findByEmailAndName(email, name);
        List<Vendor> vendors = new ArrayList<>();
        vendors.add(vendor);
        return vendors;
    }
    
    @Transactional
    public Vendor addVendor(Vendor vendor){
        vendorRepository.persist(vendor);
        return vendor;
    }
    
    public Vendor getVendor(long id){
        Vendor vendor = vendorRepository.findById(id);
        if(vendor == null){
            throw new NotFoundException();
        }
        return vendor;
    }
    
    @Transactional
    public Vendor updateVendor(Vendor vendor){
        Vendor entity = vendorRepository.findById(vendor.getId());
        if (entity == null){
            throw new NotFoundException();
        }
        entity.setAddress(vendor.getAddress());
        entity.setLastName(vendor.getLastName());
        entity.setEmail(vendor.getEmail());
        entity.setFirstName(vendor.getFirstName());
        entity.setPhone(vendor.getPhone());

        this.vendorRepository.persist(vendor);
        return entity;
    }
    
    @Transactional
    public void deleteVendor(long id){
        vendorRepository.deleteById(id);
    }
}
