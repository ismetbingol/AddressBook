package com.addressbook.service.impl;

import com.addressbook.dao.AddressDAO;
import com.addressbook.service.AddressService;
import com.addressbook.pojo.AddressBook;

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

    public void update(AddressBook addressBook, int id) throws SQLException {
        addressDAO.update(addressBook, id);
    }

    public void delete(int id) throws SQLException {
        addressDAO.delete(id);
    }
}
