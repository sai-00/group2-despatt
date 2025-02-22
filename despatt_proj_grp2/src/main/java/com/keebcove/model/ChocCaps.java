package com.keebcove.model;

public class ChocCaps implements Product {

	@Override
	public String getName() {
		return "Choc keycaps";
	}

	@Override
	public double getPrice() {
		return 2000;
	}

	@Override
	public String getDescription() {
		return "Keycaps for choc keyboards. 60 pieces";
	}
	
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Choc";
	}

}
