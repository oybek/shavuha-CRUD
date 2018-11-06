package com.oybek.shavuha.entities;

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

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "provider_app", referencedColumnName = "app"),
            @JoinColumn(name = "provider_id", referencedColumnName = "id")
    })
    private Provider provider;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "customer_app"),
            @JoinColumn(name = "customer_id")
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
