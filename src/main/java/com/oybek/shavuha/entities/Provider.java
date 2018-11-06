package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "provider")
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

    @Column(name = "work_date")
    private Date workDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "provider_product"
            , joinColumns = {
                @JoinColumn(name = "provider_app"),
                @JoinColumn(name = "provider_id")
            }
            , inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "app", referencedColumnName = "app", updatable = false, insertable = false),
            @JoinColumn(name = "id", referencedColumnName = "id", updatable = false, insertable = false)
    })
    private List<Deal> dealList = new ArrayList<>();

    public Provider(AppId appId, String name, String firstName, String lastName, float latitude, float longitude, Set<Product> products) {
        this.appId = appId;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.products = products;
    }
}

