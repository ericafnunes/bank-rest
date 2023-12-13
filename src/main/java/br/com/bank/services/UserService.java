package br.com.bank.services;

import br.com.bank.persistence.dao.UserDao;
import br.com.bank.persistence.dto.UserDto;
import br.com.bank.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;
    public void criarUser(UserDto userData) {
        User user = new User();
        user.setName(userData.getName());
        user.setAge(userData.getAge());
        user.setTelefone(userData.getTelefone());
        user.setEndereco(userData.getEndereco());
        this.userDao.save(user);
    }

    public List<User> getUsers() {
        return this.userDao.getAll();
    }

    public Optional<User> getUser(Long id){
        return this.userDao.get(id);
    }
}
