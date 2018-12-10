package domain.model;

import domain.db.FietsDb;
import domain.db.PersonDb;
import domain.db.databaseFiets;
import domain.db.databasePerson;

import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private FietsDb fietsDb;

    public ShopService(Properties properties) {
        this.personDb = new databasePerson(properties);
        this.fietsDb = new databaseFiets(properties);
    }

    public Person getPerson(String personId) {
        return this.personDb.get(personId);
    }

    public List<Person> getPersons() {
        return this.personDb.getAll();
    }

    public void addPerson(Person person) {
        this.personDb.add(person);
    }

    public void updatePersons(Person person) {
        this.personDb.update(person);
    }

    public void deletePerson(String id) {
        this.personDb.delete(id);
    }

    private PersonDb getPersonDb() {
        return personDb;
    }

    public Fiets getFiets(int productId){
        return this.fietsDb.getFiets(productId);
    }
    public Fiets getDuurste(){
        return this.fietsDb.duurste();
    }

    public List<Fiets> getProducts(){
        return this.fietsDb.fietsen();
    }

    public void addProduct(Fiets fiets){
        this.fietsDb.voegFietsToe(fiets);
    }
    public void updateFiets(Fiets fiets) {
        this.fietsDb.update(fiets);
    }

    public void deleteFiets(int id) {
        this.fietsDb.verwijder(id);
    }

    private FietsDb getFietsDb() {
        return fietsDb;
    }

    public List<Person> getPersonsSorted(String sort){return personDb.getAllSorted(sort);}

    public Person getUserIfAuthenticated(String userId, String password){return personDb.getUserIfAuthenticated(userId, password);}
}
