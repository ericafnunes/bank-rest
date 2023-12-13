package br.com.bank.persistence.dao;

import br.com.bank.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
public class UserDao implements Dao<User> {

    @Inject
    EntityManager em;

    @Override
    public User get(Long id) {
       return this.em.find(User.class,id);
    }

    @Override
    public List<User> getAll() {
        var query = this.em.createQuery("SELECT u FROM User u", User.class);
       return query.getResultList();
    }

    @Override
    @Transactional
    public void save(User user) {
        this.em.persist(user);
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
