package it.therickys93.wikibot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testAuthorizedUsers(){
		assertNull(Configurations.authorizedUsers());
		environmentVariables.set("WIKITELEGRAM_AUTHORIZED_USERS", "12345678");
		assertEquals(authorizedUsers(), Configurations.authorizedUsers());
	}
	
	@Test
	public void testWikiServer(){
		assertEquals("http://localhost:8080", Configurations.wikiServer());
		environmentVariables.set("WIKITELEGRAM_WIKI_SERVER", "http://wiki");
		assertEquals("http://wiki", Configurations.wikiServer());
	}
	
	@Test
	public void testImproveCodeCoverage(){
		Configurations conf = new Configurations();
		assertNotNull(conf.toString());
	}
	
	private List<Long> authorizedUsers(){
		List<Long> users = new ArrayList<Long>();
		users.add(new Long(12345678));
		return users;
	}
	
}
