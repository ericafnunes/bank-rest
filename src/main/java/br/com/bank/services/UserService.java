package br.com.bank.services;

import br.com.bank.exceptions.ValorInsuficienteException;
import br.com.bank.persistence.dao.UserDao;
import br.com.bank.persistence.dto.AccountDetailsDto;
import br.com.bank.persistence.dto.AccountDto;
import br.com.bank.persistence.dto.AccountListDto;
import br.com.bank.persistence.dto.UserDto;
import br.com.bank.persistence.model.Account;
import br.com.bank.persistence.model.AccountType;
import br.com.bank.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;


    public void criarUser(@Valid UserDto userData) {
        User user = new User();
        user.setName(userData.getName());
        user.setAge(userData.getAge());
        user.setTelefone(userData.getTelefone());
        user.setEndereco(userData.getEndereco());
        this.userDao.save(user);
    }




    public void criarConta(Long userId, AccountDto accountDto) {
        Optional<User> userOptional = userDao.get(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Account account = new Account();
            account.setAccountType(AccountType.valueOf(accountDto.getAccountType()));
            account.setUser(user);
            user.setAccount(account);
            userDao.update(user);
        }
    }


    public void depositarValor(Long userId, BigDecimal valor) {
        Optional<User> userOptional = userDao.get(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Account account = user.getAccount();

            if (account != null) {
                BigDecimal saldoAtual = account.getSaldo();

                if (account.getAccountType() == AccountType.POUPANCA) {
                    valor = valor.add(new BigDecimal("0.50"));
                }

                account.setSaldo(saldoAtual.add(valor));
                userDao.update(user);
            }
        }
    }

    public void sacarValor(Long userId, BigDecimal valor) {
        Optional<User> userOptional = userDao.get(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Account account = user.getAccount();

            if (account != null) {
                BigDecimal saldoAtual = account.getSaldo();

                if (saldoAtual.compareTo(valor) >= 0) {
                    account.setSaldo(saldoAtual.subtract(valor));
                    userDao.update(user);
                } else {
                    throw new ValorInsuficienteException("Saldo insuficiente");
                }
            }
        }
    }




    public List<AccountListDto> listarContas() {
        List<AccountListDto> accountList = new ArrayList<>();

        List<User> userList = userDao.getAll();

        for (User user : userList) {
            Account account = user.getAccount();

            if (account != null) {
                AccountListDto accountDto = new AccountListDto();
                accountDto.setNumeroConta(account.getId());
                accountDto.setTipoConta(account.getAccountType());
                accountDto.setSaldoAtual(account.getSaldo());
                accountDto.setNomeUsuario(user.getName());
                accountList.add(accountDto);
            }
        }

        return accountList;
    }


    public Optional<AccountDetailsDto> obterDetalhesContaUsuario(Long userId) {
        Optional<User> userOptional = userDao.get(userId);

        return userOptional.map(user -> {
            Account account = user.getAccount();

            if (account != null) {
                AccountDetailsDto accountDetails = new AccountDetailsDto();
                accountDetails.setNumeroConta(account.getId());
                accountDetails.setAccountType(account.getAccountType().toString());
                accountDetails.setSaldoAtual(account.getSaldo());
                accountDetails.setNomeUsuario(user.getName());
                accountDetails.setAge(user.getAge());
                accountDetails.setTelefone(user.getTelefone());
                accountDetails.setEndereco(user.getEndereco());

                return accountDetails;
            }

            return null;
        });
    }

    public List<User> getUsers() {
        return this.userDao.getAll();
    }

    public Optional<User> getUser(Long id) {
        return this.userDao.get(id);
    }
}


