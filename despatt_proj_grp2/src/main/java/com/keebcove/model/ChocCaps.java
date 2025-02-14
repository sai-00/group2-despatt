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
	public String getDesc() {
		return "Keycaps for choc keyboards. 60 pieces";
	}

}
