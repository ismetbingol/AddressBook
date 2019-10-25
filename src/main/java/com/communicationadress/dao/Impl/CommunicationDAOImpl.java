package com.communicationadress.dao.Impl;

import com.communicationadress.dao.CommunicationDAO;
import com.communicationadress.pojo.Communication;
import com.communicationadress.pojo.CommunicationType;
import com.communicationadress.pojo.Person;
import com.config.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class CommunicationDAOImpl implements CommunicationDAO {

    private static final String GET_ALL_PERSON = "SELECT * FROM Person p LEFT JOIN Communication c ON p.ID = c.person_id";
    private static final String GET_PERSON = "SELECT * FROM Person p LEFT JOIN Communication c ON p.ID = c.person_id WHERE p.ID=?";
    private static final String DELETE_PERSON = "DELETE FROM Person p WHERE p.ID=?";
    private static final String UPDATE_PERSON = "UPDATE Person LEFT JOIN Communication ON Person.ID = Communication.person_id SET communication_type =?,address_value=? WHERE Person.ID=?";
    private static DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = null;
    private PreparedStatement statement = null;

    private Connection getConnection() {
        return dataBaseConnection.createDataBase();
    }


    public ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException {
        ArrayList<Person> personList = new ArrayList<Person>();
        connection = getConnection();
        statement = connection.prepareStatement(GET_ALL_PERSON);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Person person = mappingPersonValues(resultSet);
            Communication communication = mappingCommunicationValues(resultSet);
            if (personList.isEmpty()) {
                addCommunicationToPerson(person, communication);
                personList.add(person);

            } else {
                boolean personIsInList = personIsInList(person, personList);
                if (personIsInList) {
                    person.getCommunications().add(communication);
                } else {
                    addCommunicationToPerson(person, communication);
                    personList.add(person);

                }
            }
        }

        return personList;
    }

    public Person getPersonCommunicationAddress(int id) throws SQLException {
        connection = getConnection();
        statement = connection.prepareStatement(GET_PERSON);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Person person = new Person();
        while (resultSet.next()) {
            person = mappingPersonValues(resultSet);
            Communication communication = mappingCommunicationValues(resultSet);
            addCommunicationToPerson(person, communication);
        }

        return person;
    }

    public void update(int personId, Communication communication) throws SQLException {
        connection = getConnection();
        statement = connection.prepareStatement(UPDATE_PERSON);
        statement.setString(1, communication.getCommunicationType().name());
        statement.setString(2, communication.getValue());
        statement.setInt(3, personId);
        statement.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        connection = getConnection();
        statement = connection.prepareStatement(DELETE_PERSON);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void addCommunicationToPerson(Person person, Communication communication) {
        ArrayList<Communication> arrayList = new ArrayList<Communication>();
        arrayList.add(communication);
        person.setCommunications(arrayList);
    }

    public Person mappingPersonValues(ResultSet resultSet) throws SQLException {
        int personId = resultSet.getInt("ID");
        String fullName = resultSet.getString("full_name");
        Date birthDay = resultSet.getDate("birth_date");
        Person person = new Person(personId, fullName, birthDay);
        return person;
    }

    public Communication mappingCommunicationValues(ResultSet resultSet) throws SQLException {
        int communicationId = resultSet.getInt("id");
        int personId = resultSet.getInt("person_id");
        CommunicationType communicationType = CommunicationType.valueOf(resultSet.getString("communication_type"));
        String value = resultSet.getString("address_value");
        Communication communication = new Communication(communicationId, personId, communicationType, value);
        return communication;
    }

    public boolean personIsInList(Person person, ArrayList<Person> personList) {
        for (Person p : personList) {
            if (p.getId() == person.getId()) {
                return true;
            }
        }
        return false;
    }
}
