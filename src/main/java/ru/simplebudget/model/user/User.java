package ru.simplebudget.model.user;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.cglib.core.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;


@Entity
@StaticMetamodel(User.class)
public class User {

    @Id
    private Long id;
    @Column(name="nickname", nullable = false, unique = true)
    private String nickName;
    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;
    @Column(name = "dayofbirth", columnDefinition = "timestamp default now()")
    private LocalDate dateOfBirth;
    @Column
    private String firsName;
    @Column
    private String LastName;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private LocalDate registered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDate registered) {
        this.registered = registered;
    }
}
