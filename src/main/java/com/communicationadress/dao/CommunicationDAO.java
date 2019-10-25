package com.communicationadress.dao;

import com.communicationadress.pojo.Communication;
import com.communicationadress.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommunicationDAO {

    ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException;

    Person getPersonCommunicationAddress(int id) throws SQLException;

    void update(int personId, Communication communication) throws SQLException;

    void delete(int id) throws SQLException;
}
