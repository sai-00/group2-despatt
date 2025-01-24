package com.keebcove.utility;

import com.keebcove.model.KbPart;
import com.keebcove.model.AkkoFairy;

public class KbPartFactory {
	public static KbPart getName(String part) {
		
		KbPart keebpart = null;
		
		switch (part.toUpperCase()) {
		case "AKKO FAIRY SILENT" :
			keebpart = new AkkoFairy();
			break;
		}
		return keebpart;
	}
}
