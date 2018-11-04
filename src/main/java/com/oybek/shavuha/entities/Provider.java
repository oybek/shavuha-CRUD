package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "customer")
public class Provider implements Serializable {

    @EmbeddedId
    @Column(name = "appid")
    private AppId appId;

    @Column(name = "name")
    private String name;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "provider_product"
            , joinColumns = @JoinColumn(name="provider_appid")
            , inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products;
}

