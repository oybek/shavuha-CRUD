package com.oybek.shavuha.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	// How many shaurms he bought
	@Column(name = "bought")
	private long bought;

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bought = 0;
	}

	public Customer(String firstName, String lastName, long bought) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bought = 0;
	}

	public Customer increaseBought() {
		bought++;
		return this;
	}
}

