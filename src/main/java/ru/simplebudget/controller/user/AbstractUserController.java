package ru.simplebudget.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.user.UserService;

import java.util.List;

public abstract class AbstractUserController {

    private final
    UserService service;

    @Autowired
    public AbstractUserController(UserService service) {
        this.service = service;
    }

    List<User> getAll() {
        return service.getAll();
    }

    public User getById(Long id){
        return service.getById(id);
    }
    public User getByEmail(String email){return service.getByEmail(email);}
    public User getByNickName(String nickName){return service.getByNickName(nickName);}
    protected void saveOrUpdate(User user){
        service.save(user);
    }
    boolean delete(Long id){
        return service.delete(id);
    }
}
