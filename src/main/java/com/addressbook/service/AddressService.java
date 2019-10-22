package com.addressbook.service;

import pojo.AddressBook;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {
    List<AddressBook> getAllAddress() throws SQLException;

    AddressBook find(int id) throws SQLException;

    void update(AddressBook addressBook);

    void delete(int id) throws SQLException;

}
