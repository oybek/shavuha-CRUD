package com.oybek.shavuha.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
            @JoinColumn(name = "provider_app", updatable = false),
            @JoinColumn(name = "provider_id", updatable = false)
    })
    private Provider provider;

    @ManyToOne(optional = false)
    @JoinColumns({
            @JoinColumn(name = "customer_app", updatable = false),
            @JoinColumn(name = "customer_id", updatable = false)
    })
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "deal_product"
            , joinColumns = @JoinColumn(name="deal_id", insertable = false, updatable = false)
            , inverseJoinColumns = @JoinColumn(name="product_id", insertable = false, updatable = false))
    private Set<Product> product;

    // deal is complete when provider entered deal id
    @Column(name = "closed")
    private boolean closed = false;

    public Deal close() {
        this.closed = true;
        return this;
    }
}
