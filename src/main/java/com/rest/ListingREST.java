package com.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dao.ListingDAO;
import com.dto.ErrorView;
import com.dto.Listing;

@Path("/listing")
public class ListingREST {
	
	static List<Listing> listings;
	
	static {
		/*listings = new ArrayList<Listing>();
		Listing listing1 = new Listing();
		listing1.setCategory("Cars");
		listing1.setName("Maruthi Swift");
		listing1.setDescription("Well maintained swift for sale");
		listing1.setContactDetails("Chaitanya - +919949338844");
		listings.add(listing1);
		
		Listing listing2 = new Listing();
		listing2.setCategory("Bikes");
		listing2.setName("Bajaj Pulsar");
		listing2.setDescription("Well maintained pulsar for sale");
		listing2.setContactDetails("Karthik - +919949338844");
		listings.add(listing2);
		
		Listing listing3 = new Listing();
		listing3.setCategory("Fridge");
		listing3.setName("BPL 180L");
		listing3.setDescription("Well maintained fridge for sale");
		listing3.setContactDetails("Naresh - +919949338844");
		listings.add(listing3);*/
		
		try {
			ListingDAO.createListingTable();
		} catch (SQLException e) {
			System.out.println("Error in setting up the DB");
		}
	}
	
	@GET
	@Path("/getAllListings")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllListings () {
		ListingDAO dao = new ListingDAO();
		try {
			listings = dao.getAllListing();
		return Response.status(Response.Status.OK).entity(listings).build();
		} catch (SQLException e) {
			ErrorView errorView = new ErrorView();
			errorView.setErrorStatus("SQL Exception");
			errorView.setErrorDescription("Unable to retrieve the listings. Please try after sometime.");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorView).build();
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addListing (Listing listing) {
		if(listing != null) {
			listings.add(listing);
			return Response.status(Response.Status.OK).entity(listings).build();
		} else {
			ErrorView errorView = new ErrorView();
			errorView.setErrorStatus("Listing Not Available");
			errorView.setErrorDescription("Listing entered is empty");
			return Response.status(Response.Status.BAD_REQUEST).entity(errorView).build();
		}
	}
}
