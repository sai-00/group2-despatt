package com.keebcove.model;

public class ChocAmbients implements Product {

	@Override
	public String getName() {
		return "Ambient Twilight Choc switches";
	}

	@Override
	public double getPrice() {
		return 700;
	}

	@Override
	public String getDescription() {
		return "Manufacturer: Kailh\r\n"
				+ "10 pieces\r\n"
				+ "Type: Low Profile/Linear\r\n"
				+ "Actuation: 35g\r\n"
				+ "Bottom Out: 35g\r\n"
				+ "Pre Travel: 1.49mm\r\n"
				+ "Travel: 2.95mm\r\n"
				+ "Mounting: PCB(5-Pin)";
	}
	
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Choc";
	}

}
