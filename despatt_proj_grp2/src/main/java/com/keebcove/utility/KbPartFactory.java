package com.keebcove.utility;

import com.keebcove.model.Product;
import com.keebcove.model.AkkoFairy;
import com.keebcove.model.AkkoTop40;
import com.keebcove.model.AkkoKeycaps;

public class KbPartFactory extends AbstractFactory{

	public Product getProduct(String name) {
		
		if (name == null) return null;

        switch (name.toUpperCase()) {
        	case "AKKO FAIRY SILENT":
        		return new AkkoFairy();
        	case "AKKO TOP 40":
        		return new AkkoTop40();
            case "AKKO KEYCAPS":
                return new AkkoKeycaps();
            default:
                return null;
        }
		
	}
}
