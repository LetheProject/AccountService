package org.amoseman.accountservice.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amoseman.accountservice.dao.AccountDAO;
import org.amoseman.accountservice.dao.exception.UserDoesNotExistException;
import org.amoseman.accountservice.dao.exception.UsernameAlreadyInUseException;
import org.amoseman.accountservice.pojo.AccountCreationRequest;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final AccountDAO accountDAO;

    public AccountResource(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @POST
    @Path("/request")
    public Response request(AccountCreationRequest request) {
        try {
            accountDAO.request(request);
            return Response.accepted().build();
        }
        catch (UsernameAlreadyInUseException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), String.format("Username %s already in use", request.getUsername())).build();
        }
    }

    @POST
    @Path("/request/{username}")
    public Response accept(@PathParam("username") String username) {
        try {
            accountDAO.accept(username);
            return Response.ok().build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), String.format("No request for %s exists", username)).build();
        }
    }

    @DELETE
    @Path("/request/{username}")
    public Response reject(@PathParam("username") String username) {
        try {
            accountDAO.reject(username);
            return Response.ok().build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), String.format("No request for %s exists", username)).build();
        }
    }

    @GET
    @Path("/request")
    public Response listRequests() {
        return Response.ok(accountDAO.listRequests()).build();
    }

    @DELETE
    @Path("/{username}")
    public Response delete(@PathParam("username") String username) {
        try {
            accountDAO.delete(username);
            return Response.ok().build();
        }
        catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), String.format("User %s does not exist", username)).build();
        }
    }
}
