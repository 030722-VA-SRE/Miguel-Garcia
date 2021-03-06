package com.revature.models;

public class Flavor{
	
	private int id;
	private String flavor;
	private int ounces;
	private float price;
		
	private Brand brand;
	
	public Flavor(){
		brand = new Brand();
	}
	
	public Flavor(int id, String flavor, int ounces, float price, Brand brand){
		this();
		this.id = id;
		this.flavor = flavor;
		this.ounces = ounces;
		this.price = price;
		this.brand = brand;
	}//end 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
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
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Brand getBrand() {
		return brand;
	}
	
	@Override
	public String toString() {
		return "Flavor [id=" + id + ", flavor=" + flavor + ", ounces=" + ounces + ", price=" + price + ", brand="
				+ brand + "]";
	}
	
	
}//end Flavor
