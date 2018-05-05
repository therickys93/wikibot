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

	public static String wikiServer() {
		if(System.getenv("WIKITELEGRAM_WIKI_SERVER") != null) {
			return System.getenv("WIKITELEGRAM_WIKI_SERVER");
		} else {
			return "http://localhost:8080";
		}
	}

	public static String mongoDBHost() {
		if(System.getenv("WIKITELEGRAM_MONGODB_HOST") != null) {
			return System.getenv("WIKITELEGRAM_MONGODB_HOST");
		} else {
			return "localhost";
		}
	}

	public static int mongoDBPort() {
		if(System.getenv("WIKITELEGRAM_MONGODB_PORT") != null) {
			return Integer.parseInt(System.getenv("WIKITELEGRAM_MONGODB_PORT"));
		} else {
			return 27017;
		}
	}

	public static String mongoDBDatabase() {
		if(System.getenv("WIKITELEGRAM_MONGODB_DB") != null) {
			return System.getenv("WIKITELEGRAM_MONGODB_DB");
		} else {
			return "wikibot";
		}
	}

	public static String mongoDBCollection() {
		if(System.getenv("WIKITELEGRAM_MONGODB_COLLECTION") != null) {
			return System.getenv("WIKITELEGRAM_MONGODB_COLLECTION");
		} else {
			return "telegram";
		}
	}

}
