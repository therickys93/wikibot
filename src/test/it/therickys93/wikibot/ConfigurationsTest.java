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
	public void testMongoDBHost(){
		assertEquals("localhost", Configurations.mongoDBHost());
		environmentVariables.set("WIKITELEGRAM_MONGODB_HOST", "mongodb");
		assertEquals("mongodb", Configurations.mongoDBHost());
	}
	
	@Test
	public void testMongoDBPort(){
		assertEquals(27017, Configurations.mongoDBPort());
		environmentVariables.set("WIKITELEGRAM_MONGODB_PORT", "12345");
		assertEquals(12345, Configurations.mongoDBPort());
	}
	
	@Test
	public void testMongoDBDatabase(){
		assertEquals("wikibot", Configurations.mongoDBDatabase());
		environmentVariables.set("WIKITELEGRAM_MONGODB_DB", "telegram");
		assertEquals("telegram", Configurations.mongoDBDatabase());
	}
	
	@Test
	public void testMongoDBCollection(){
		assertEquals("telegram", Configurations.mongoDBCollection());
		environmentVariables.set("WIKITELEGRAM_MONGODB_COLLECTION", "messages");
		assertEquals("messages", Configurations.mongoDBCollection());
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
