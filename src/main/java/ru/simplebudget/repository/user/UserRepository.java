package ru.simplebudget.repository.user;


import ru.simplebudget.model.user.User;

import java.util.List;

public interface UserRepository {
    User getById(Long id);
    User getByEmail(String email);
    User getByNickName(String nickName);
    User save(User user);
    boolean delete(Long id);
    List<User> getAll();
}
