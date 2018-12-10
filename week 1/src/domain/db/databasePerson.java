package domain.db;

import domain.model.Person;
import domain.model.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class databasePerson implements PersonDb {
    private Properties properties;
    private String url;

    public databasePerson(Properties properties) {
        this.properties = properties;
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch (ClassNotFoundException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    public Person get(String personId) {
        Person person = null;
        String sql = "SELECT * FROM person WHERE userid = ?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                if (userid == null){
                    return null;
                }
                String email = result.getString("email");
                String password = result.getString("password");
                String firstName = result.getString("firstname");
                String lastname = result.getString("lastname");
                String role = result.getString("role");
                Role rol = Role.valueOf(role);
                person = new Person(userid, email, password, firstName, lastname, rol);

            }
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            makePerson(persons, result);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    @Override
    public List<Person> getAllSorted(String sort) {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM person order by " + sort.toLowerCase();
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            makePerson(persons, result);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

    private void makePerson(List<Person> persons, ResultSet result) throws SQLException {
        while (result.next()) {
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstName = result.getString("firstname");
            String lastname = result.getString("lastname");
            Role role = Role.valueOf(result.getString("role"));
            Person person = new Person(userid, email, password, firstName, lastname, role );
            persons.add(person);
        }
    }

    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = "INSERT INTO person (userid, email, password, firstname, lastname, role) VALUES (?,?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getUserid());
            statement.setString(2, person.getEmail());
            statement.setString(3, person.getPassword());
            statement.setString(4, person.getFirstName());
            statement.setString(5, person.getLastName());
            statement.setString(6, person.getRole().toString());
            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null) {
            throw new DbException("Nothing to update.");
        }
        String sql = "UPDATE person SET email = ?, password = ?,firstname = ?, lastname = ?, role = ? WHERE userid = ?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getEmail());
            statement.setString(2,person.getPassword());
            statement.setString(3, person.getFirstName());
            statement.setString(4, person.getLastName());
            statement.setString(5, person.getRole().toString());
            statement.setString(6, person.getUserid());

            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Override
    public void delete(String personId) {
        if (personId == null) {
            throw new DbException("Nothing to delete.");
        }
        String sql = "DELETE FROM person WHERE userid = ?";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personId);
            statement.execute();
        } catch (Exception e) {
            throw new DbException(e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        int number = 0;
        String sql = "SELECT count(userid) FROM person";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            number = Integer.parseInt(result.getString("count"));
        } catch (Exception e) {
            throw new DbException(e);
        }
        return number;
    }

    @Override
    public Person getUserIfAuthenticated(String userid, String password) {
        try {
            Person person = get(userid);
            if (person.isCorrectPassword(password)){
                return person;
            }
            else {
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

}
