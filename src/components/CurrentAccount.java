package components;

public class CurrentAccount extends Accounts {  			//1.2.2 Creation of the Current Account class

	public CurrentAccount(String label, int balance, int accountNumber, Clients clients) {
		super(label, balance, accountNumber, clients);
	}
	
}
