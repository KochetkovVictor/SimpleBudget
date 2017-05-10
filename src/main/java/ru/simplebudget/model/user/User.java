package ru.simplebudget.model.user;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;


@Entity
@StaticMetamodel(User.class)
public class User {

    @Id
    private Long id;
    private String nickName;
    private String password;
    private LocalDate dateOfBirth;
    private String firsName;
    private String LastName;

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
}
