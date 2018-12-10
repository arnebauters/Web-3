package test.view;

import static org.junit.Assert.*;

import domain.model.Person;
import domain.model.ShopService;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;


public class ShopServiceTest {
    private Properties properties = new Properties();
	private static ShopService service;
	private Person person;
	private String userid;
	private String PASSWORD = "1234";

	@Before
	public void setUp() {
        properties.setProperty("user", "local_r0703015");
        properties.setProperty("password", "V8;5tsraYU;zXehB");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode", "prefer");
        properties.setProperty("currentSchema", "r0703015");
        properties.setProperty("url", "jdbc:postgresql://databanken.ucll.be:51819/2TX37");
        service= new ShopService(properties);
		userid = generateRandomUseridInOrderToRunTestMoreThanOnce("sinterklaas");
		person = new Person(userid, "klaas@klaas.be", PASSWORD, "Klaas", "Claesens" );
		person.setPasswordHashed(PASSWORD);
	}

	@Test
	public void getPerson_should_return_the_person_if_registered() {
		service.addPerson(person);

		Person personRetrieved = service.getPerson(userid);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserid());
		assertEquals(person.getEmail(), personRetrieved.getEmail());
		assertEquals(person.getFirstName(), personRetrieved.getFirstName());
		assertEquals(person.getLastName(),personRetrieved.getLastName());
	}

	@Test
	public void getPerson_should_return_null_if_person_not_registered() {
		service.addPerson(person);
		
		Person personRetrieved = service.getPerson("Unknown");

		assertNull(personRetrieved);
	}

	@Test
	public void getUserIfAuthenticated_should_return_the_person_if_registered_and_correct_password() {
		service.addPerson(person);

		Person personRetrieved = service.getUserIfAuthenticated(userid, PASSWORD);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserid());
	}

	@Test
	public void getUserIfAuthenticated_should_return_null_if_person_not_registered() {
		service.addPerson(person);
		
		Person personRetrieved = service.getUserIfAuthenticated("Unknown", PASSWORD);

		assertNull(personRetrieved);
	}

	@Test
	public void getUserIfAuthenticated_should_return_null_if_person_is_registered_but_incorrect_password() {
		service.addPerson(person);
		
		Person personRetrieved = service.getUserIfAuthenticated(userid, "WrongPassword");

		assertNull(personRetrieved);
	}

	private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
		int random = (int) (Math.random() * 1000 + 1);
		return random + component;
	}

}

