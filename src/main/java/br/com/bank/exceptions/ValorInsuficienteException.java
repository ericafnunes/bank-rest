package br.com.bank.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;


public class ValorInsuficienteException extends RuntimeException {
    public ValorInsuficienteException(String message) {
        super("Sem saldo disponivel para transacao"+ message);
    }
}
