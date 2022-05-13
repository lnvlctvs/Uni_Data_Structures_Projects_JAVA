import java.io.*;
import java.util.*;

public class StoreFileRL {

private ArrayList <Service> services = new ArrayList<Service>();
private ArrayList <Contract> contracts = new ArrayList<Contract>();

public void loadFile(String data) {
 File f = null;
 BufferedReader reader = null;
 Service tmpService = null;
 Contract tmpContract = null;
 String line;
	try {
		f = new File(data);
	} 	catch (NullPointerException e) {
		System.err.println("File not found.");
	}
	try {
		reader = new BufferedReader(new FileReader(f));
	} 	catch (FileNotFoundException e) {
		System.err.println("Error opening file!");
	}
	try {
		line = reader.readLine();
		if(line.trim().equalsIgnoreCase("SERVICE LIST")) {
			line = reader.readLine();
			if (line.trim().equals("{")) {
				line = reader.readLine();
				if (line.trim().toLowerCase().startsWith("SERVICE".toLowerCase())) {
					line=reader.readLine();
					while(true) {
					if (line.trim().equals("{")) {
						String serviceName="";
						String type = "";
						double monthlyPrice=0;
						double currentSale=0;
						double timeForStath=0;
						double timeForMob=0;
						int sms=0;
						double upoloipo=0;
						int mb=0;
						while(!line.trim().equals("}")) {
							line=reader.readLine();
							if(line.trim().toLowerCase().startsWith("SERVICE NAME ".toLowerCase()))
								serviceName=line.substring(14).trim();
							if(line.trim().toLowerCase().startsWith("TYPE ".toLowerCase()))
								type=line.substring(6).trim();
							if(line.trim().toLowerCase().startsWith("MONTHLY PRICE ".toLowerCase()))
								monthlyPrice=Double.parseDouble(line.substring(15).trim());
							if(line.trim().toLowerCase().startsWith("CURRENT SALE ".toLowerCase()))
								currentSale=Double.parseDouble(line.substring(14).trim());
							if(line.trim().toLowerCase().startsWith("TIME FOR STATH ".toLowerCase()))
								timeForStath=Double.parseDouble(line.substring(16).trim());
							if(line.trim().toLowerCase().startsWith("TIME FOR MOB ".toLowerCase()))
								timeForMob=Double.parseDouble(line.substring(14).trim());
							if(line.trim().toLowerCase().startsWith("SMS ".toLowerCase()))
								sms=Integer.parseInt(line.substring(5).trim());
							if(line.trim().toLowerCase().startsWith("UPOLOIPO ".toLowerCase()))
								upoloipo=Double.parseDouble(line.substring(10).trim());
							if(line.trim().toLowerCase().startsWith("MB ".toLowerCase()))
								mb=Integer.parseInt(line.substring(4).trim());
						}
						if(serviceName!="" && type!="" && monthlyPrice!=0) {
							switch(type) {
								case "MTalk":
									tmpService = new MTalk(++Service.ID, serviceName, monthlyPrice, currentSale, timeForStath, timeForMob, sms);
									services.add(tmpService);
									break;
								case "MTalkKartSumv":
									tmpService = new MTalkKartSumv(++Service.ID, serviceName, monthlyPrice, currentSale, timeForStath, timeForMob, sms, upoloipo);
									services.add(tmpService);
									break;
								case "MInternet":
									tmpService = new MInternet(++Service.ID, serviceName, monthlyPrice, currentSale, mb);
									services.add(tmpService);
									break;
						}}
						else
							System.out.println("There is an error with the file, information missing.");
					}line=reader.readLine();
					if(line==null)
						break;
					}
				}
			}
		} else if(line.trim().equalsIgnoreCase("CONTRACT LIST")) {
			line = reader.readLine();
			if (line.trim().equals("{")) {
				line = reader.readLine();
				if (line.trim().toLowerCase().startsWith("CONTRACT".toLowerCase())) {
					while(true) {
					if (line.trim().equals("{")) {
						int contractNumber=0;
						int serviceID=0;
						String paymentMethod="";
						String customerName="";
						String phoneNumber="";
						String date="";
						String type="";
						double pay=0;
						double statStath=0;
						double statKin=0;
						int statSms=0;
						double statMB=0;
						double timeForStath=0;
						double timeForMob=0;
						int SMS=0;
						double mb=0;
						double upoloipo=0;
						while(!line.trim().equals("}")) {
							if(line.trim().toLowerCase().startsWith("CONTRACT NUMBER ".toLowerCase()))
									contractNumber=Integer.parseInt(line.substring(17).trim());
							if(line.trim().toLowerCase().startsWith("SERVICE ID ".toLowerCase()))
									serviceID=Integer.parseInt(line.substring(12).trim());
							if(line.trim().toLowerCase().startsWith("PAYMENT METHOD ".toLowerCase()))
									paymentMethod=line.substring(16).trim();
							if(line.trim().toLowerCase().startsWith("TYPE ".toLowerCase()))
									type=line.substring(6).trim();
							if(line.trim().toLowerCase().startsWith("CUSTOMER ".toLowerCase()))
									customerName=line.substring(10).trim();
							if(line.trim().toLowerCase().startsWith("PHONE NUMBER ".toLowerCase()))
									phoneNumber=line.substring(14).trim();
							if(line.trim().toLowerCase().startsWith("DATE ".toLowerCase()))
									date=line.substring(6).trim();
							if(line.trim().toLowerCase().startsWith("PAY ".toLowerCase()))
									pay=Double.parseDouble(line.substring(5).trim());
							if(line.trim().toLowerCase().startsWith("STAT STATH ".toLowerCase()))
									statStath=Double.parseDouble(line.substring(12).trim());
							if(line.trim().toLowerCase().startsWith("STAT KIN ".toLowerCase()))
									statKin=Double.parseDouble(line.substring(10).trim());
							if(line.trim().toLowerCase().startsWith("STAT SMS ".toLowerCase()))
									statSms=Integer.parseInt(line.substring(10).trim());
							if(line.trim().toLowerCase().startsWith("STAT MB ".toLowerCase()))
									statMB=Double.parseDouble(line.substring(9).trim());
							if(line.trim().toLowerCase().startsWith("TIME FOR STATH ".toLowerCase()))
									timeForStath=Double.parseDouble(line.substring(16).trim());
							if(line.trim().toLowerCase().startsWith("TIME FOR MOB ".toLowerCase()))
									timeForMob=Double.parseDouble(line.substring(14).trim());
							if(line.trim().toLowerCase().startsWith("SMS ".toLowerCase()))
									SMS=Integer.parseInt(line.substring(5).trim());
							if(line.trim().toLowerCase().startsWith("UPOLOIPO ".toLowerCase()))
									upoloipo=Double.parseDouble(line.substring(10).trim());
							if(line.trim().toLowerCase().startsWith("MB ".toLowerCase()))
									mb=Double.parseDouble(line.substring(4).trim());
							line = reader.readLine();
						}
						if(contractNumber!=0 && type!="" && serviceID>0 && customerName!="" && phoneNumber!="" && date!="")
							switch(type.toLowerCase()) {
								case "sumvolaiou":
									tmpContract = new MTalkContract(++Contract.ID, serviceID, paymentMethod, customerName, phoneNumber, date, pay, statStath, statKin, statSms, statMB, timeForStath, timeForMob, SMS);
									contracts.add(tmpContract);
									break;
								case "kartosumvolaiou":
									tmpContract = new MTalkKartSumvContract(++Contract.ID, serviceID, paymentMethod, customerName, phoneNumber, date, pay, statStath, statKin, statSms, statMB, timeForStath, timeForMob, SMS, upoloipo);
									contracts.add(tmpContract);
									break;
								case "internet":
									tmpContract = new MInternetContract(++Contract.ID, serviceID, paymentMethod, customerName, phoneNumber, date, pay, statStath, statKin, statSms, statMB, mb);
									contracts.add(tmpContract);
									break;
							}
						else
							System.out.println("There is an error with the file, information missing.");
					}line=reader.readLine();
						
					if(line==null)
						break;
					}
				}
			}
		}
	}
	catch (IOException e) {
		System.out.println ("Error reading line ...");
	}
	try {
		reader.close();
	}
	catch (IOException e) {
		System.err.println("Error closing file.");
	}
	}
	public ArrayList <Service> getServiceList() {
		return services;
	} 
	public ArrayList <Contract> getContractList() {
		return contracts;
	} 
}	
	
	