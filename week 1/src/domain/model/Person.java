package domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Role rol;

	public Person(String userid, String email, String password, String firstName, String lastName, Role role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}
	public Person(String userid, String email, String password, String firstName, String lastName){
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public void setRole(Role role) {
		if (role == null){
			throw new DomainException("Geef een rol mee.");
		}
		this.rol = role;
	}

	public Role getRole(){
		return this.rol;
	}

	public Person() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new DomainException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new DomainException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new DomainException("Email not valid");
		}
		this.email = email;
	}

	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		String passCompare = hashPassword(password);
		return this.password.equals(passCompare);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new DomainException("No password given");
		}
		this.password = password;
	}

	public void setPasswordHashed(String password) {
        if (password == null || password.trim().isEmpty()){
        	throw new DomainException("No password given.");
		}
		this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");

            crypt.reset();

            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);

            byte[] digest = crypt.digest();

            BigInteger digestAsBigInteger = new BigInteger(1, digest);

            return digestAsBigInteger.toString(16);

        }catch (Exception e){
            throw new DomainException(e.getMessage(), e);
        }
    }


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new DomainException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new DomainException("No last name given");
		}
		this.lastName = lastName;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}

}
