package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dto.ErrorView;
import com.dto.User;

@Path("/login")

public class LoginREST {

@POST
@Path("/authenticate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response authenticate(User user) {
	if(user!=null) {
		if(user.getUsername().equalsIgnoreCase("chaitanyapotla") & user.getPassword().equals("password@12")) {
			return Response.status(Response.Status.OK).build();
		} else {
			ErrorView errorView = new ErrorView();
			errorView.setErrorStatus("Wrong Credentails");
			errorView.setErrorDescription("Username / Password is incorrect");
			return Response.status(Response.Status.UNAUTHORIZED).entity(errorView).build();
		}
	} else {
		ErrorView errorView = new ErrorView();
		errorView.setErrorStatus("Bad Request");
		errorView.setErrorDescription("Userdetails are missing");
		return Response.status(Response.Status.BAD_REQUEST).entity(errorView).build();
	}
}

}
