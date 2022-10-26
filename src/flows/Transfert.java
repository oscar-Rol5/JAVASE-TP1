package flows;

import java.sql.Date;
import java.time.LocalDate;

public class Transfert extends Flow {					//1.3.3 Creation of the Transfert class
	
	private int account_transfering;

	public Transfert(String comment, String identifier, double amount, int account_transfering, int target_account_number, boolean effect, LocalDate date_flow) {
		super(comment, identifier, amount, target_account_number, effect, date_flow);

	}

	
	
}
