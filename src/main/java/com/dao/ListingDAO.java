package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dto.Listing;

public class ListingDAO {
	static Connection conn = null;
	static int counter = 3;

	public static void connectionToDerby() throws SQLException {
		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		String dbUrl = "jdbc:derby:C:\\Users\\demo;create=true";
		conn = DriverManager.getConnection(dbUrl);
	}

	public static void createListingTable() throws SQLException {
		if (conn == null) {
			connectionToDerby();
		}
		Statement stmt = conn.createStatement();

		// create table
		stmt.executeUpdate(
				"Create table listings (id int primary key, name varchar(128), category varchar(128), description varchar(256), contactdetails varchar(128))");

		// insert 3 rows
		stmt.executeUpdate(
				"insert into listings values (1,'Maruthi Swift', 'Cars','Well maintained swift for sale', 'Chaitanya - +919949338844')");
		stmt.executeUpdate(
				"insert into listings values (2,'Bajaj Pulsar', 'Bikes','Well maintained pulsar for sale', 'Karthik - +919949338844')");
		stmt.executeUpdate(
				"insert into listings values (3,'BPL 180L', 'Fridge','Well maintained fridge for sale', 'Naresh - +919949338844')");
	}

	public List<Listing> getAllListing() throws SQLException {
		if (conn == null) {
			connectionToDerby();
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM listings");
		List<Listing> listings = new ArrayList<Listing>();
		while (rs.next()) {
			Listing listing = new Listing();
			listing.setName(rs.getString("name"));
			listing.setCategory(rs.getString("category"));
			listing.setDescription(rs.getString("description"));
			listing.setContactDetails(rs.getString("contactdetails"));
			listings.add(listing);
		}
		return listings;
	}
	
	public void addListing(Listing listing) throws SQLException {
		if (conn == null) {
			connectionToDerby();
		}
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("insert into listings values ("+ getPrimaryKey() +",'" + listing.getName() +"', '" + listing.getCategory() +"','" + listing.getDescription() +"', '" + listing.getContactDetails() +"')");
	}

	private int getPrimaryKey() {
		counter = counter + 1;
		return counter;
	}

}
