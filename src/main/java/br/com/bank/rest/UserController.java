package br.com.bank.rest;

import br.com.bank.persistence.dto.UserDto;
import br.com.bank.persistence.model.User;
import br.com.bank.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


import java.util.List;

@ApplicationScoped
@Path("/v1/users")
public class UserController {


    @Inject
    UserService service;

    @GET
    public Response listarUserAll() {
        List<User> user = this.service.getUsers();
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Path("/{id}")
    public Response listarUser(@PathParam("id") Long id) {
        User user = this.service.getUser(id);
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    public Response criarUser(@Valid UserDto userData) {
        this.service.criarUser(userData);
        return Response.status(Response.Status.CREATED).entity("ok").build();
    }
}