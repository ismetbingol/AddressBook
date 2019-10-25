package com.communicationadress.service;

import com.communicationadress.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CommunicationService {

    ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException;
    Person getCommunicationAddress(int id) throws SQLException;

}
