package org.freecode.demo.model;

import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name="t_user")
public class User implements Serializable {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="passwd")
    @Transient
    // Note: use org.springframework.data.annotation.Transient NOT from javax.persistence.Transient because do not want to store password directly.
    // only store the encrypted version.
    // transient is for all serializations (over the wire, saving to disk, saving to db)
    // javax.persistence.Transient is specifically for JPA DB serialization @org.springframework.data.annotation.Transient is for ObjectMapping Framework serializations used within Spring
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
