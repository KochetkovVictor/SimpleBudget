package ru.simplebudget.service.user;


import ru.simplebudget.model.user.User;

public interface UserService {
    User getById(Long id);
    User getByEmail(String email);
    User getByNickName(String nickName);
    User save(User user);
    boolean delete(Long id);
}
