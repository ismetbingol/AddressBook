package com.addressbook.service;

import pojo.AddressBook;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {
    public List<AddressBook> getAllAddress() throws SQLException;
    public AddressBook find(int id ) throws SQLException;
    public void update(AddressBook addressBook);
    public void delete(AddressBook addressBook) throws SQLException;

}
