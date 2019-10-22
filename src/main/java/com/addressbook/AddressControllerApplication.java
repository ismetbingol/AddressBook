package com.addressbook;

import com.addressbook.controller.AddressController;
import com.addressbook.dao.impl.AddressDAOImpl;
import com.addressbook.service.impl.AddressServiceImpl;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/com/addressbook")
public class AddressControllerApplication extends Application {
    private Set<Object> controller = new HashSet<Object>();

    public AddressControllerApplication() {
        // Register our controllers
        controller.add(new AddressController(new AddressServiceImpl(new AddressDAOImpl())));
    }

    @Override
    public Set<Object> getSingletons() {
        return controller;
    }
}
