package com.patient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class patient {
	private String email;
	private String name;
	private String password;
	private String address;
	private String contact;
	private int age;
	private String bloodGroup;
	Connection con;
	Scanner sc = new Scanner(System.in);
	BufferedReader br;
	patient(Connection con,String email,String password,String name,int age,String contact,String address,String bloodGroup)
	{
		this.con =con;
		this.email = email;
		this.name = name;
		this.password=password;
		this.address = address;
		this.contact = contact;
		this.age = age;
		this.bloodGroup = bloodGroup;

		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public void viewPatientMenu() throws SQLException, IOException {
		while(true) {
			System.out.println("\nChoose your activity\n--------------------------\n");	
			System.out.println("1. View profile");
			System.out.println("2. Update profile");
			System.out.println("3. View all doctors");
			System.out.println("4. Book Appointment");
			System.out.println("5. View Appointment");
			System.out.println("6. View Prescriptions");
			System.out.println("7. Exit");

			System.out.print("\nEnter choice: ");
			int choice = sc.nextInt();

			switch(choice) {
			case 1: viewProfile();
			break;
			case 2: updateProfile();
			break;
			case 3: viewDoctors();
			break;
			case 4: bookAppointment();
			break;
			case 5: viewAppointments();
			break;
			case 6: viewPrescriptions();
			break;
			case 7: System.out.println("Thank you for using our services!");
			con.close();
			System.exit(0);
			default: System.out.println("Invalid choice! Please try again.");

			}
		}

	}

	private void viewProfile()
	{
		System.out.println("Email: "+this.email);
		System.out.println("Name:"+this.name);
		System.out.println("Address: "+this.address);
		System.out.println("Contact: "+this.contact);
		System.out.println("Age: "+this.age);
		System.out.println("Blood Group: "+this.bloodGroup);
	}
	private void patientUpdateMenu() {
		System.out.println("\nUpdate Patient Details\n--------------------------\n");	
		System.out.println("1. Edit Name");
		System.out.println("2. Edit Email");
		System.out.println("3. Edit Password");
		System.out.println("4. Edit Age");
		System.out.println("5. Edit Contact");
		System.out.println("6. Edit Address");
		System.out.println("7. Edit Blood Group");
		System.out.println("8. Exit\n");

	}
	private void updateProfile() throws IOException, SQLException {
		while(true) {
			patientUpdateMenu();

			System.out.print("Enter choice: ");
			int choice = sc.nextInt();

			switch(choice) {
			case 1: updateName();
			break;
			case 2: updateEmail();
			break;
			case 3: updatePassword();
			break;
			case 4: updateAge();
			break;
			case 5: updateContact();
			break;
			case 6: updateAddress();
			break;
			case 7: updateBloodGroup();
			break;
			case 8: System.out.println("Thank you for using our services!");
			return;
			default: System.out.println("Invalid choice! Please try again.");
			}
		}
	}
	private void update_curr_instance()
	{

		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("select * from patient where email_id ='"+email+"' and password='"+password+"' and  status = 'approved'");

			//			rs.next();
			//			System.out.println(rs.getString(1));
			boolean f =false;
			//			rs.next();
			//			System.out.println(rs.getString(1));
			if(rs.next())
			{

				email = rs.getString(1);
				password = rs.getString(2);
				name = rs.getString(3);
				age = rs.getInt(4);
				contact = rs.getString(5);
				address = rs.getString(6);
				bloodGroup = rs.getString(7);

			}
			else {
				System.out.println("You have Changed either email or password please login again .\n");
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void updateBloodGroup() throws IOException, SQLException {
		System.out.print("Enter Blood Group: ");
		String bg = br.readLine().toUpperCase();

		String query1 = "update patient set blood_group = '" + bg + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Blood Group Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Blood Group could not be updated! Please try again");
			updateBloodGroup();
			return;
		}
	}

	private void updateAddress() throws IOException, SQLException {
		System.out.print("Enter Address: ");
		String address = br.readLine();

		String query1 = "update patient set address = '" + address + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Address Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Address could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updateContact() throws IOException, SQLException {
		System.out.print("Enter Contact: ");
		String contact = br.readLine();

		String query1 = "update patient set contact = '" + contact + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Contact Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Contact could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updateAge() throws IOException, SQLException {
		System.out.print("Enter Age: ");
		int age = sc.nextInt();

		String query1 = "update patient set address = " + age + " where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Age Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Age could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updatePassword() throws IOException, SQLException {
		Statement smt = (Statement) con.createStatement();
		ResultSet rs = ((java.sql.Statement) smt).executeQuery("select password from patient where email_id='" + this.email +"'");

		String o_pass="";
		while(rs.next()){
			//		    int eid = rs.getInt(1);
			o_pass = rs.getString(1);
			//		    System.out.println(rs.getString(1));
		}

		System.out.print("Enter old password: ");
		String old_pass = br.readLine();

		if (!o_pass.equals(old_pass)) {
			System.out.println("You have entered the wrong password!");
			return;
		}

		String new_pass, n_pass;
		int cnt = 0;
		do {
			if(cnt++ >= 3)
			{
				System.out.println("3 incorrect attempts. You will receive a $5000 penalty!");
				return;
			}
			System.out.print("Enter new password: ");
			new_pass = br.readLine();
			System.out.print("Confirm new password: ");
			n_pass = br.readLine();
		} while(!new_pass.equals(n_pass));


		String query1 = "update patient set password = '" + new_pass + "' where email_id = '"+this.email+"'";


		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Password Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Password could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updateEmail() throws IOException, SQLException {
		System.out.print("Enter email: ");
		String em_id = br.readLine();

		String query1 = "update patient set email_id = '" + em_id + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Email Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Patient Email could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updateName() throws IOException, SQLException {
		System.out.print("Enter Name: ");
		String name = br.readLine();

		String query1 = "update patient set name = '" + name + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Patient Name Updated!");
		}
		else {
			System.out.println("Patient Name could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void viewDoctors() {
		String query ="select name,age,specialization,fees,start_time,end_time,contact,address from doctor";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(query);
			boolean doctor_exist = false;

			//            if( System.out.println("No Doctor is there");
			while(rs.next()) {
				doctor_exist = true;
				String dname = rs.getString(1);
				int dage = rs.getInt(2);
				String specilization = rs.getString(3);
				int fees = rs.getInt(4);
				String start_time = rs.getString(5);
				String end_time = rs.getString(6);
				String dcontact = rs.getString(7);
				String daddress = rs.getString(8);


				System.out.println("Doctor name:"+dname+"\tage:"+dage+"\t Specilization:"+specilization+"\t Fees:"+fees+" \tSlot Start time:"+start_time
						+"\tSlot End time:"+end_time+"\t Contact:"+contact+"\t Address:"+address);
			}
			if(doctor_exist == false) {
				System.out.println("No Doctor is there");
			}

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	private void bookAppointment()
	{
		String query ="select specialization from doctor";
		Statement stmt;
		System.out.println("Enter the specilization from the given list:");
		sc.nextLine();
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String spec = rs.getString(1);
				System.out.println(spec);
			}
			String choice = sc.next();
			query = "select email_id,name,age,specialization,fees,start_time,end_time,contact,address from doctor where specialization = '"+choice+"'";
			rs = stmt.executeQuery(query);
			int count=0;
			while(rs.next()) {
				String email = rs.getString(1);
				String dname = rs.getString(2);
				int dage = rs.getInt(3);
				String specilization = rs.getString(4);
				int fees = rs.getInt(5);
				int start_time = rs.getInt(6);
				int end_time = rs.getInt(7);
				long dcontact = rs.getLong(8);
				String daddress = rs.getString(9);

				count++;
				System.out.println("Doctor Email id:"+email+"\tDoctor name:"+dname+"\tage:"+dage+"\t Specilization:"+specilization+"\t Fees:"+fees+" \tSlot Start time:"+start_time
						+"\tSlot End time:"+end_time+"\t Contact:"+contact+"\t Address:"+address);
			}
			if(count==0)
			{
				System.out.println("Enter correct specialization...");
				bookAppointment();
			}
			//			System.out.println("Enter the email id of doctor for which you want to book an appointment...");
			//			sc.nextLine();
			//			choice = sc.next();


		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}*/

	private void bookAppointment()
	{
		String query ="select specialization from doctor";
		Statement stmt;
		System.out.println("Enter the specilization from the given list:");
		sc.nextLine();
		try {
			stmt = con.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				String spec = rs.getString(1);
				System.out.println(spec);
			}
			String choice = sc.next();
			query = "select email_id,name,age,specialization,fees,start_time,end_time,contact,address from doctor where specialization = '"+choice+"'";
			rs = stmt.executeQuery(query);
			int count=0;
			while(rs.next()) {
				String email = rs.getString(1);
				String dname = rs.getString(2);
				int dage = rs.getInt(3);
				String specilization = rs.getString(4);
				int fees = rs.getInt(5);
				String start_time = rs.getString(6);
				String end_time = rs.getString(7);
				String dcontact = rs.getString(8);
				String daddress = rs.getString(9);

				count++;
				System.out.println("Doctor Email id:"+email+"\tDoctor name:"+dname+"\tage:"+dage+"\t Specilization:"+specilization+"\t Fees:"+fees+" \tSlot Start time:"+start_time
						+"\tSlot End time:"+end_time+"\t Contact:"+contact+"\t Address:"+address);
			}
			if(count==0)
			{
				System.out.println("Enter correct specialization...");
				bookAppointment();
			}
			System.out.println("Enter the email id of doctor for which you want to book an appointment...");
			sc.nextLine();
			choice = sc.next();

			// add doctor check condition



			slots st = new slots();
			ArrayList <String> slotdate = st.dateGenerator();
			st.fetchdoctorslots(stmt,slotdate,choice);

			Iterator<String> iter = slotdate.iterator();
			int counter = 0;
			System.out.println("All The Available Dates for Doctor");
			while(iter.hasNext()) {
				System.out.println(counter +  " " + iter.next());
				counter++;

			}

			System.out.println("Enter the date s .no ");
			int slotdatepick = sc.nextInt();
			String selectedDate = null;
			switch(slotdatepick) {
			case 0:
				selectedDate = slotdate.get(0);
				break;
			case 1:
				selectedDate = slotdate.get(1);
				break;
			case 2:
				selectedDate = slotdate.get(2);
				break;

			case 3:
				selectedDate = slotdate.get(3);
				break;

			case 4:
				selectedDate = slotdate.get(4);
				break;

			default:
				System.out.print("Invalid Date Selected");



			}


			System.out.print(selectedDate);

			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");

			SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yy");

			String reformattedStr = null;

			try {

				reformattedStr = myFormat.format(fromUser.parse(selectedDate));

				//System.out.print(reformattedStr);

			} catch (Exception e) {

				e.printStackTrace();

			}
			Statement smt = con.createStatement();
			st.fetchdoctortimeslots(stmt,smt, reformattedStr,choice);

			System.out.println("Please Enter Your slots preference");

			int timeslotprefer = sc.nextInt();



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	private void viewAppointments() {

	}
	private void viewPrescriptions() {

	}





}
