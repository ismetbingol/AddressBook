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


    private static DataBaseConnection dataBaseConnection = new DataBaseConnection();
    private Connection connection = null;
    private PreparedStatement statement = null;

    private Connection getConnection() {
        return dataBaseConnection.createDataBase();
    }


    public ArrayList<Person> getAllPersonCommunicationAddress() throws SQLException {
        ArrayList<Person> personList = new ArrayList<Person>();
        connection = getConnection();
        String query = "SELECT * FROM Person p LEFT JOIN Communication c ON p.ID = c.person_id";
        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int personId = resultSet.getInt("ID");
            String fullName = resultSet.getString("full_name");
            Date birthDay = resultSet.getDate("birth_date");
            int cid = resultSet.getInt("id");
            CommunicationType communicationType = CommunicationType.valueOf(resultSet.getString("communication_type"));
            String value = resultSet.getString("address_value");
            Person person = new Person(personId, fullName, birthDay);
            Communication communications = new Communication(cid, personId, communicationType, value);

            if (personList.isEmpty()) {
                addPersonIntoList(person, communications, personList);
            } else {
                boolean personIsInList = false;
                for (Person p : personList) {
                    if (p.getId() == personId) {
                        p.getCommunications().add(communications);
                        personIsInList = true;
                    }
                }
                if (!personIsInList) {
                    addPersonIntoList(person, communications, personList);
                }
            }
        }

        return personList;
    }

    public void addPersonIntoList(Person person, Communication communication, ArrayList<Person> personList) {
        ArrayList<Communication> arrayList = new ArrayList<Communication>();
        arrayList.add(communication);
        person.setCommunications(arrayList);
        personList.add(person);
    }

    public Person getCommunicationAddress(int id) throws SQLException {
        return null;
    }
}
