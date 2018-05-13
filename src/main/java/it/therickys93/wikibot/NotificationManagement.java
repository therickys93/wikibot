package it.therickys93.wikibot;

import static spark.Spark.*;

import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class NotificationManagement {

	public static void execute(){
		port(Configurations.telegramNotificationPort());
		
		internalServerError((req, res) -> {
		    res.type("application/json");
		    return "{\"success\":false}";
		});
		
		post("/test", "application/json", (req,res) -> {
			 JsonParser parser = new JsonParser();
			 JsonObject obj = parser.parse(req.body()).getAsJsonObject();
			 String message = obj.get("message").getAsString();
			 
			 sendMessage(message);
			 
			 return "{\"success\":true}";
		});
	}
	
	private static void sendMessage(String message){
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		 List<Long> authorizedUsers = Configurations.authorizedUsers();
		 
		 for(Long user : authorizedUsers){
			 bot.execute(new SendMessage(user, message));
		 }
	}
	
}
