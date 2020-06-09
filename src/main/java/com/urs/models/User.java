package com.urs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotEmpty
    private String name;
    @Size(min = 4)
    private String password;
    // One user can have multiple tasks assigned, map task to users,
    // cascade -all operations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;
    // @JoinTable (join email column from User with name from Role )
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
    private List<Role> roles;

    public User() {
    }

    public User(final String email, final String name, final String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(final List<Task> task) {
        this.tasks = task;
    }

    public String toString() {
        return "User [name: " + this.name + ", email: " + this.email + ", password: " + this.password + "]";
    }

}