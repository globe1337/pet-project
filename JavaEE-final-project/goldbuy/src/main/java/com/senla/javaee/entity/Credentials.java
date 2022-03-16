package com.senla.javaee.entity;

import com.senla.javaee.configuration.GraphConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "credentials")
@NamedEntityGraph(name = GraphConfiguration.CREDENTIALS_USER, attributeNodes = @NamedAttributeNode("user"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id",insertable = false, updatable = false)
    private long id;
    @Column(name = "password")
    private String password;
    @Column(name = "login")
    private String login;
    @OneToOne( fetch = FetchType.LAZY, mappedBy = "credentials")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
