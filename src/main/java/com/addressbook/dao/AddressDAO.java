package com.addressbook.dao;

import com.addressbook.pojo.AddressBook;

import java.sql.SQLException;
import java.util.List;

public interface AddressDAO {

    List<AddressBook> getAllAddress() throws SQLException;

    AddressBook find(int id) throws SQLException;

    void update(AddressBook addressBook, int id) throws SQLException;

    void delete(int id) throws SQLException;
}
