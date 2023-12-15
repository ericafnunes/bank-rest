package br.com.bank.persistence.dto;



import br.com.bank.persistence.model.AccountType;
import java.math.BigDecimal;

public class AccountListDto {
    private Long numeroConta;
    private AccountType tipoConta;
    private BigDecimal saldoAtual;
    private String nomeUsuario;

    // getters e setters

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public AccountType getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(AccountType tipoConta) {
        this.tipoConta = tipoConta;
    }

    public BigDecimal getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(BigDecimal saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
}
