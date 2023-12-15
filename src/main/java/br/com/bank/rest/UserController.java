package br.com.bank.rest;



import br.com.bank.exceptions.NotFoundException;
import br.com.bank.persistence.dto.AccountDetailsDto;
import br.com.bank.persistence.dto.AccountDto;
import br.com.bank.persistence.dto.AccountListDto;
import br.com.bank.persistence.dto.UserDto;
import br.com.bank.persistence.model.User;
import br.com.bank.services.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import br.com.bank.exceptions.ValorInsuficienteException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Path("/v1/usuarios")
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
        User user = this.service.getUser(id).orElseThrow(() -> {
            return new NotFoundException("Não foi encontrado o usuario!");
        });
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @POST
    public Response criarUser(@Valid UserDto userData) {
        this.service.criarUser(userData);
        return Response.status(Response.Status.CREATED).entity("Usuario criado com sucesso!").build();
    }
    @POST
    @Path("/{id}/contas")
    public Response criarConta(@PathParam("id") Long id, AccountDto accountDto) {
        this.service.criarConta(id,accountDto);
        return Response.status(Response.Status.CREATED).entity("Sua conta foi criada com sucesso!").build();
    }

        @POST
        @Path("/{id}/depositar")
        public Response depositarValor(@PathParam("id") Long id, BigDecimal valor) {
            this.service.depositarValor(id, valor);
            return Response.status(Response.Status.OK).entity("Depósito realizado com sucesso").build();
        }

    @POST
    @Path("/{id}/sacar")
    public Response sacarValor(@PathParam("id") Long id, BigDecimal valor) {
        try {
            this.service.sacarValor(id, valor);
            return Response.status(Response.Status.OK).entity("Seu saque foi realizado").build();
        } catch (ValorInsuficienteException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/contas")
    public Response listarContas() {
        List<AccountListDto> accountDetailsList = this.service.listarContas();
        return Response.status(Response.Status.OK).entity(accountDetailsList).build();
    }

    @GET
    @Path("/{userId}/contas/{accountId}")
    public Response obterDetalhesConta(@PathParam("userId") Long userId, @PathParam("accountId") Long accountI) {
        Optional<AccountDetailsDto> accountDetails = this.service.obterDetalhesContaUsuario(userId);
        if (accountDetails.isPresent()) {
            return Response.status(Response.Status.OK).entity(accountDetails.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Nao foi possivel localizar informacoes da conta").build();
        }
    }
}








