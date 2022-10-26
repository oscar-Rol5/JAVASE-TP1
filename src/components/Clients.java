package components;

public class Clients { 					//1.1.1 Creation of the client class

	private String name;
	private String firstname;
	private int client_number;
	
	public Clients(String name, String firstname, int client_number) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.client_number = client_number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public int getClient_number() {
		return client_number;
	}
	public void setClient_number(int client_number) {
		this.client_number = client_number;
	}
	
	@Override
	public String toString() {
		return "Clients [name=" + name + ", firstname=" + firstname + ", client_number=" + client_number + "]";
	}
	
	

}
