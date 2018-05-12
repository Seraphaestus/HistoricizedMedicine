package seraphaestus.historicizedmedicine;

import java.util.ArrayList;
import java.util.List;

public class HardCodedValues {

	public static List<String> getRecipeFilePaths() {
		List<String> output = new ArrayList<String>();
		final String ebers = "0ebers/";
		output.add(ebers + "corpus");
		output.add(ebers + "honey_poultice");
		output.add(ebers + "ligature");
		output.add(ebers + "meat_bandage");
		output.add(ebers + "suture");
		final String corpus = "1corpus/";
		output.add(corpus + "canon");
		output.add(corpus + "reed_bandage");
		output.add(corpus + "scalpel");
		output.add(corpus + "statue_end");
		output.add(corpus + "statue_nether");
		output.add(corpus + "statue_wise");
		final String canon = "2canon/";
		output.add(canon + "cautery");
		output.add(canon + "magna");
		output.add(canon + "razor");
		final String magna = "3magna/";
		output.add(magna + "nitrous_oxide");
		return output;
	}
	
}
