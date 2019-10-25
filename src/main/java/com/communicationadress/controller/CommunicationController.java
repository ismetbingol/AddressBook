package com.communicationadress.controller;

import com.communicationadress.pojo.Communication;
import com.communicationadress.pojo.CommunicationType;
import com.communicationadress.pojo.Person;
import com.communicationadress.service.CommunicationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/addressBook/v2")
public class CommunicationController {

    CommunicationService communicationService;

    public CommunicationController(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @GET
    @Path("/getAllAddress")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonCommunicationAddress() throws SQLException {
        ArrayList<Person> personCommunicationAddressList = communicationService.getAllPersonCommunicationAddress();
        if (!personCommunicationAddressList.isEmpty()) {

            return Response.ok(personCommunicationAddressList).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/getPerson/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") int id) throws SQLException {
        Person person = communicationService.getCommunicationAddress(id);
        return Response.ok(person).build();
    }

    @Path("/deletePerson/{id}")
    @DELETE
    public Response delete(@PathParam("id") int id) throws SQLException {
        communicationService.delete(id);
        return Response.ok().entity("Person is deleted successfully").build();
    }

    @Path("/updatePersonInfo/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@FormParam("id") int cid, @FormParam("communication_Type") CommunicationType communicationType, @FormParam("address_value") String value, @PathParam("id") int personId) throws SQLException {
        Communication communication = new Communication(cid, personId, communicationType, value);
        communicationService.update(personId, communication);
        return Response.ok().entity("Address value is updated successfully").build();

    }

}
