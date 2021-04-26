package org.freecode.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="t_user")
public class User {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="passwd")
    @Transient
    private String passwd;

    @Email(message = "Invalid e-mail address")
    @NotEmpty(message = "Cannot be empty")
    @Column(name="email", nullable = false, unique = true)
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
