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
@Table(name = "deal")
public class Deal implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "customer_app", insertable = false, updatable = false),
            @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    })
    private Provider provider;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "customer_app", insertable = false, updatable = false),
            @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    })
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "deal_product"
            , joinColumns = @JoinColumn(name="deal_id")
            , inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> product;

    @Column(name = "done")
    private boolean done;
}
