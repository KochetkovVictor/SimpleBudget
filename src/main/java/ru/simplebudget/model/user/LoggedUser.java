package ru.simplebudget.model.user;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class LoggedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private User user;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object authUser = auth.getPrincipal();
        return (authUser instanceof LoggedUser) ? (LoggedUser) authUser : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static Long id(){
        return get().user.getId();
    }

    public void update(User newUser) {
        user = newUser;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
