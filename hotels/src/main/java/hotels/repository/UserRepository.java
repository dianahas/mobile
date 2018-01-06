package hotels.repository;

import hotels.model.User;

public interface UserRepository {
    User getUserById(Integer userId);

    User getUserByEmail(String email);

    void addUser(User user);
}
