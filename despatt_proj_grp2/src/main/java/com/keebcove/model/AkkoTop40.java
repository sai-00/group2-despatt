package com.keebcove.model;

public class AkkoTop40 implements Product {

	@Override
	public String getName() {
		return "Akko Top 40";
	}

	@Override
	public double getPrice() {
		return 4800;
	}

	@Override
	public String getDesc() {
		return "Introducing the ACR TOP 40, a keyboard designed for ultimate minimalism and portability.\r\n"
				+ "\r\n"
				+ "With QMK/VIA capability, the keyboard can be programmed across different layers for maximum efficiency.\r\n"
				+ "\r\n"
				+ "The TOP mounted structure also provides unique sound profile comparing to gasket mount.\r\n"
				+ "\r\n"
				+ "Note: Currently, only Akko MDA keycap sets are compatible with the 40% size. You may consider exploring third-party keycaps.\r\n"
				+ "\r\n"
				+ "";
	}

}
