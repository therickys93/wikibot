package it.therickys93.wikibot;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Wikibot {

	public static void main(String[] args) throws UnknownHostException{
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		List<Long> authorizedUsers = Configurations.authorizedUsers();
		
		bot.setUpdatesListener(new UpdatesListener() {
		    @Override
		    public int process(List<Update> updates) {
		    	
		    	for(Update update : updates){
		    		String updateJson = new Gson().toJson(update);
		    		System.out.println(updateJson);
		    		saveUpdateInDB(updateJson);
		    		Long chat_id = update.message().chat().id();
		    		String text = update.message().text();
		    		if(authorizedUsers != null && authorizedUsers.contains(update.message().chat().id())){
		    			Wiki wiki = new Wiki(Configurations.wikiServer());
		    			String response = "";
		    			try {
							response = wiki.executeMessage(text);
						} catch (IOException e) {
							response = "problema nel contattare il server";
						}
			    		bot.execute(new SendMessage(chat_id, response));
		    		} else {
		    			bot.execute(new SendMessage(chat_id, "Non sei autorizzato!!!"));
		    		}
		    		
		    	}

		        return UpdatesListener.CONFIRMED_UPDATES_ALL;
		    }
		});
		
		
		System.out.println("Wiki Telegram Bot started");
	}
	
	private static void saveUpdateInDB(String updateJson){
		try {
			MongoClientOptions options = new MongoClientOptions.Builder().socketTimeout(500).connectTimeout(500).build();
			MongoClient mongoClient = new MongoClient(new ServerAddress(Configurations.mongoDBHost(), Configurations.mongoDBPort()), options);
			mongoClient.getDatabaseNames();
			DB db = mongoClient.getDB(Configurations.mongoDBDatabase());
			DBCollection coll = db.getCollection(Configurations.mongoDBCollection());
			DBObject object = (DBObject)JSON.parse(updateJson);
    		coll.insert(object);
    		mongoClient.close();
		} catch (Exception e){
			// non fare nulla
		}
	}
	
}