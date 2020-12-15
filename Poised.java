import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * This program serves as a project management system for a small structural engineering firm called "Poised".
 * The purpose of this program is to keep track of all the projects that Poised is handling.
 * <p>
 * @author Shivashna Rooplall
 * @version 3.0 , 12 December 2020
 */

public class Poised {
	/**
	 * @see Poised
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			// When the program runs, the main menu will be the first thing to be displayed to the user.
			int option = menuOption();
		
			// Objects with default values are then created
			// This allows the default values in the object fields to be replaced later on with values entered by the user
			
			Projects project = new Projects(0, null, null, null, null, 0.0d, 0.0d, null, false);
			ArrayList<Projects> projectList =  new ArrayList<Projects>();//List of all projects
			ArrayList<Projects> incompleteProjectsList =  new ArrayList<Projects>();// List of incomplete projects
			ArrayList<Projects> overdueProjectsList =  new ArrayList<Projects>();// List of overdue projects
			
			ProjectPeople client = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> clientList = new ArrayList<ProjectPeople>();// List all of clients
			ArrayList<ProjectPeople> incompleteClientList = new ArrayList<ProjectPeople>();// List of incomplete projects client details
			ArrayList<ProjectPeople> overdueClientList = new ArrayList<ProjectPeople>();// List of overdue projects client details
			
			ProjectPeople contractor = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> contractorList = new ArrayList<ProjectPeople>();// List all of Contractors
			ArrayList<ProjectPeople> incompleteContractorList = new ArrayList<ProjectPeople>();// List of incomplete projects contractor details
			ArrayList<ProjectPeople> overdueContractorList = new ArrayList<ProjectPeople>();// List of overdue projects contractor details
			
			ProjectPeople architect = new ProjectPeople(null, null, null, null);
			ArrayList<ProjectPeople> architectList = new ArrayList<ProjectPeople>();// List all of architects
			ArrayList<ProjectPeople> incompleteArchitectList = new ArrayList<ProjectPeople>();// List of incomplete projects architect details
			ArrayList<ProjectPeople> overdueArchitectList = new ArrayList<ProjectPeople>();// List of overdue projects architect details
			
			

			// The program then reads all the information about the existing projects from the appropriate text files and generates all the lists 
			BufferedReader projectReader;
			BufferedReader projectPeopleReader;
			try {
				
				projectReader = new BufferedReader(new FileReader("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt"));
				projectPeopleReader = new BufferedReader(new FileReader("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjectPeople.txt"));
				
				//Reading the first lines of each text file
				String projectLine = projectReader.readLine();
				String projectPeopleLine = projectPeopleReader.readLine();
				
				while (projectLine != null) {
					
					// Separating each line into an array
					String[] projectArray = projectLine.split(", ", (9));
					String[] projectPeopleArray = projectPeopleLine.split(", ", (13));

					//Getting the required information to add project object to project list 
					int projectNumber = Integer.parseInt(projectArray[0]);
					String projectName = projectArray[1];
					String projectType = projectArray[2];
					String projectPhysicalAddress = projectArray[3];
					String erfNumber = projectArray[4];
					double totalFee = Double.parseDouble(projectArray[5]);
					double amountPaid = Double.parseDouble(projectArray[6]);
					String deadline = projectArray[7];
					String finalised_str = projectArray[8].replaceAll("\r\n", "");
					boolean finalised = Boolean.parseBoolean(finalised_str);
					
					// Getting the required information to add client object to client list
					String clientName = projectPeopleArray[1];
					String clientTelephoneNumber = projectPeopleArray[2];
					String clientEmailAddress = projectPeopleArray[3];
					String clientPhysicalAddress = projectPeopleArray[4];
					
					// Getting the required information to add contractor object to contractor list
					String contractorName = projectPeopleArray[5];
					String contractorTelephoneNumber = projectPeopleArray[6];
					String contractorEmailAddress = projectPeopleArray[7];
					String contractorPhysicalAddress = projectPeopleArray[8];
					
					// Getting the required information to add architect object to architect list
					String architectName = projectPeopleArray[9];
					String architectTelephoneNumber = projectPeopleArray[10];
					String architectEmailAddress = projectPeopleArray[11];
					String architectPhysicalAddress = projectPeopleArray[12].replaceAll("\r\n", "");
					
					// Creating all the objects above and adding them to the list
					// Project object
					//The line below is essential to avoid overwriting the contents of the list
					project = new Projects(projectNumber,projectName,projectType,projectPhysicalAddress,erfNumber,totalFee,amountPaid,deadline,finalised);
					projectList.add(projectNumber-1,project);
					
					// Client object
					//The line below is essential to avoid overwriting the contents of the list
					client = new ProjectPeople( clientName,clientTelephoneNumber,clientEmailAddress,clientPhysicalAddress);
					clientList.add(projectNumber-1,client);
					
					// Contractor object
					//The line below is essential to avoid overwriting the contents of the list
					contractor = new ProjectPeople(contractorName,contractorTelephoneNumber,contractorEmailAddress,contractorPhysicalAddress);
					contractorList.add(projectNumber-1,contractor);
					
					// Architect object
					//The line below is essential to avoid overwriting the contents of the list
					architect = new ProjectPeople(architectName,architectTelephoneNumber,architectEmailAddress,architectPhysicalAddress);
					architectList.add(projectNumber-1,architect);
					
					// Generating incomplete project list, client list, contractor list and architect list
					if(finalised == false) {
						incompleteProjectsList.add(project);
						incompleteClientList.add(client);
						incompleteContractorList.add(contractor);
						incompleteArchitectList.add(architect);	
					}
					
					
					// Reading the next line of each text file
					projectLine = projectReader.readLine();
					projectPeopleLine = projectPeopleReader.readLine();
					
				}
				

				projectReader.close();
				projectPeopleReader.close();
			
			
			
				
			//The program will run and allow the user to select different options until they choose option 8 (to exit the program)
			while (option != 8) {

//******************************************** OPTION 1 *******************************************************************************************************************************************
				if (option == 1) {
					// Option 1 allows the user to enter details for the project and these details will be used to create the project object, client object, contractor object and architect object. 
					
					try {
						
						// Details for the project object from user input
						// The project number is automatically generated for every new project
						int projectNumber = projectList.size() + 1;
						String projectName = JOptionPane.showInputDialog("Enter the project name or press 'ok' to create default project name : ");
						
						// Checking if the project name already exists
						boolean equalProjectNames = false;
						for (int countProjectName = 0; countProjectName < projectList.size(); countProjectName ++) {
							String projectName_List = projectList.get(countProjectName).getProjectName();
							if(projectName.equalsIgnoreCase(projectName_List)) {
								equalProjectNames = true;
								while(equalProjectNames == true) {
									projectName = JOptionPane.showInputDialog("The project name you have entered already exists. Please enter another project name : ");
									if (projectName != projectName_List) {
										equalProjectNames = false;
									}
								}
							}
						}
						
						
						String projectType = JOptionPane.showInputDialog("Enter the project type : ");
						String physicalAddress = JOptionPane.showInputDialog("Enter the physical address for the project : ");
						String erfNumber = JOptionPane.showInputDialog("The project number is : " + projectNumber +"\nEnter the ERF number for the project : ");
						double totalFee = Double.parseDouble(JOptionPane.showInputDialog("Enter the total fee for the project : R"));
						double totalAmountPaid = Double.parseDouble(JOptionPane.showInputDialog("Enter the total amount paid by the client for the project : R"));
						String deadline = JOptionPane.showInputDialog("Enter the deadline for the project. (eg.10 September 2020) : ");
						boolean finalised = false;
			

						// Details for the client object from user input
						String clientName = JOptionPane.showInputDialog("Enter the first name and last name of the client : ");
						String[] clientNameArray = clientName.split(" ",2);
						String clientSurname = clientNameArray[1];
						String clientTelephoneNumber = JOptionPane.showInputDialog("Enter the client's telephone number : ");
						String clientEmailAddress = JOptionPane.showInputDialog("Enter the client's email address : ");
						String clientPhysicalAddress = JOptionPane.showInputDialog("Enter the client's physical address : ");
						//Changing the default values of the fields in the client object
						client = new ProjectPeople(clientName, clientTelephoneNumber, clientEmailAddress, clientPhysicalAddress);
						//Adding to list of clients
						clientList.add(projectNumber-1,client);
			
				
						// Details for the contractor object from user input
						String contractorName = JOptionPane.showInputDialog("Enter the first name and last name of the contractor : ");
						String contractorTelephoneNumber = JOptionPane.showInputDialog("Enter the contractor's telephone number : ");
						String contractorEmailAddress = JOptionPane.showInputDialog("Enter the contractor's email address : ");
						String contractorPhysicalAddress = JOptionPane.showInputDialog("Enter the contractor's physical address : ");
						//Changing the values of the fields in the contractor object
						contractor = new ProjectPeople(contractorName, contractorTelephoneNumber, contractorEmailAddress, contractorPhysicalAddress);
						//Adding to list of contractors
						contractorList.add(projectNumber-1,contractor);
			
				
						// Details for the architect object from user input
						String architectName = JOptionPane.showInputDialog("Enter the first name and last name of the architect : ");
						String architectTelephoneNumber = JOptionPane.showInputDialog("Enter the architect's telephone number : ");
						String architectEmailAddress = JOptionPane.showInputDialog("Enter the architect's email address : ");
						String architectPhysicalAddress = JOptionPane.showInputDialog("Enter the architect's physical address : ");
						//Changing the values of the fields in the architect object
						architect = new ProjectPeople(architectName, architectTelephoneNumber, architectEmailAddress, architectPhysicalAddress);
						//Adding to list of Architects
						architectList.add(projectNumber-1,architect);
			
				
						// Changing the default values of the fields in the project object to the values entered by the user
						
						if (projectName.isEmpty() == true) {
							// This code will execute if the user does not enter a project name
							// If a project name isn't entered, a default project name will be created
							
							String defaultProjectName = projectType + " " + clientSurname;
							//Checking if the default project name already exists
							equalProjectNames = false;
							for (int countProjectName = 0; countProjectName < projectList.size(); countProjectName ++) {
								String projectName_List = projectList.get(countProjectName).getProjectName();
								if(defaultProjectName.equalsIgnoreCase(projectName_List)) {
									equalProjectNames = true;
									while(equalProjectNames == true) {
										projectName = JOptionPane.showInputDialog("The project name you have entered already exists. Please enter another project name : ");
										if (defaultProjectName != projectName_List) {
											equalProjectNames = false;
										}
									}
								}
							}
							
							project = new Projects(projectNumber,defaultProjectName,projectType,physicalAddress,erfNumber,totalFee,totalAmountPaid,deadline,finalised);
							//Adding project object to the list
							projectList.add(projectNumber-1,project);
							//Displaying the project details
							System.out.println("The project has been created.\nProject details :");
							System.out.println(project + "\n");
							//Calling the method to write the project to text file 'AllProjects.txt'
							addProjectToTextFile(project, client, contractor, architect);
							
				
				
						} else if (projectName.isEmpty() == false) {
							// This code will execute if a project name is entered by the user
							
							project = new Projects(projectNumber,projectName,projectType,physicalAddress,erfNumber,totalFee,totalAmountPaid,deadline,finalised);
							//Adding project object to the list
							projectList.add(projectNumber-1,project);
							System.out.print(projectList);
							//Displaying the project details
							System.out.println("\n\nThe project has been created.\nProject details :");
							System.out.println(project + "\n");
							//Calling the method to write the project to text file 'AllProjects.txt'
							addProjectToTextFile(project, client, contractor, architect);
						
						}
			
						//Displaying the client, contractor and architect details
						System.out.println("Client details :");
						System.out.println(client + "\n");
						System.out.println("Contractor details :");
						System.out.println(contractor + "\n");
						System.out.println("Architect details :");
						System.out.println(architect + "\n");
						// Displaying the main menu again
						option = menuOption();
				
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Please make sure that you enter the full name (name and surname) of the client.");
						option = menuOption();
					}
		
				
				
//************************************************ OPTION 2 ****************************************************************************************
				} else if (option == 2){
					// This option allows the user to set a new deadline for the project
					
					// This method displays all the projects details from the respective lists
					displayListProjectDetails(projectList, clientList, contractorList, architectList);
					
					// The must user must choose whether to select a project they want to edit by entering the project number or the project name				
					int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to edit the deadline for by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
					 
					if (selectionChoice == 1) {
						// This option will execute if the user chooses to select a project by entering the project number
						
						int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the deadline for : "));
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							countProjects += 1;
							// Getting all the project details from the list
							int projectNumber_List = projectList.get(j).getProjectNumber();
							
							if(projectNum_input == projectNumber_List) {
								// The deadline before it is changed
								String initialDeadline = projectList.get(j).getDeadline();
								
								// Getting new deadline from user input
								String newDeadline = JOptionPane.showInputDialog("The current deadline for this project is : " + initialDeadline + "\nEnter the new deadline you wish to set for the project (eg.10 September 2020) : ");
								
								// Updating the deadline in the projectList
								projectList.get(j).setNewDeadline(newDeadline);
								
								//Writing edited info to the text file 'AllProjects.txt'
								modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", initialDeadline, newDeadline);
								
								System.out.println("\nThe deadline of the project has been changed to : " + projectList.get(j).getDeadline());
							
							} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
								System.out.println("\nThe project number you have entered does not exist.");
							}
						}
						
						// Displaying the main menu again
						option = menuOption();
					
					
					} else if (selectionChoice == 2) {
						// This option will execute if the user chooses to select a project by entering the project name
						String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project your would like to edit the deadline for : ");
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							countProjects += 1;
							// Getting the required project details from the list
							String projectName_List = projectList.get(j).getProjectName();
							
							if(projectName_input.equalsIgnoreCase(projectName_List)){
								// The deadline before it is changed
								String initialDeadline = projectList.get(j).getDeadline();
								
								String newDeadline = JOptionPane.showInputDialog("The current deadline for this project is : " + initialDeadline + "\nEnter the new deadline you wish to set for the project (eg. 10 September 2020) : ");
								
								// Updating the deadline
								projectList.get(j).setNewDeadline(newDeadline);
								
								//Writing edited info to the text file
								modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", initialDeadline, newDeadline);
								System.out.println("\nThe deadline of the project has been changed to : " + projectList.get(j).getDeadline());
								
							} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
								System.out.println("\nThe project name you have entered does not exist.");
							}
						}
						
						// Displaying the main menu again
						option = menuOption();
					}
			
		
		
//************************************************** OPTION 3 ************************************************************************************************************
				} else if (option == 3){
					// Changing the total amount paid by the client
					// This option allows the total amount paid by the client to be updated every time the client pays a partial amount
					
					// This method displays all the projects details from the respective lists
					displayListProjectDetails(projectList, clientList, contractorList, architectList);
					
					// The must user must choose whether to select a project they want to edit by entering the project number or the project name
					int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to edit the total amount paid by the client for by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
					
					if (selectionChoice == 1) {
						// This option will execute if the user chooses to select a project by entering the project number
						
						int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the total amount paid for : "));
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						
						for (int j = 0; j < projectList.size(); j++) {
							
							countProjects += 1;
							
							// Getting all the project details from the list
							int projectNumber_List = projectList.get(j).getProjectNumber();
							
							if(projectNum_input == projectNumber_List) {
								
								// The total amount paid before it is changed
								double initialAmountPaid = projectList.get(j).getTotalAmountPaid();
								
								//Getting the new total amount paid from the user
								double amountPaidNow = Integer.parseInt(JOptionPane.showInputDialog("The total amount paid by the client for this project thus far is : R" + initialAmountPaid + "\nEnter the additonal amount paid by the client for the project : R"));
								
								// Updating the total amount paid in the project list
								projectList.get(j).setNewTotalAmountPaid(amountPaidNow);
								
								//Writing edited info to the text file 'AllProjects.txt'
								modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", (""+initialAmountPaid), (""+ projectList.get(j).getTotalAmountPaid()));
								
								System.out.println("\nThe total amount paid for the project has been updated to : R" + projectList.get(j).getTotalAmountPaid());
							
							} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
								System.out.println("\nThe project number you have entered does not exist.");
							}
						}
						
						// Displaying the main menu again
						option = menuOption();
					
				
					} else if (selectionChoice == 2) {
						// This option will execute if the user chooses to select a project by entering the project name
						
						String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project you would like to edit the total amount paid for : ");
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							countProjects += 1;
							// Getting the required project details from the list
							String projectName_List = projectList.get(j).getProjectName();
							
							if(projectName_input.equalsIgnoreCase(projectName_List)){
								
								// The total amount paid before it is changed
								double initialAmountPaid = projectList.get(j).getTotalAmountPaid();
								
								//Getting the new total amount paid from the user
								double amountPaidNow = Integer.parseInt(JOptionPane.showInputDialog("The total amount paid by the client for this project thus far is : " + initialAmountPaid + "\nEnter the additonal amount paid by the client for the project : "));
								
								// Updating the total amount paid in the project list
								projectList.get(j).setNewTotalAmountPaid(amountPaidNow);
								
								//Writing edited info to the text file 'AllProjects.txt'
								modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", (""+initialAmountPaid), (""+ projectList.get(j).getTotalAmountPaid()));
								System.out.println("\nThe total amount paid for the project has been updated to : R" + projectList.get(j).getTotalAmountPaid());
								
							} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
								System.out.println("\nThe project name you have entered does not exist.");
							}
						}
						
						// Displaying the main menu again
						option = menuOption();
					}
		
					
		
//*********************************************** OPTION 4 ******************************************************************
				} else if (option == 4) {
					// This option allows the user to edit the contractor's contact details
					// The user can either edit the contractor's telephone number or email address
					
					// This method displays all the projects details from the respective lists
					displayListProjectDetails(projectList, clientList, contractorList, architectList);
					
					int contractorOption = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the option you wish to select :\n1\t- Edit contractor's telephone number\n2\t- Edit the contractor's email address\nEnter an option number to continue : "));
					
					if (contractorOption == 1) {
						// This option will run if the user chooses to edit a contractors telephone number
							
						int ProjectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the contractor's telephone number for : "));
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							
							countProjects += 1;
							
							int projectNum_List = projectList.get(j).getProjectNumber();
						
							// If the project number exists in the project list, then the contractor for that specific project is stored in the position 'projectNumber-1' = 'j' in the contractor list
							if(ProjectNum_input == projectNum_List) {
									
								// The contractor's telephone number before it is changed
								String initialContractorTelephoneNumber = contractorList.get(j).getTelephoneNumber();
									
								//Getting the new contractor's telephone number from the user
								String newContractorTelephoneNumber = JOptionPane.showInputDialog("The number currently stored for the contractor is : " + initialContractorTelephoneNumber + "\nEnter the new telephone number for the contractor : ");
									
								// Updating the contractor's telephone number in the contractor list
								contractorList.get(j).setNewTelephoneNumber(newContractorTelephoneNumber);
									
								//Writing edited info to the text file 'AllProjectPeople.txt'
								modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjectPeople.txt", initialContractorTelephoneNumber, newContractorTelephoneNumber);
									
								System.out.println("\nThe new number for the contractor has been updated to : " + contractorList.get(j).getTelephoneNumber());
							
							} else if (ProjectNum_input != projectNum_List && countProjects == projectList.size()) {
								System.out.println("\nThe project number you have entered does not exist.");
							}
						}
						// Displaying the main menu again
						option = menuOption();
						
						
					} else if (contractorOption == 2) {
						// This option will run if the user chooses to edit a contractors email address
							
							int ProjectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you would like to edit the contractor's email address for : "));
							
							// Create a counter variable to keep track of how many projects the loop below has iterated through
							int countProjects = 0;
							for (int j = 0; j < projectList.size(); j++) {
								
								countProjects += 1;
								
								int projectNum_List = projectList.get(j).getProjectNumber();
						
								// If the project number exists in the project list, then the contractor for that specific project is stored in the position 'projectNumber-1' = 'j' in the contractor list
								if(ProjectNum_input == projectNum_List) {
									
									// The contractor's email address before it is changed
									String initialContractorEmailAddress = contractorList.get(j).getEmailAddress();
									
									//Getting the new contractor's email address from the user
									String newContractorEmailAddress = JOptionPane.showInputDialog("The email address currently stored for the contractor is : " + initialContractorEmailAddress + "\nEnter the new email address for the contractor : ");
									
									// Updating the contractor's email address in the contractor list
									contractorList.get(j).setNewEmailAddress(newContractorEmailAddress);
									
									//Writing edited info to the text file 'AllProjectPeople.txt'
									modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjectPeople.txt", initialContractorEmailAddress, newContractorEmailAddress);
									
									System.out.println("\nThe new email address for the contractor has been updated to : " + contractorList.get(j).getEmailAddress());
								
								} else if (ProjectNum_input != projectNum_List && countProjects == projectList.size()) {
									System.out.println("\nThe project number you have entered does not exist.");
								}
							
							}
							// Displaying the main menu again
							option = menuOption();
						
					}
					
		
		
//********************************************* OPTION 5 *******************************************************************************************************************************************************************************************************************************
				} else if (option == 5) {
					// This option allows the user to finalize a project.
					// When a project is finalized, the program checks if the client has paid the full fees or not
					// If the full fees has been paid the program will output a message saying so
					// Else an invoice will be generated for the client
					// All the information about the project will be written to a text file called "CompletedProject.txt"
					
					// Displaying the list of projects to the user in the console
					displayListProjectDetails(projectList, clientList, contractorList, architectList);
					
					// The must user must choose whether to select a project they want to edit by entering the project number or the project name
					int selectionChoice = Integer.parseInt(JOptionPane.showInputDialog("Search for the project you would like to finalise by : \n1\t- Entering the project number \n2\t- Entering the project name \nEnter the number of the option you wish to select : "));
					
					if (selectionChoice == 1) {
						// This option will execute if the user chooses to select a project by entering the project number
						
						int projectNum_input = Integer.parseInt(JOptionPane.showInputDialog("Enter the project number of the project you want to finalise : "));
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							
							countProjects += 1;
							
							// Getting all the project details from the list
							int projectNumber_List = projectList.get(j).getProjectNumber();
							String projectName_List = projectList.get(j).getProjectName();
							String projectType_List = projectList.get(j).getProjectType();
							String physicalAddress_List = projectList.get(j).getPhysicalAddress();
							String erfNumber_List = projectList.get(j).getERFNumber();
							double totalFee_List = projectList.get(j).getTotalFee();
							double amountPaid_List = projectList.get(j).getTotalAmountPaid();
							String deadline_List = projectList.get(j).getDeadline();
							boolean finalised_List = projectList.get(j).getFinalised();
							
							
							if(projectNum_input == projectNumber_List) {
								
								if (finalised_List == false) {// Checking if the project has already been finalised
									
									// Setting the boolean value finalised to true in the list
									projectList.get(j).setNewFinalised(true);
								
									// Changing the value of finalised in the text file 'AllProjects.txt'
									String oldLine = projectNumber_List + ", " + projectName_List + ", " + projectType_List + ", " + physicalAddress_List + ", " + erfNumber_List + ", " + totalFee_List + ", " + amountPaid_List + ", " + deadline_List + ", " + finalised_List;
									String newLine = projectNumber_List + ", " + projectName_List + ", " + projectType_List + ", " + physicalAddress_List + ", " + erfNumber_List + ", " + totalFee_List + ", " + amountPaid_List + ", " + deadline_List + ", " + true;
									modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", oldLine, newLine);
								
								
									//Getting all the project information to write to the text file
									//Project details
									double amountPaid = projectList.get(j).getTotalAmountPaid();
									double totalFee = projectList.get(j).getTotalFee();
								
									if (amountPaid == totalFee) {
										System.out.println("The client has paid the full fees for the project.");
										//Calling the method to write the project information to a text file
										addCompletedProjectToTextFile(project, client, contractor, architect);
							
									} else {
										// Client details
										String clientName = clientList.get(j).getName();
										String clientTelephoneNumber = clientList.get(j).getTelephoneNumber();
										String clientEmailAddress = clientList.get(j).getEmailAddress();
										String clientPhysicalAddress = clientList.get(j).getPeoplePhysicalAddress();
										String completionDate = JOptionPane.showInputDialog("Enter the completion date of the project : ");
										project.setNewCompletionDate(completionDate);
										double amountStillToPay = totalFee - amountPaid;
										System.out.println("\nInvoice Number : " + projectList.get(j).getERFNumber());
										System.out.println("\nClient's name : " + clientName + "\nClient's telephone number : " + clientTelephoneNumber + "\nClient's email address : " + clientEmailAddress + "\nClient's physical address : " + clientPhysicalAddress + "\nAmount still outstanding : R" + amountStillToPay);
										//Calling the method to write the project information to a text file
										addCompletedProjectToTextFile(project, client, contractor, architect);
								
									}
								
								}else if (finalised_List == true) {
									System.out.println("\n\nThis project has already been finalised.");
								}
							
							} else if (projectNum_input != projectNumber_List && countProjects == projectList.size()) {
								System.out.println("\nThe project number you have entered does not exist.");
							}
							
						}
						
						// Displaying the main menu again
						option = menuOption();
					
					
					} else if (selectionChoice == 2) {
						// This option will execute if the user chooses to select a project by entering the project name
						
						String projectName_input = JOptionPane.showInputDialog("Enter the project name of the project you want to finalise : ");
						
						// Create a counter variable to keep track of how many projects the loop below has iterated through
						int countProjects = 0;
						for (int j = 0; j < projectList.size(); j++) {
							
							countProjects += 1;
							
							// Getting the required project details from the list
							int projectNumber_List = projectList.get(j).getProjectNumber();
							String projectName_List = projectList.get(j).getProjectName();
							String projectType_List = projectList.get(j).getProjectType();
							String physicalAddress_List = projectList.get(j).getPhysicalAddress();
							String erfNumber_List = projectList.get(j).getERFNumber();
							double totalFee_List = projectList.get(j).getTotalFee();
							double amountPaid_List = projectList.get(j).getTotalAmountPaid();
							String deadline_List = projectList.get(j).getDeadline();
							boolean finalised_List = projectList.get(j).getFinalised();
							
							if(projectName_input.equalsIgnoreCase(projectName_List)){
								
								if (finalised_List == false) {// Checking if the project has already been finalised
									
									// Setting the boolean value finalised to true in the list
									projectList.get(j).setNewFinalised(true);
								
									// Changing the value of finalised in the text file 'AllProjects.txt'
									String oldLine = projectNumber_List + ", " + projectName_List + ", " + projectType_List + ", " + physicalAddress_List + ", " + erfNumber_List + ", " + totalFee_List + ", " + amountPaid_List + ", " + deadline_List + ", " + finalised_List + "\r\n";
									String newLine = projectNumber_List + ", " + projectName_List + ", " + projectType_List + ", " + physicalAddress_List + ", " + erfNumber_List + ", " + totalFee_List + ", " + amountPaid_List + ", " + deadline_List + ", " + true + "\r\n";
									modifyTextFile("/Users/Shivashna/Desktop/HyperionDev - Eclipse work/Poised.java/AllProjects.txt", oldLine, newLine);
								
								
									//Getting all the project information to write to the text file
									//Project details
									double amountPaid = projectList.get(j).getTotalAmountPaid();
									double totalFee = projectList.get(j).getTotalFee();
								
									if (amountPaid == totalFee) {
										System.out.println("The client has paid the full fees for the project.");
										//Calling the method to write the project information to a text file
										addCompletedProjectToTextFile(project, client, contractor, architect);
								
									} else {
										// Client details
										String clientName = clientList.get(j).getName();
										String clientTelephoneNumber = clientList.get(j).getTelephoneNumber();
										String clientEmailAddress = clientList.get(j).getEmailAddress();
										String clientPhysicalAddress = clientList.get(j).getPeoplePhysicalAddress();
										String completionDate = JOptionPane.showInputDialog("Enter the completion date of the project : ");
										project.setNewCompletionDate(completionDate);
										double amountStillToPay = totalFee - amountPaid;
										System.out.println("\nInvoice Number : " + projectList.get(j).getERFNumber());
										System.out.println("\nClient's name : " + clientName + "\nClient's telephone number : " + clientTelephoneNumber + "\nClient's email address : " + clientEmailAddress + "\nClient's physical address : " + clientPhysicalAddress + "\nAmount still outstanding : R" + amountStillToPay);
										//Calling the method to write the project information to a text file
										addCompletedProjectToTextFile(project, client, contractor, architect);
									
									}
								
								}else if (finalised_List == true) {
									System.out.println("\n\nThis project has already been finalised.");
								}
							
							} else if (projectName_input != projectName_List && countProjects == projectList.size()) {
								System.out.println("\nThe project name you have entered does not exist.");
							}
						}
						
						// Displaying the main menu again
						option = menuOption();
					}
					
					
					
//*********************************************** OPTION 6 *********************************************************************************************************************************************************************************************************************************************************************************	
				} else if (option == 6) {
					// This option allows the user to view a list of projects that still need to be completed
					
					// Displaying all the incomplete projects in an easy to read manor
					displayIncompleteProjectDetails(incompleteProjectsList, incompleteClientList, incompleteContractorList, incompleteArchitectList);
					option = menuOption();
			
					
				
//*********************************************** OPTION 7 ********************************************************************************************************************************************************************************************************************************************************************************
				} else if (option == 7) {
					
					// Determining whether a project is overdue or not and adding it the over lists if it is.
					
					for (int countOverdue = 0; countOverdue < incompleteProjectsList.size(); countOverdue ++) {
						
						try {
							// Getting current date
							Date currentDate = new Date();
							
							// Deadline from incomplete projects list
							String deadline_Incomp_List = incompleteProjectsList.get(countOverdue).getDeadline();
							
							// Converting the string date to date format
							DateFormat format = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
							Date deadline_Date = format.parse(deadline_Incomp_List);
							
							// If the current date is passed the deadline, the project will be added to the overdue list
							if (deadline_Date.compareTo(currentDate) < 0) {
								overdueProjectsList.add(incompleteProjectsList.get(countOverdue));
								overdueClientList.add(incompleteClientList.get(countOverdue));
								overdueContractorList.add(incompleteContractorList.get(countOverdue));
								overdueArchitectList.add(incompleteArchitectList.get(countOverdue));	
							}
				
						} catch (java.text.ParseException e) {
							e.printStackTrace();
						}
					}
					
					// Displaying the details of all the overdue projects
					displayOverdueProjectDetails(overdueProjectsList, overdueClientList, overdueContractorList, overdueArchitectList);
					option = menuOption();
					
					
					
//************************************************ OPTION 8 *******************************************************************************************************************************************************************************************************************************************************************************			
				} else if (option == 8) {
					// This option allows the user to exit the program by breaking out of the while loop
					System.out.print("Goodbye");
					break;
				
					
					
//**********************************************************************************************************************************************************************************************************************************************************************************************************************************
				} else {
					// This will execute if the user enters anything else except the numbers "1,2,3,4,5,6,7,8" for the main menu option
					System.out.println("The value you have entered isn't recognised! Please enter a number corresponding to an option from the menu.");
					option = menuOption();
				}
			
			}
			
			} catch (IOException e) {//Buffered reader catch block
				System.out.println("Error with the project text files.");
				e.printStackTrace();
			}
			
			System.out.println("Goodbye");
			
		} catch(NumberFormatException e) {// option - main menu catch block
			System.out.println("Error! You have not entered a number from the menu.");
		}
		
	}

	
	
//*****************************************************************************************************************************************************************************************************************************************************************************************************************************
	// METHODS
	
	/** This method displays all the project details from the projectList, clientList, contractorList and architectList in an easy to read manor
	 * 
	 * @param projectList stores all the existing project details
	 * @param clientList stores all the client details for the existing projects
	 * @param contractorList stores all the contractor details for the existing projects
	 * @param architectList stores all the architect details for the existing projects
	 */
	public static void displayListProjectDetails(ArrayList<Projects> projectList, ArrayList<ProjectPeople> clientList,
			ArrayList<ProjectPeople> contractorList, ArrayList<ProjectPeople> architectList) {
		for (int i = 0; i < projectList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nPROJECT DETAILS : " + projectList.get(i));
			System.out.println("\nCLIENT DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + clientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + contractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR PROJECT " + projectList.get(i).getProjectNumber() + " : " + architectList.get(i));	
		}
	}
	
	
	
	/** This method displays all the incomplete project details from the incompleteProjectList, incompleteClientList, incompleteContractorList and incompleteArchitectList in an easy to read manor
	 * 
	 * @param incompleteProjectsList stores all the incomplete project details
	 * @param incompleteClientList stores all the client details for the incomplete projects
	 * @param incompleteContractorList stores all the contractor details for the incomplete projects
	 * @param incompleteArchitectList stores all the architect details for the incomplete projects
	 */
	public static void displayIncompleteProjectDetails(ArrayList<Projects> incompleteProjectsList, ArrayList<ProjectPeople> incompleteClientList,
			ArrayList<ProjectPeople> incompleteContractorList, ArrayList<ProjectPeople> incompleteArchitectList) {
		for (int i = 0; i < incompleteProjectsList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nINCOMPLETE PROJECT DETAILS : " + incompleteProjectsList.get(i));
			System.out.println("\nCLIENT DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteClientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteContractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR INCOMPLETE PROJECT " + incompleteProjectsList.get(i).getProjectNumber() + " : " + incompleteArchitectList.get(i));	
		}
	}
		
		
		
	/** This method displays all the overdue project details from the overdueProjectList, overdueClientList, overdueContractorList and overdueArchitectList in an easy to read manor
	 * 
	 * @param overdueProjectsList stores all the overdue project details
	 * @param overdueClientList stores all the client details for the overdue projects
	 * @param overdueContractorList stores all the contractor details for the overdue projects
	 * @param overdueArchitectList stores all the architect details for the overdue projects
	 */
	public static void displayOverdueProjectDetails(ArrayList<Projects> overdueProjectsList, ArrayList<ProjectPeople> overdueClientList,
			ArrayList<ProjectPeople> overdueContractorList, ArrayList<ProjectPeople> overdueArchitectList) {
		for (int i = 0; i < overdueProjectsList.size(); i++) {
			// Displaying the list in an easy to read manor
			System.out.println("\n\nOVERDUE PROJECT DETAILS : " + overdueProjectsList.get(i));
			System.out.println("\nCLIENT DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueClientList.get(i));
			System.out.println("\nCONTRACTOR DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueContractorList.get(i));
			System.out.println("\nARCHITECT DETAILS FOR OVERDUE PROJECT " + overdueProjectsList.get(i).getProjectNumber() + " : " + overdueArchitectList.get(i));	
		}
	}
	
	
	
	/** This method is used to display the main menu to the user when it is called
	 * 
	 * @return option the number of the option the user has entered
	 */
	public static Integer menuOption() {
		int option = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the option you would like to select :\n1\t- Create a new project\n2\t- Change the due date of a project\n3\t- Change the total amount paid for a project\n4\t- Update a contractor's contact details\n5\t- Finalise a project\n6\t- View a list of projects that still need to be completed\n7\t- View a list of projects that are passed the due date\n8\t- Exit\nEnter an option number to continue : "));
		return option;
	}

	
	
	/**This method writes the completed projects to a text file called "CompletedProject.java"
	 * 
	 * @param project projectList stores all the existing project details
	 * @param client clientList stores all the client details for the existing projects
	 * @param contractor contractorList stores all the contractor details for the existing projects
	 * @param architect architectList stores all the architect details for the existing projects
	 */
	public static void addCompletedProjectToTextFile(Projects project, ProjectPeople client, ProjectPeople contractor, ProjectPeople architect) {
		
		// Project details
		double amountPaid = project.getTotalAmountPaid();
		double totalFee = project.getTotalFee();
		int projectNumber = project.getProjectNumber();
		String projectName = project.getProjectName();
		String projectType = project.getProjectType();
		String physicalAddress = project.getPhysicalAddress();
		String erfNumber = project.getERFNumber(); 
		String deadline = project.getDeadline();
		String completionDate = project.getCompletionDate();
		Boolean finalised = project.getFinalised();
		
		// Client details
		String clientName = client.getName();
		String clientTelephoneNumber = client.getTelephoneNumber();
		String clientEmailAddress = client.getEmailAddress();
		String clientPhysicalAddress = client.getPeoplePhysicalAddress();
		
		// Contractor details
		String contractorName = contractor.getName();
		String contractorTelephoneNumber = contractor.getTelephoneNumber();
		String contractorEmailAddress = contractor.getEmailAddress();
		String contractorPhysicalAddress = contractor.getPeoplePhysicalAddress();
		
		// Architect details
		String architectName = architect.getName();
		String architectTelephoneNumber = architect.getTelephoneNumber();
		String architectEmailAddress = architect.getEmailAddress();
		String architectPhysicalAddress = architect.getPeoplePhysicalAddress();
		
		try {
			
			FileWriter compProjectWriter = new FileWriter("CompletedProject.txt",true);
			FileWriter compProjectPeopleWriter = new FileWriter("CompletedProjectPeople.txt",true);
			
			String projectString = projectNumber + ", " + projectName + ", " + projectType + ", " + physicalAddress + ", " + erfNumber + ", " + totalFee + ", " + amountPaid + ", " + deadline + ", " + completionDate + ", " + finalised + "\r\n";
			String projectPeopleString = projectNumber + ", " + clientName + ", " + clientTelephoneNumber + ", " + clientEmailAddress + ", " + clientPhysicalAddress + ", " + contractorName + ", " + contractorTelephoneNumber + ", " + contractorEmailAddress + ", " + contractorPhysicalAddress + ", " + architectName + ", " + architectTelephoneNumber + ", " + architectEmailAddress + ", " + architectPhysicalAddress + "\r\n";
			
			compProjectWriter.write(projectString);
			compProjectPeopleWriter.write(projectPeopleString);
			compProjectWriter.close();
			compProjectPeopleWriter.close();
			
			System.out.println("The project information has been written to the text file \"CompletedProject.txt\".");
			
		} catch(Exception e) {
			System.out.println("Error");
		}
		
	}
	
	
	
	/**This method writes all projects to a text file
	 * 
	 * @param project projectList stores all the existing project details
	 * @param client clientList stores all the client details for the existing projects
	 * @param contractor contractorList stores all the contractor details for the existing projects
	 * @param architect architectList stores all the architect details for the existing projects
	 */
	public static void addProjectToTextFile(Projects project, ProjectPeople client, ProjectPeople contractor, ProjectPeople architect) {
		
		// Project details
		double amountPaid = project.getTotalAmountPaid();
		double totalFee = project.getTotalFee();
		int projectNumber = project.getProjectNumber();
		String projectName = project.getProjectName();
		String projectType = project.getProjectType();
		String physicalAddress = project.getPhysicalAddress();
		String erfNumber = project.getERFNumber(); 
		String deadline = project.getDeadline();
		Boolean finalised = project.getFinalised();
		
		// Client details
		String clientName = client.getName();
		String clientTelephoneNumber = client.getTelephoneNumber();
		String clientEmailAddress = client.getEmailAddress();
		String clientPhysicalAddress = client.getPeoplePhysicalAddress();
		
		// Contractor details
		String contractorName = contractor.getName();
		String contractorTelephoneNumber = contractor.getTelephoneNumber();
		String contractorEmailAddress = contractor.getEmailAddress();
		String contractorPhysicalAddress = contractor.getPeoplePhysicalAddress();
		
		// Architect details
		String architectName = architect.getName();
		String architectTelephoneNumber = architect.getTelephoneNumber();
		String architectEmailAddress = architect.getEmailAddress();
		String architectPhysicalAddress = architect.getPeoplePhysicalAddress();
		
		try {
			
			FileWriter projectWriter = new FileWriter("AllProjects.txt",true);
			FileWriter projectPeopleWriter = new FileWriter("AllProjectPeople.txt",true);
			
			String projectString = projectNumber + ", " + projectName + ", " + projectType + ", " + physicalAddress + ", " + erfNumber + ", " + totalFee + ", " + amountPaid + ", " + deadline + ", " + finalised + "\r\n";
			String projectPeopleString = projectNumber + ", " + clientName + ", " + clientTelephoneNumber + ", " + clientEmailAddress + ", " + clientPhysicalAddress + ", " + contractorName + ", " + contractorTelephoneNumber + ", " + contractorEmailAddress + ", " + contractorPhysicalAddress + ", " + architectName + ", " + architectTelephoneNumber + ", " + architectEmailAddress + ", " + architectPhysicalAddress + "\r\n";
			
			projectWriter.write(projectString);
			projectPeopleWriter.write(projectPeopleString);
			projectWriter.close();
			projectPeopleWriter.close();
			
			System.out.println("The project information has been written to the text file \"AllProjects.txt\" and client, contractor and architect details have been written to \"AllProjectPeople.txt\".");
			
		} catch(Exception e) {
			System.out.println("Error");
		}
	}
	
	
	
	/** This method updates information in the specified text file for all options 1, 2, 3, 4 or 5
	 * 
	 * @param filePath path to the text file to be edited
	 * @param oldString string that needs to be replaced
	 * @param newString this string will replace the old string
	 */
	static void modifyTextFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
         
        BufferedReader reader = null;
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent 
            String newContent = oldContent.replace(oldString, newString);
             
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                //Closing the resources 
                reader.close(); 
                writer.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }	
	
}