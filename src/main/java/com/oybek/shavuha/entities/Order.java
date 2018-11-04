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
@Table(name = "product")
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "provider_appid")
    private Provider provider;

    @OneToOne(optional = false)
    @JoinColumn(name = "customer_appid")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_product"
            , joinColumns = @JoinColumn(name="order_id")
            , inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> product;

    @Column(name = "done")
    private boolean done;
}
