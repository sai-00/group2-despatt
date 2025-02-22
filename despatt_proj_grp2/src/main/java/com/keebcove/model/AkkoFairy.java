package com.keebcove.model;

public class AkkoFairy implements Product {

	@Override
	public String getName() {
		return "Akko Fairy Silent";
	}

	@Override
	public double getPrice() {
		return 750;
	}

	@Override
	public String getDescription() {
		return "Linear silent switches;<br>"
				+ "Operating Force: 50gf ± 5gf;<br>"
				+ "Total Travel: 3.3mm;<br>"
				+ "45 pcs per pack;<br>"
				+ "5-pin and fits keycaps with standard MX structure;";
	}
	
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "MX";
	}

}
