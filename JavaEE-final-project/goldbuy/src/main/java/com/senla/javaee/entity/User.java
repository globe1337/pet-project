package com.senla.javaee.entity;

import com.senla.javaee.configuration.GraphConfiguration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NamedEntityGraph(name = GraphConfiguration.USER_CREDENTIALS, attributeNodes = @NamedAttributeNode("credentials"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "roleid")})
    private List<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Product> products;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_umber")
    private String phoneNumber;
    @Column(name = "mail")
    private String mail;
    @OneToMany(mappedBy = "customer")
    private List<History> purchaseHistory;
    @OneToMany(mappedBy = "owner")
    private List<History> salesHistory;

    public List<History> getSalesHistory() {
        return salesHistory;
    }

    public void setSalesHistory(List<History> salesHistory) {
        this.salesHistory = salesHistory;
    }

    public List<History> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void setPurchaseHistory(List<History> purchaseHistory) {
        this.purchaseHistory = purchaseHistory;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


}
