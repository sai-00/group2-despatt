package com.keebcove.model;

public class ChocCorne implements Product {

	@Override
	public String getName() {
		return "Choc Corne Barebones";
	}

	@Override
	public double getPrice() {
		return 3000;
	}

	@Override
	public String getDescription() {
		return "Barebones set for Choc Corne build.";
	}

	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Choc";
	}
	
}
