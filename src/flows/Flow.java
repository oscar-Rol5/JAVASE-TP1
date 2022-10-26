package flows;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Flow {  				//1.3.2 Creation of the Flow class

	private String comment;
	private String identifier;
	private double amount;
	private int target_account_number;
	private boolean effect;
	private LocalDate date_flow;
	
	public Flow(String comment, String identifier, double amount, int target_account_number, boolean effect,
			LocalDate date_flow) {
		super();
		this.comment = comment;
		this.identifier = identifier;
		this.amount = amount;
		this.target_account_number = target_account_number;
		this.effect = effect;
		this.date_flow = date_flow;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTarget_account_number() {
		return target_account_number;
	}

	public void setTarget_account_number(int target_account_number) {
		this.target_account_number = target_account_number;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public LocalDate getDate_flow() {
		return date_flow;
	}

	public void setDate_flow(LocalDate date_flow) {
		this.date_flow = date_flow;
	}
	
}
