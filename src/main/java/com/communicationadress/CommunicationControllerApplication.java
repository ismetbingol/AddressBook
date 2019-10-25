package com.communicationadress;

import com.communicationadress.controller.CommunicationController;
import com.communicationadress.dao.Impl.CommunicationDAOImpl;
import com.communicationadress.service.impl.CommunicationServiceImpl;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/com/communicationadress")
public class CommunicationControllerApplication extends Application {
    private Set<Object> controller = new HashSet<Object>();

    public CommunicationControllerApplication() {
        controller.add(new CommunicationController(new CommunicationServiceImpl(new CommunicationDAOImpl())));

    }

    @Override
    public Set<Object> getSingletons() {
        return controller;
    }
}
