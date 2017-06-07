package ru.simplebudget.service.user;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.simplebudget.model.user.LoggedUser;
import ru.simplebudget.model.user.User;
import ru.simplebudget.repository.user.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    private
    UserRepository repository;

    @Override
    public LoggedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=repository.getByEmail(email);
        if(user==null){throw new UsernameNotFoundException("User with email "+email+" not found");}
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
}
