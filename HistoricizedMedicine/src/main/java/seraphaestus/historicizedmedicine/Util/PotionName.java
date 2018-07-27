package seraphaestus.historicizedmedicine.Util;

import net.minecraft.potion.Potion;

public class PotionName {

	public static String potionName(Potion p) {
		String s = p.getName();
		if (s.contains("effect.")) {
			s = s.split("\\.")[1];
			char[] ca = s.toCharArray();
			ca[0] = Character.toUpperCase(ca[0]);
			s = new String(ca);
		}
		return s;
	}
}
