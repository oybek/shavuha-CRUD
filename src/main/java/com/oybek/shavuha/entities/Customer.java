package com.oybek.shavuha.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

    @EmbeddedId
    @Column(name = "appid")
	private AppId appId;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Deal> dealList = new ArrayList<>();

	public Customer(AppId appId, String firstName, String lastName) {
		this.appId = appId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer addDeal(Deal deal) {
		dealList.add(deal);
		return this;
	}
}

