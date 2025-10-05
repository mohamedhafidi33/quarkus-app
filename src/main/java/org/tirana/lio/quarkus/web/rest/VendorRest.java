package org.tirana.lio.quarkus.web.rest;

import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.tirana.lio.quarkus.data.entity.Vendor;
import org.tirana.lio.quarkus.service.VendorService;

import io.quarkus.runtime.util.StringUtil;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;

public class VendorRest {

    private final VendorService vendorService;

    public VendorRest(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GET
    public List<Vendor> getVendors(@QueryParam("email")String email, @QueryParam("name")String name){
        if(StringUtil.isNullOrEmpty(email) && StringUtil.isNullOrEmpty(name)){
            return vendorService.getAllVendors();
        }else{
            if(!StringUtil.isNullOrEmpty(email) && !StringUtil.isNullOrEmpty(name)){
                return vendorService.getVendorsByEmailAndName(email, name);
            }else if(!StringUtil.isNullOrEmpty(email)){
               return vendorService.getVendorsByEmail(email);
            }else{
               return vendorService.getVendorsByName(name);
            }
        }
    }

    @POST
    @ResponseStatus(201)
    public Vendor addVendor(Vendor vendor){
        return vendorService.addVendor(vendor);
    }

    @GET
    @Path("/{id}")
    public Vendor getVendor(@RestPath("id")long id){
        return vendorService.getVendor(id);
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(204)
    public void updateVendor(@RestPath("id")long id, Vendor vendor){
        if (id != vendor.getId()){
            throw new WebApplicationException(400);
        }
        vendorService.updateVendor(vendor);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(205)
    public void deleteVendor(@RestPath("id")long id){
        vendorService.deleteVendor(id);
    }
}
