package br.com.bank.persistence.dao;

import br.com.bank.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class UserDao implements Dao<User> {

    @Inject
    EntityManager em;

    @Override
    public Optional<User> get(Long id) {
       try {
           var query = this.em.createQuery("SELECT u FROM User u WHERE id = :id", User.class);
           return Optional.of(query.setParameter("id", id).getSingleResult());
       } catch (NoResultException ex){
          return Optional.empty();
       }
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
