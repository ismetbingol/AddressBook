package com.communicationadress.service.impl;

import com.communicationadress.pojo.Communication;
import com.communicationadress.service.CommunicationService;
import com.communicationadress.dao.CommunicationDAO;
import com.communicationadress.pojo.Person;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommunicationServiceImpl implements CommunicationService {

    CommunicationDAO communicationDAO;

    public CommunicationServiceImpl(CommunicationDAO communicationDAO) {
        this.communicationDAO = communicationDAO;
    }

    public ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException {
        return communicationDAO.getAllPersonCommunicationAddress();
    }

    public Person getCommunicationAddress(int id) throws SQLException {
        return communicationDAO.getPersonCommunicationAddress(id);
    }

    public void update(int personId, Communication communication) throws SQLException {

        communicationDAO.update(personId, communication);
    }

    public void delete(int id) throws SQLException {

        communicationDAO.delete(id);
    }
}
