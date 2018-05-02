package it.therickys93.wikibot;

import java.util.List;

public class Configurations {

	public static String telegramBotToken() {
		if(System.getenv("WIKITELEGRAM_BOT_TOKEN") != null) {
			return System.getenv("WIKITELEGRAM_BOT_TOKEN");
		} else {
			return "<telegram_bot_token>";
		}
	}

	public static List<Long> authorizedUsers() {
		if(System.getenv("WIKITELEGRAM_AUTHORIZED_USERS") != null) {
			String env = System.getenv("WIKITELEGRAM_AUTHORIZED_USERS");
			return Utils.parseAuthorizedEnv(env);
		} else {
			return null;
		}
	}

}
