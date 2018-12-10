package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDb {
    Person get(String personId);

    List<Person> getAll();

    List<Person> getAllSorted(String sort);


    void add(Person person);

    void update(Person person);

    void delete(String personId);

    int getNumberOfPersons();

    Person getUserIfAuthenticated(String userid, String password);
}
