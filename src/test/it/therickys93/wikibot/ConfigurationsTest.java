package it.therickys93.wikibot;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class ConfigurationsTest {

	@Rule
	public final EnvironmentVariables environmentVariables = new EnvironmentVariables();
	
	@Test
	public void testTelegramBotToken(){
		assertEquals("<telegram_bot_token>", Configurations.telegramBotToken());
		environmentVariables.set("WIKITELEGRAM_BOT_TOKEN", "bot_token_here");
		assertEquals("bot_token_here", Configurations.telegramBotToken());
	}
	
}
