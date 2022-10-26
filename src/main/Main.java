package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import components.Accounts;
import components.Clients;
import components.CurrentAccount;
import components.SavingsAccount;
import flows.Credit;
import flows.Debit;
import flows.Flow;
import flows.Transfert;

public class Main {					//1.1.2 Creation of the main class

	static ArrayList<Clients> clientCollection = new ArrayList<Clients>();
	
	static ArrayList<Accounts> accountsCollection = new ArrayList<Accounts>();
	
	static ArrayList<Flow> flowCollection = new ArrayList<Flow>();
	
	static Hashtable hashtable = new Hashtable();
	
	public static Collection loadClient(int num) {
		
		for(int x = 0; x < num; x++ ) {
		
			clientCollection.add(new Clients("name" + x, "firstname" + x, x));
			
		}
		
		return clientCollection;		
		
	}
	
	public static void displayClient() {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("How many clients do you want to create? ");
		
	    int nCli = scanner.nextInt();  // Reads the user input
		
		loadClient(nCli);
				
		clientCollection.stream().forEach((p)-> {
			System.out.println(p.toString()); 
			});		
		
	}
	
	public static Collection<Accounts> loadAccounts(ArrayList<Clients> clientsCollection) {  	//1.2.3 Creation of the table account
				
		for(int i = 0; i < clientsCollection.size(); i++) {
			
			accountsCollection.add(new CurrentAccount("Current Account" + String.valueOf(i), 0, i, clientsCollection.get(i)));
			
		}
		
		for(int o = 0; o < clientsCollection.size(); o++) {
		
			accountsCollection.add(new SavingsAccount("Savings Account" + String.valueOf(o), 0, o, clientsCollection.get(o)));
			
		}
		
		return accountsCollection;
		
	}
	
	public static void displayAccounts() {
		
		loadAccounts(clientCollection);
		
		accountsCollection.stream().forEach((p)-> {
			System.out.println(p.toString()); 
			});	
	
	}
	
	public static Hashtable loadHashtable(ArrayList<Accounts> accountsCollection) {  		//1.3.1 Adaptation of the table of accounts
				
		int cont = 0;
		String key = "ca" + cont;
		
		for(int e = 0; e < accountsCollection.size();  e++) {
			
			hashtable.put(key + cont, accountsCollection.get(e));

			cont ++;
			
			if(e == (accountsCollection.size() / 2) - 1) {
				
				cont = 0;
				key = "sa" + cont;
				
			}
			
		}
		
		return hashtable;
		
	}
	
	public static void displayHashtable() {
		
		loadHashtable(accountsCollection);
		
		//List<Accounts> sortedList = accountsCollection.stream().sorted().collect(Collectors.toList());
		
		Enumeration keys = hashtable.keys();
		Enumeration enumeration = hashtable.elements();
		
		while (enumeration.hasMoreElements()) {
			
			System.out.println("Hashtable Key: " + keys.nextElement() + " || " + enumeration.nextElement());
			
		}
		
	}
	
	public static Collection<Flow> loadFlow(){  				//1.3.4 Creation of the flow array
		
		flowCollection.add(new Debit("Debit", "fl01", 50, 0, true, LocalDate.now().plusDays(2)));
		
		flowCollection.add(new Credit("Credit", "fl02", 100.50, 0, true, LocalDate.now().plusDays(2)));

		flowCollection.add(new Credit("Credit1", "fl03", 1500, 0, true, LocalDate.now().plusDays(2)));
		
		flowCollection.add(new Transfert("Transfer", "fl04", 50, 1, 0, true, LocalDate.now().plusDays(2)));
		
		return flowCollection;
		
	}
	 
	public static void changeBalance(Hashtable hashtable, ArrayList<Flow> flowCollection) {   			//1.3.5 Updating accounts
		
		for(int r = 0; r < flowCollection.size(); r++) {
			
			if(flowCollection.get(r).getComment() == "Debit") {
				
				if(flowCollection.get(r).getTarget_account_number() == 0) {
					
					accountsCollection.get(0).setBalance( - (flowCollection.get(r).getAmount()), flowCollection.get(r));
					
				}
				
			}
			
			if(flowCollection.get(r).getComment() == "Credit") {
				
				for(int g = 0; g < accountsCollection.size(); g++ ) {
										
					if(accountsCollection.get(g).getLabel().charAt(0) == 'C') {
						
						accountsCollection.get(g).setBalance(accountsCollection.get(g).getBalance() + flowCollection.get(r).getAmount(), flowCollection.get(r));
						
					}
					
				}
				
			}
			
			if(flowCollection.get(r).getComment() == "Credit1") {
				
				for(int j = 0; j < accountsCollection.size(); j++ ) {
											
					if(accountsCollection.get(j).getLabel().charAt(0) == 'S') {
						
						accountsCollection.get(j).setBalance(accountsCollection.get(j).getBalance() + flowCollection.get(r).getAmount(), flowCollection.get(r));
						
					}
					
				}
				
			}
			
			if(flowCollection.get(r).getComment() == "Transfer") {
				
				accountsCollection.get(0).setBalance(accountsCollection.get(0).getBalance() - flowCollection.get(r).getAmount(), flowCollection.get(r));
			
				accountsCollection.get(1).setBalance(accountsCollection.get(1).getBalance() + flowCollection.get(r).getAmount(), flowCollection.get(r));
				
			}
			
		}
		
		ArrayList<Double> balances = new ArrayList<Double>();
		
		accountsCollection.stream().forEach((p)-> {balances.add(p.getBalance());});
		
        Predicate<Double> LowerThan0 =  x -> x < 0;
		
        List<Double> collect = balances.stream()
                .filter(LowerThan0)
                .collect(Collectors.toList());

        System.out.println("Number of accounts with negative balance: " + collect.size());
		
        Enumeration keys = hashtable.keys();
		Enumeration enumeration = hashtable.elements();
        
		while (enumeration.hasMoreElements()) {
					
			System.out.println("Hashtable Key: " + keys.nextElement() + " || " + enumeration.nextElement());
					
		}
		
	}
	
	public static void loadJson() throws IOException {
		
		JSONObject json = new JSONObject();
		
		FileWriter file = new FileWriter("C:\\Users\\roliv\\eclipse-workspace\\ORBank\\output.json");
		
		for	(int n = 0; n < flowCollection.size(); n++) {
			
			json.put("Comment", flowCollection.get(n).getComment());
			json.put("Identifier", flowCollection.get(n).getIdentifier());
			json.put("Amount", flowCollection.get(n).getTarget_account_number());
			json.put("Effect", flowCollection.get(n).isEffect());
			json.put("Date", flowCollection.get(n).getDate_flow());
			
			file.write(json.toString());
			
		}
		
		file.close();
		
	}
	
	public static void loadXML() throws ParserConfigurationException, JAXBException {
		
		
        JAXBContext jaxbContext = JAXBContext.newInstance(Accounts.class);
		
        Marshaller marshaller = jaxbContext.createMarshaller();
        
        File file = new File("C:\\Users\\roliv\\eclipse-workspace\\ORBank\\output.xml");
        
		for	(int m = 0; m < accountsCollection.size(); m++) {

			marshaller.marshal(accountsCollection.get(m), file);
			
		}			
        
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, JAXBException {
		
		displayClient();
		
		System.out.println();
		System.out.println(" ---------- Clients ------------");
		System.out.println();
		
		System.out.println();
		System.out.println(" ---------- Accounts ------------");
		System.out.println();
		
		displayAccounts();
		
		System.out.println();
		System.out.println(" ---------- Hashtable ------------");
		System.out.println();
		
		
		displayHashtable();
		
		loadFlow();
		
		System.out.println();
		System.out.println(" ---------- Changed Balances ------------");
		System.out.println();
		
		changeBalance(hashtable, flowCollection);
		
		loadJson();  // You need the json jar to use this function.
		
		//loadXML(); // Couldn't find a proper function to make it work
		
	}

}
