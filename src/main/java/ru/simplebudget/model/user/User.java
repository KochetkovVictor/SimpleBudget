package ru.simplebudget.model.user;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@StaticMetamodel(User.class)
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator(name = "global_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickName;
    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;
    @Column(name = "dateOfBirth", columnDefinition = "timestamp default now()")
    private LocalDate dateOfBirth;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "register", columnDefinition = "timestamp default now()")
    private LocalDate registered;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firsName) {
        this.firstName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
        this.enabled=true;
        this.registered=LocalDate.now();
        Set<Role> roles=new HashSet<>();
        roles.add(Role.ROLE_USER);
        this.roles=roles;
    }
}
