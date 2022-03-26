package com.revature.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Check;


@Entity
@Table(name="flavor")
public class Flavor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, columnDefinition = "CHECK (ounces > 0)")
	private int ounces;
	@Column(nullable = false, columnDefinition = "CHECK (price > 00.00)")
	private float price;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "brand_id")
	private Brand brandId;

	public Flavor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOunces() {
		return ounces;
	}

	public void setOunces(int ounces) {
		this.ounces = ounces;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Brand getBrandId() {
		return brandId;
	}

	public void setBrandId(Brand brandId) {
		this.brandId = brandId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandId, id, name, ounces, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flavor other = (Flavor) obj;
		return Objects.equals(brandId, other.brandId) && id == other.id && Objects.equals(name, other.name)
				&& ounces == other.ounces && Float.floatToIntBits(price) == Float.floatToIntBits(other.price);
	}

	@Override
	public String toString() {
		return "Flavor [id=" + id + ", name=" + name + ", ounces=" + ounces + ", price=" + price + ", brandId="
				+ brandId + "]";
	}
	
}//end 
