package it.therickys93.wikibot;

public class Configurations {

	public static String telegramBotToken() {
		if(System.getenv("WIKITELEGRAM_BOT_TOKEN") != null) {
			return System.getenv("WIKITELEGRAM_BOT_TOKEN");
		} else {
			return "<telegram_bot_token>";
		}
	}

}
