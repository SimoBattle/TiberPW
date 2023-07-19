package beans;



public class User {
    private final String name, lastname, email;
	private final Boolean maggiorenne, admin;    
    
	public User(String name, String lastname, String email, Boolean maggiorenne, Boolean admin) {
            
        this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.maggiorenne = maggiorenne;
        this.admin = admin;
	}

    public String getName() { return name; }

    public String getLastName() { return lastname; }

    public String getEmail() { return email; }

    public Boolean getMaggiorenne() { return maggiorenne; }

    public Boolean getAdmin() {return admin; }

    
}
