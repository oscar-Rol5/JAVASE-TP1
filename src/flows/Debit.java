package flows;

import java.sql.Date;
import java.time.LocalDate;

public class Debit extends Flow {    				//1.3.3 Creation of the Debit class

	public Debit(String comment, String identifier, double amount, int target_account_number, boolean effect,
			LocalDate date_flow) {
		super(comment, identifier, amount, target_account_number, effect, date_flow);

	}

}
