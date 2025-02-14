package com.keebcove.model;

public class ChocParts implements Product {
	private String name;
    private double price;
    private String desc;

    public ChocParts(String name, double price, String desc) {
        this.name = name;
        this.price = price;
        this.desc = desc;
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
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}

}
