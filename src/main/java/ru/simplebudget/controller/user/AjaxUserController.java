package ru.simplebudget.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.simplebudget.model.user.User;
import ru.simplebudget.service.user.UserService;
import org.springframework.http.MediaType;


import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/ajax/users")
public class AjaxUserController extends AbstractUserController {

    private final MessageSource messageSource;

    @Autowired
    public AjaxUserController(UserService service, MessageSource messageSource) {
        super(service);
        this.messageSource = messageSource;
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
    public void saveOrUpdate(@Valid User user) {
      try{   if (user.getId() == 0) {
            user.setId(null);
        }
        super.saveOrUpdate(user);}
      catch (DataIntegrityViolationException ex) {
          throw new DataIntegrityViolationException(messageSource.getMessage("exception.duplicate_email", null, LocaleContextHolder.getLocale()));
      }
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
