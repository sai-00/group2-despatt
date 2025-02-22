package com.keebcove.model;

public class AkkoKeycaps implements Product {

	@Override
	public String getName() {
		return "Akko Keycaps";
	}

	@Override
	public double getPrice() {
		return 2500;
	}

	@Override
	public String getDescription() {
		return "This OEM profile keycap set includes a total of 162 dyb-sub PBT keys.\r\n"
				+ "It provides strong flexibility for customization as it is aimed for not only the popular 108/TLK layouts but also special layouts such as 75%/65%/96-Key/1800 Classic etc.\r\n";
	}
	
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "MX";
	}

}
