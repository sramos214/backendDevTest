package com.napptilus.dto;

public class ProductDetailDto {

	private String id;
	private String name;
	private Float price;
	private boolean availability;

	public ProductDetailDto() {
	}

	public ProductDetailDto(String id, String name, Float price, boolean availability) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.availability = availability;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "ProductDetail [id=" + id + ", name=" + name + ", price=" + price + ", availability=" + availability
				+ "]";
	}
	
	

}
