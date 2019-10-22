package com.addressbook.dao.impl;

import pojo.AddressBook;
import com.addressbook.config.DataBaseConnection;
import com.addressbook.dao.AddressDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {

    private static DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = null;
    private PreparedStatement statement = null;

    public List<AddressBook> getAllAddress() throws SQLException {
        List<AddressBook> addressBookList = new ArrayList<AddressBook>();
        connection = getConnection();
        String query = "SELECT * FROM Address;";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            int id = resultSet.getInt("ID");
            String fulName = resultSet.getString("FullName");
            String address = resultSet.getString("Address");
            String phoneNumber = resultSet.getString("PhoneNumber");
            addressBookList.add(new AddressBook(id, fulName, address, phoneNumber));
        }

        return addressBookList;
    }

    public AddressBook find(int id) throws SQLException {
        connection = getConnection();
        String query = "SELECT * FROM Address WHERE ID =?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        AddressBook addressBook = new AddressBook();
        while (resultSet.next()) {
            int personId = resultSet.getInt("ID");
            String fulName = resultSet.getString("FullName");
            String address = resultSet.getString("Address");
            String phoneNumber = resultSet.getString("PhoneNumber");
            addressBook = new AddressBook(personId, fulName, address, phoneNumber);
        }
        return addressBook;
    }

    public void update(AddressBook addressBook) {


    }

    public void delete(int id) throws SQLException {
        connection = getConnection();
        String query = "DELETE FROM Address WHERE ID=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();

    }


    private Connection getConnection() {
        return dataBaseConnection.createDataBase();
    }
}
