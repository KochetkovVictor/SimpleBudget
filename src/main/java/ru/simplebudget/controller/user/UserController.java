package ru.simplebudget.controller.user;

import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.user.UserService;
import org.springframework.http.MediaType;

import java.util.List;


@RestController
@RequestMapping(value="/ajax/users")
public class UserController extends AbstractUserController{

    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll(){return super.getAll();}

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") Long id){return super.delete(id);}

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") Long id){return super.getById(id);}

    @RequestMapping(method = RequestMethod.POST)
    public User saveOrUpdate(User user){
        if(user.getId()==0){
            user.setId(null);
        }
        super.saveOrUpdate(user);
        return user;
    }
}
