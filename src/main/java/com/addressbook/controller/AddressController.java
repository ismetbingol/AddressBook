package com.addressbook.controller;

import com.addressbook.pojo.AddressBook;
import com.addressbook.service.AddressService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/addressBook")
public class AddressController {


    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GET
    @Path("/getAllAddress")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddress() throws SQLException {
        List<AddressBook> addressBookList = addressService.getAllAddress();
        if (!addressBookList.isEmpty()) {

            return Response.ok(addressBookList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/find/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) throws SQLException {
        AddressBook addressBook = addressService.find(id);
        return Response.ok(addressBook).build();
    }

    @Path("/delete/{id}")
    @DELETE
    public Response delete(@PathParam("id") int id) throws SQLException {
        addressService.delete(id);
        return Response.ok().entity("Address is deleted successfully").build();
    }

    @Path("/update/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@FormParam("FullName") String fullName, @FormParam("Address") String address, @FormParam("PhoneNumber") String phoneNumber, @PathParam("id") int id) throws SQLException {

        AddressBook addressBook = new AddressBook();
        addressBook.setFullName(fullName);
        addressBook.setAddress(address);
        addressBook.setPhoneNumber(phoneNumber);
        addressService.update(addressBook, id);
        return Response.ok().entity("Address is updated successfully").build();

    }

}