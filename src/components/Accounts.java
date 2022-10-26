package components;

import javax.xml.bind.annotation.*;

import flows.Flow;

@XmlRootElement
public abstract class Accounts { 					//1.2.1 Creation of the Accounts abstract class

	protected String label;
	protected double balance;
	protected int accountNumber;
	protected Clients clients;
	
	protected Accounts(String label, double balance, int accountNumber, Clients clients) {
		super();
		this.label = label;
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.clients = clients;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance, Flow flow) {
		this.balance = balance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Clients getClients() {
		return clients;
	}
	public void setClients(Clients clients) {
		this.clients = clients;
	}
	
	@Override
	public String toString() {
		return "Accounts [label=" + label + ", balance=" + balance + ", accountNumber=" + accountNumber + ", clients="
				+ clients + "]";
	}
	
}
