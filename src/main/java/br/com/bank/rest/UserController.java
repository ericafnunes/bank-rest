package br.com.bank.rest;

import br.com.bank.services.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/v1/usuarios")
public class UserController {


    @Inject
    UserService userService;

    @GET
    public Response listarUser() {
        return Response.status(Response.Status.OK).entity("ok").build();
    }
    @POST
    public Response criarUser() {
        return Response.status(Response.Status.OK).entity("ok").build();
    }
}