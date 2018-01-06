package hotels.repository;

import hotels.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return entityManager.createQuery("SELECT u from User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

}
