package com.communicationadress.controller;

import com.addressbook.pojo.AddressBook;
import com.communicationadress.pojo.Person;
import com.communicationadress.service.CommunicationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


}
