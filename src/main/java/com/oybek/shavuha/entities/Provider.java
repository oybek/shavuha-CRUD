package com.oybek.shavuha.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "provider")
    private List<Deal> dealList = new ArrayList<>();

    public Provider(AppId appId, String name, String firstName, String lastName, float latitude, float longitude, Date workDate, Set<Product> products, List<Deal> dealList) {
        this.appId = appId;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workDate = workDate;
        this.products = products;
        this.dealList = dealList;
    }

    public Provider addDeal(Deal deal) {
        dealList.add(deal);
        return this;
    }
}

