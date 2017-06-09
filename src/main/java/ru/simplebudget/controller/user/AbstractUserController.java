package ru.simplebudget.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.user.UserService;

import java.util.List;

public class AbstractUserController {

    private final
    UserService service;

    @Autowired
    public AbstractUserController(UserService service) {
        this.service = service;
    }

    List<User> getAll() {
        return service.getAll();
    }

    User getById(Long id){
        return service.getById(id);
    }
    User getByEmail(String email){return service.getByEmail(email);}
    User getByNickName(String nickName){return service.getByNickName(nickName);}
    User saveOrUpdate(User user){
       return service.save(user);
    }
    boolean delete(Long id){
        return service.delete(id);
    }
}
