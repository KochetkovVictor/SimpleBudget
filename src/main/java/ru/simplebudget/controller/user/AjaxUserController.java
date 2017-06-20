package ru.simplebudget.controller.user;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.user.UserService;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/users")
public class AjaxUserController extends AbstractUserController {

    public AjaxUserController(UserService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") Long id) {
        return super.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveOrUpdate(User user, HttpServletRequest request) {
        System.out.println(request.getParameter("nickname"));
        if (user.getId() == 0) {
            user.setId(null);
        }
        super.saveOrUpdate(user);
    }

    @RequestMapping(value = "/byEmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }

    @RequestMapping(value = "/byNickName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByNickName(@RequestParam("nickName") String nickName) {
        return super.getByNickName(nickName);
    }
}
