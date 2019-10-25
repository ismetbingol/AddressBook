package com.communicationadress.service;

import com.communicationadress.pojo.Communication;
import com.communicationadress.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommunicationService {

    ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException;

    Person getCommunicationAddress(int id) throws SQLException;

    void update(int personId, Communication communication) throws SQLException;

    void delete(int id) throws SQLException;

}
