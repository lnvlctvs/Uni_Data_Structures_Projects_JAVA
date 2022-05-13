/*
---THEODOROPOULOS - FOTIOS KONSTANTINOS(3160040)
-2o eksamhno

---SCHIZA MARIANNA(3160170)
-2o eksamhno

---VELISSARIOS STAURIANOS (3160166)
-2o eksamhno

*/

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class mainApp extends JFrame implements ActionListener, MouseListener {
	private JButton showMonthlyCost, loadServiceList, reloadServiceList, loadContractList, reloadContractList, updateStatistics;
	private DefaultListModel<String> ServicesListModel;
	private static ArrayList<Contract> sumvolaia = new ArrayList<Contract>();
	private static ArrayList<Service> services = new ArrayList<Service>();
	private static StoreFileRL loader = new StoreFileRL();
	private JList<String> list1,list2;
	private JTextArea resultArea,resultArea2;
	private Service tmpService=null;
	private int currentServicesStep=1;
	private DefaultListModel<String> model2;
	private JComboBox sTypeList;
	private String contractsFileName="";

	private void addContractFunc() {
		DateFormat dateVar = new SimpleDateFormat("dd/MM/yyyy");
		Date dateVar2 = new Date();
		Double saleFinal=0.0;
		String tpay = JOptionPane.showInputDialog(null,
                        "Payment method?", null);
		String name = JOptionPane.showInputDialog(null,
                        "Full name?", null);
		String phone = JOptionPane.showInputDialog(null,
                        "Telephone?", null);
		String date = JOptionPane.showInputDialog(null,
                        "Date?", dateVar.format(dateVar2));
		String sale = JOptionPane.showInputDialog(null,
                        "Additional sale?(type the percentage e.g. 25 for 25% or 0 for no sale)?", null);
		if(sale!=null)
			saleFinal = Double.parseDouble(sale)/100;
		if((tpay!=null && tpay.length()>=2) && (name!=null && name.length()>=2) && (phone!=null && phone.length()>=2) && (date!=null && date.length()>=2) && saleFinal>=0 && saleFinal<=100) {
			Double finalPrice = tmpService.getPagio() - tmpService.getPagio()*tmpService.getSaleThisPeriod() + saleFinal;
			if(tmpService instanceof MTalk) {
				sumvolaia.add(new MTalkContract(++Contract.ID, tmpService.getCurrentID()-1, tpay, name, phone, date, finalPrice,0,0,0,0, tmpService.getTimeForStath(), tmpService.getTimeForMob(), tmpService.getSms()));
			} else if(tmpService instanceof MTalkKartSumv) {
				sumvolaia.add(new MTalkKartSumvContract(++Contract.ID, tmpService.getCurrentID()-1, tpay, name, phone, date, finalPrice,0,0,0,0, tmpService.getTimeForStath(), tmpService.getTimeForMob(), tmpService.getSms(), tmpService.getUpoloipo()));
			} else if(tmpService instanceof MInternet) {
				sumvolaia.add(new MInternetContract(++Contract.ID, tmpService.getCurrentID()-1, tpay, name, phone, date, finalPrice,0,0,0,0, tmpService.getMb()));
			} else
				System.out.println("ERROR ON ADDING CONTRACT");
			model2.removeAllElements();
			for (Contract contract : sumvolaia) {
				String s = String.valueOf(sTypeList.getSelectedItem());
							if(s=="All service types")
								model2.addElement(contract.getName());
							else if(s=="Programmata Sumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalk && !(services.get(contract.getServiceID()-1) instanceof MTalkKartSumv))
								model2.addElement(contract.getName());
							else if(s=="Programmata Kartosumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalkKartSumv)
								model2.addElement(contract.getName());
							else if(s=="Kinito Internet" && services.get(contract.getServiceID()-1) instanceof MInternet)
								model2.addElement(contract.getName());
						}
			JOptionPane.showMessageDialog(null, "New Contract added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	private void drawFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tabs = new JTabbedPane();
		JPanel servicePanel = new JPanel();
		JPanel contractPanel = new JPanel();
		tabs.addTab("Services", servicePanel);
		tabs.addTab("Contracts", contractPanel);
		
		
		BorderLayout layout = new BorderLayout();
		Container cp = getContentPane();
		cp.setLayout(layout);
		
		JPanel paneButton1 = new JPanel();
		paneButton1.setLayout(new GridLayout(1,2));
		JPanel paneButton2 = new JPanel();
		paneButton2.setLayout(new GridLayout(2,2));
		
		//Buttons
			loadServiceList = new JButton("Load service list");
			loadServiceList.addActionListener(this);
			updateStatistics = new JButton("Update statistics");
			updateStatistics.setEnabled(false);
			updateStatistics.addActionListener(this);
			showMonthlyCost = new JButton("Show total monthly cost");
			showMonthlyCost.addActionListener(this);
			paneButton1.add(loadServiceList);
			servicePanel.add(paneButton1);
		//List
			String[] cs = {"Programmata Sumvolaiou", "Programmata Kartosumvolaiou", "Kinito Internet"};
			DefaultListModel<String> model = new DefaultListModel<>();
			for (int i = 0; i < cs.length; i++ )
				model.addElement(cs[i]);
			list1 = new JList<>(model);
			list1.setVisibleRowCount(7);
			list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scroller = new JScrollPane(list1);
			Dimension d = list1.getPreferredSize();
			d.width = 200;
			d.height = 120;
			scroller.setPreferredSize(d);

			servicePanel.add(scroller);
			//On first-stage click
			MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() >= 2) {
				int index = theList.locationToIndex(mouseEvent.getPoint());
				if (index >= 0) {
					Object o = theList.getModel().getElementAt(index);
					String choice = o.toString();
					if(services.size()<=0)
						JOptionPane.showMessageDialog(null, "You have to load Services file!", "Warning", JOptionPane.WARNING_MESSAGE);
					else if(currentServicesStep==1) {
						if(choice=="Programmata Sumvolaiou")
							resultArea.setText("\"Programmata sumvolaiou\" features:\n -Free talking time(fixed and mobile)\n -Free SMS\n ***These features have limited usage***");
						else if(choice=="Programmata Kartosumvolaiou")
							resultArea.setText("\"Programmata sumvolaiou\" features:\n -Free talking time(fixed and mobile)\n -Free SMS\n -Available balance \n ***These features have limited usage***");
						else
							resultArea.setText("\"Programmata sumvolaiou\" feature:\n -Free internet acces(limited)");
						model.removeAllElements();
						model.addElement("<<- Back");
						for (Service service : services) {
							if(service instanceof MTalk && !(service instanceof MTalkKartSumv) && choice=="Programmata Sumvolaiou")
								model.addElement(service.getName());
							else if(service instanceof MTalkKartSumv && choice=="Programmata Kartosumvolaiou")
								model.addElement(service.getName());
							else if(service instanceof MInternet && choice=="Kinito Internet")
								model.addElement(service.getName());
						}
					currentServicesStep=2;
				} else if(currentServicesStep==2) {
					if(choice=="<<- Back") {
						model.removeAllElements();
						for (int i = 0; i < cs.length; i++)
							model.addElement(cs[i]);
						currentServicesStep=1;
					} else {
						model.removeAllElements();
						model.addElement("<<- Back");
						model.addElement("To add a contract double click here or press the button");
						// tmpService
						for (Service service : services) {
							if(choice==service.getName())
								tmpService=service;
						}
						String extra="";
						if(tmpService instanceof MTalk) {
							extra = "\n-Hours for fixed telephones: "+tmpService.getTimeForStath()+"\n-Hours for mobile phones: "+tmpService.getTimeForMob()+"\n-Sms: "+tmpService.getSms();
							if(tmpService instanceof MTalkKartSumv)
								extra += "\n-Balance: "+tmpService.getUpoloipo();
						} else if(tmpService instanceof MInternet)
							extra = "\n-MB: "+tmpService.getMb();
						else
							System.out.println("DD");
						resultArea.setText(tmpService.getName()+":\n-Fee: "+tmpService.getPagio()+"\n-Sale this period: "+tmpService.getSaleThisPeriod()+extra);
						currentServicesStep=3;
					}
				} else if(currentServicesStep==3) {
					if(choice=="<<- Back") {
						model.removeAllElements();
						for (int i = 0; i < cs.length; i++)
							model.addElement(cs[i]);
						currentServicesStep=1;
					} else if(choice=="To add a contract double click here or press the button") {
						addContractFunc();
					}
				}
				}
				}
			}
			};
			list1.addMouseListener(mouseListener);
		//Textarea
			resultArea = new JTextArea("Information will be displayed here.",10,20);
			resultArea.setEditable(false);
			servicePanel.add(resultArea);
			
		
		
		/*---2nd tab---*/
		//Buttons
			loadContractList = new JButton("Load contract list");
			loadContractList.addActionListener(this);
			reloadContractList = new JButton("Update contract list");
			paneButton2.add(loadContractList);

			paneButton2.add(reloadContractList);
			reloadContractList.addActionListener(this);
			paneButton2.add(updateStatistics);
			paneButton2.add(showMonthlyCost);
			contractPanel.add(paneButton2);
		//List
			model2 = new DefaultListModel<>();
			model2.addElement("You have to load contract list file.");
			list2 = new JList<>(model2);
			list2.setVisibleRowCount(7);
			list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane scroller2 = new JScrollPane(list2);
			scroller2.setPreferredSize(d);
		//Textarea
			resultArea2 = new JTextArea("Information will be displayed here.",10,20);
			resultArea2.setEditable(false);
			// contractPanel.add(resultArea2);
			String[] sTypeStr = {"All service types", "Programmata Sumvolaiou", "Programmata Kartosumvolaiou", "Kinito Internet"};
			sTypeList = new JComboBox(sTypeStr);
			sTypeList.setSelectedIndex(0);
			sTypeList.addActionListener (new ActionListener () {
				public void actionPerformed(ActionEvent e) {
					String s = String.valueOf(sTypeList.getSelectedItem());
					model2.removeAllElements();
					if(sumvolaia.size()<=0)
						model2.addElement("You have to load contract list file.");
					else
						for (Contract contract : sumvolaia) {
							if(s=="All service types")
								model2.addElement(contract.getName());
							else if(s=="Programmata Sumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalk && !(services.get(contract.getServiceID()-1) instanceof MTalkKartSumv))
								model2.addElement(contract.getName());
							else if(s=="Programmata Kartosumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalkKartSumv)
								model2.addElement(contract.getName());
							else if(s=="Kinito Internet" && services.get(contract.getServiceID()-1) instanceof MInternet)
								model2.addElement(contract.getName());
						}
				}
			});
			MouseListener mouseListener2 = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				if(list2.getSelectedValue()!="You have to load contract list file.") {
					updateStatistics.setEnabled(true);
				JList theList = (JList) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
				int index = theList.locationToIndex(mouseEvent.getPoint());
				if (index >= 0) {
					Object o = theList.getModel().getElementAt(index);
					String choice = o.toString();
					Contract thisContract=null;
					for (Contract contract : sumvolaia)
						if(contract.getName()==choice)
							thisContract=contract;
					String extra="";
					if(services.get(thisContract.getServiceID()-1) instanceof MTalkKartSumv)
						extra="\nRemaining amount: "+thisContract.getUpoloipo();
					JOptionPane.showMessageDialog(null, "Full name: "+thisContract.getName()+"\nService name: "+services.get(thisContract.getServiceID()-1).getName()+"\nTelephone: "+thisContract.getPhone()+"\nDate: "+thisContract.getDate()+"\nFee: "+thisContract.getPagio()+"\n ---Statistics---\nTalked on fixed phones:(minutes): "+thisContract.getStatStath()+"\nTalked on mobile phones:(minutes) "+thisContract.getStatKin()+"\nSms spent: "+thisContract.getStatSMS()+"\nMB Spent: "+thisContract.getStatMB()+extra, "Information", JOptionPane.INFORMATION_MESSAGE);
				}
				}
			}}
			};
			list2.addMouseListener(mouseListener2);
			contractPanel.add(sTypeList);
			contractPanel.add(scroller2);
			

		
		
		
		
		
		// layout.layoutContainer(cp);
		// JPanel paneButton = new JPanel();
		// paneButton.setLayout(new FlowLayout());
		
		cp.add(tabs);
		pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loadServiceList) {
			JButton open = new JButton();
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose services file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
				services.clear();
				loader.loadFile(chooser.getSelectedFile().getAbsolutePath());
				
				services = loader.getServiceList();
			}
		} else if(e.getSource() == loadContractList) {
			if(services.size()==0)
				JOptionPane.showMessageDialog(null, "You have to load service list file first.", "Warning", JOptionPane.WARNING_MESSAGE);
			else {
			JButton open = new JButton();
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Choose services file");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if(chooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					sumvolaia.clear();
					contractsFileName = chooser.getSelectedFile().getAbsolutePath();
					loader.loadFile(contractsFileName);
					sumvolaia = loader.getContractList();
					model2.removeAllElements();
					String s = String.valueOf(sTypeList.getSelectedItem());
					for (Contract contract : sumvolaia) {
						if(s=="All service types")
								model2.addElement(contract.getName());
							else if(s=="Programmata Sumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalk && !(services.get(contract.getServiceID()-1) instanceof MTalkKartSumv))
								model2.addElement(contract.getName());
							else if(s=="Programmata Kartosumvolaiou" && services.get(contract.getServiceID()-1) instanceof MTalkKartSumv)
								model2.addElement(contract.getName());
							else if(s=="Kinito Internet" && services.get(contract.getServiceID()-1) instanceof MInternet)
								model2.addElement(contract.getName());
					}
				}
			}
		} else if(e.getSource()==reloadContractList) {
			if(sumvolaia.size()>0) {
				updateContracts(sumvolaia, services);
				JOptionPane.showMessageDialog(null, "Contract list file updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "You have to load contract list file first.", "Warning", JOptionPane.WARNING_MESSAGE);
			
		} else if(e.getSource()==updateStatistics) {
			if(list2.getSelectedValue()!="You have to load contract list file." && list2.getSelectedValue()!="") {
				if(list2.getSelectedValue()==null)
					updateStatistics.setEnabled(false);
				else {
					Contract tmpContract = null;
					String option=list2.getSelectedValue();
					for (Contract contract : sumvolaia) {
						if(contract.getName()==option) {
							String StatStath = (JOptionPane.showInputDialog(null,"Minutes used for telephones?", contract.getStatStath()));
							if(StatStath!=null)
								contract.setStatStath(Double.parseDouble(StatStath));
							String StatKin = (JOptionPane.showInputDialog(null,"Minutes used for mobile phones?", contract.getStatKin()));
							if(StatKin!=null)
								contract.setStatKin(Double.parseDouble(StatKin));
							String StatMB = (JOptionPane.showInputDialog(null,"MB spent?", contract.getStatMB()));
							if(StatMB!=null)
								contract.setStatMB(Double.parseDouble(StatMB));
							String StatSms = JOptionPane.showInputDialog(null,"SMS spent?", contract.getStatSMS());
							if(StatSms!=null && StatSms!="")
								contract.setStatSms(Integer.parseInt(StatSms));
							if(services.get(contract.getServiceID()-1) instanceof MTalkKartSumv) {
							String StatUpoloipo = (JOptionPane.showInputDialog(null,"Remaining amount?", contract.getUpoloipo()));
							if(StatUpoloipo!=null)
								contract.setUpoloipo(Double.parseDouble(StatUpoloipo));
							}
							break;
						}
					}
				}
			}
		} else if(e.getSource()==showMonthlyCost) {
					double tmp1 = 0;
					double tmp2 = 0;
					double tmp3 = 0;
					for(int x=0;x<sumvolaia.size();x++) {
						if(services.get(sumvolaia.get(x).getServiceID()-1) instanceof MTalk && !(services.get(sumvolaia.get(x).getServiceID()-1) instanceof MTalkKartSumv))
							tmp1+=sumvolaia.get(x).getPagio();
						else if(services.get(sumvolaia.get(x).getServiceID()-1) instanceof MInternet)
							tmp2+=sumvolaia.get(x).getPagio();
						else
							tmp3+=sumvolaia.get(x).getUpoloipo();
					}
					JOptionPane.showMessageDialog(null, "Total montly cost for \"Programmata sumvolaiou tilefwnias\": "+tmp1+"\nTotal montly cost for Internet: "+tmp2+"\nTotal remaining amount for \"Kartosumvolaia\": "+tmp3, "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void mouseClicked(MouseEvent event) {
		
	}
	public void mousePressed(MouseEvent e){}
                public void mouseEntered(MouseEvent e){}
                public void mouseExited(MouseEvent e){}
                public void mouseReleased(MouseEvent e){}
	
	public mainApp() {
		setTitle("Programmata thlepikoinwnias");
		drawFrame();
		setBounds(300, 300, 790, 500);
		setVisible(true);
	}
	public static void main(String[] args) {
		mainApp frame = new mainApp();
		
		int choice,choice2;
		double sale, finalPrice, tmp1=0,tmp2=0,tmp3=0;
		String tpay, name, phone, date;
		
		//Sumvolaia
		ArrayList<Contract> sumvolaia = new ArrayList<Contract>();
	}
	public static void updateContracts(ArrayList<Contract> sumvolaia, ArrayList<Service> services) {
		try {
			//Tha evaza thn metavlith contractsFileName alla mou zhtaei na thn kanw static
            File contractsFile = new File("contracts.txt");
            FileWriter fw = new FileWriter(contractsFile);
			fw.write("CONTRACT LIST\n{");
			for (Contract tmp : sumvolaia) {
				if(tmp.getServiceID()==0)
					break;
				if(services.get(tmp.getServiceID()-1) instanceof MTalk) {
					//MTalk
					fw.write("\n\tCONTRACT\n\t{");
					fw.write("\n\t\tCONTRACT NUMBER "+tmp.getCurrID());
					fw.write("\n\t\tSERVICE ID "+tmp.getServiceID());
					fw.write("\n\t\tPAYMENT METHOD "+tmp.getTpay());
					if(services.get(tmp.getServiceID()-1) instanceof MTalkKartSumv)
						fw.write("\n\t\tTYPE Kartosumvolaiou");
					else
						fw.write("\n\t\tTYPE Sumvolaiou");
					fw.write("\n\t\tCUSTOMER "+tmp.getName());
					fw.write("\n\t\tPHONE NUMBER "+tmp.getPhone());
					fw.write("\n\t\tDATE "+tmp.getDate());
					fw.write("\n\t\tPAY "+tmp.getPagio());
					//FOR MONTHLY USAGE
					fw.write("\n\t\tSTAT STATH "+tmp.getStatStath());
					fw.write("\n\t\tSTAT KIN "+tmp.getStatKin());
					fw.write("\n\t\tSTAT SMS "+tmp.getStatSMS());
					fw.write("\n\t\tSTAT MB "+tmp.getStatMB());
					fw.write("\n\t\tTIME FOR STATH "+tmp.getTimeForStath());
					fw.write("\n\t\tTIME FOR MOB "+tmp.getTimeForMob());
					fw.write("\n\t\tSMS "+tmp.getSms());
					if(services.get(tmp.getServiceID()-1) instanceof MTalkKartSumv) {
						fw.write("\n\t\tUPOLOIPO "+tmp.getUpoloipo());
					}
					fw.write("\n\t}");
				} else {
					//MInternet
					fw.write("\n\tCONTRACT\n\t{");
					fw.write("\n\t\tCONTRACT NUMBER "+tmp.getCurrID());
					fw.write("\n\t\tSERVICE ID "+tmp.getServiceID());
					fw.write("\n\t\tPAYMENT METHOD "+tmp.getTpay());
					fw.write("\n\t\tTYPE Internet");
					fw.write("\n\t\tCUSTOMER "+tmp.getName());
					fw.write("\n\t\tPHONE NUMBER "+tmp.getPhone());
					fw.write("\n\t\tDATE "+tmp.getDate());
					fw.write("\n\t\tPAY "+tmp.getPagio());
					//FOR MONTHLY USAGE
					fw.write("\n\t\tSTAT STATH "+tmp.getStatStath());
					fw.write("\n\t\tSTAT KIN "+tmp.getStatKin());
					fw.write("\n\t\tSTAT SMS "+tmp.getStatSMS());
					fw.write("\n\t\tSTAT MB "+tmp.getStatMB());
					fw.write("\n\t\tMB "+tmp.getMb());
					fw.write("\n\t}");
				}
			}
			fw.write("\n}");
            fw.close();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
		
	}
}