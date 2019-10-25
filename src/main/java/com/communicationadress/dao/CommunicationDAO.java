package com.communicationadress.dao;

import com.communicationadress.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommunicationDAO {

    ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException;
    Person getCommunicationAddress(int id) throws SQLException;
}
