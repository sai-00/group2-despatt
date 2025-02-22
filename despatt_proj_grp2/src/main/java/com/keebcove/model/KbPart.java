package com.keebcove.model;

public class KbPart implements Product {
	private String name;
    private double price;
    private String description;
	private String category;

    public KbPart(String name, double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}

}
