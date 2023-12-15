package br.com.bank.persistence.dto;

import java.math.BigDecimal;

public class AccountDetailsDto {

    private Long numeroConta;

    Integer age;

    String telefone;

    String endereco;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private String accountType;

    private BigDecimal saldoAtual;
    private String nomeUsuario;

    public Long getNumeroConta() {
        return numeroConta;
    }



    public String getAccountType() {
        return accountType;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
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