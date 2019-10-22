package com.addressbook.service.impl;

import pojo.AddressBook;
import com.addressbook.dao.AddressDAO;
import com.addressbook.service.AddressService;


import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {



    private AddressDAO addressDAO;

    public AddressServiceImpl(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public List<AddressBook> getAllAddress() throws SQLException {
       return addressDAO.getAllAddress();
    }

    public AddressBook find(int id) throws SQLException {
       return addressDAO.find(id);
    }

    public void update(AddressBook addressBook) {
        addressDAO.update(addressBook);
    }

    public void delete(AddressBook addressBook) throws SQLException {
        addressDAO.delete(addressBook);
    }
}
