package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "customer")
public class Provider implements Serializable {

    @EmbeddedId
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

    // How many shaurms he bought
    @Column(name = "sold")
    private long sold;

    public Provider increaseSold() {
        sold++;
        return this;
    }
}

