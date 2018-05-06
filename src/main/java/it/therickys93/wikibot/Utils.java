package it.therickys93.wikibot;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<Long> parseAuthorizedEnv(String env) {
		if(env == null || env.isEmpty())
			return null;
		List<Long> users = new ArrayList<Long>();
		String[] tokens = env.split(",");
		for(int index = 0; index < tokens.length; index++){
			users.add(new Long(tokens[index]));
		}
		return users;
	}

}
