package com.addressbook.dao;

import pojo.AddressBook;

import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {

     List<AddressBook> getAllAddress() throws SQLException;
     AddressBook find(int id ) throws SQLException;
     void update(AddressBook addressBook);
     void delete(AddressBook addressBook) throws SQLException;
}
