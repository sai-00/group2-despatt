package com.keebcove.utility;

import com.keebcove.model.ChocAmbients;
import com.keebcove.model.ChocCaps;
import com.keebcove.model.ChocCorne;
import com.keebcove.model.Product;


public class ChocFactory extends AbstractFactory{

	public Product getProduct(String name) {
		
		if (name == null) return null;

        switch (name.toUpperCase()) {
        	case "AMBIENT TWILIGHT CHOC SWITCHES":
        		return new ChocAmbients();
        	case "CHOC KEYCAPS":
        		return new ChocCaps();
            case "CHOC CORNE BAREBONES":
                return new ChocCorne();
            default:
                return null;
        }
		
	}

}
