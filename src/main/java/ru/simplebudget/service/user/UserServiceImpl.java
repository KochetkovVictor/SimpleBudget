package ru.simplebudget.service.user;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
import ru.simplebudget.repository.user.UserRepository;
import ru.simplebudget.utils.EmailValidator;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final
    UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoggedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;
        if (EmailValidator.validate(login)) {
            user = repository.getByEmail(login);
        } else {
            user = repository.getByNickName(login);
        }
        if (user == null) {
            throw new UsernameNotFoundException("User with email " + login + " not found");
        }
        return new LoggedUser(user);
    }

    @Override
    public User getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public User getByNickName(String nickName) {
        return repository.getByNickName(nickName);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
