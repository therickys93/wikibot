package it.therickys93.wikibot;

import static spark.Spark.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.MultipartConfigElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;

public class NotificationManagement {

	public static void execute(){
		port(Configurations.telegramNotificationPort());
		
		File uploadDir = new File("upload");
        uploadDir.mkdir();
        
        staticFiles.externalLocation("upload");
		
		internalServerError((req, res) -> {
		    res.type("application/json");
		    return "{\"success\":false}";
		});
		
		post("/notify", "application/json", (req,res) -> {
			 JsonParser parser = new JsonParser();
			 JsonObject obj = parser.parse(req.body()).getAsJsonObject();
			 String message = obj.get("message").getAsString();
			 
			 sendMessage(message);
			 
			 return "{\"success\":true}";
		});
		
		post("/notifyImage", (request, response) -> {
			Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
		    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		    try {
		    	InputStream is = request.raw().getPart("image").getInputStream();
		    	String mention = request.queryParams("mention");
		    	Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
		        sendImage(tempFile.toFile(), mention);
		        Files.deleteIfExists(tempFile);
		    } catch (Exception e){
		    	// do nothing here
		    }
		    return "{\"success\":true}";
		});
	}
	
	private static void sendImage(File file, String message){
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		 List<Long> authorizedUsers = Configurations.authorizedUsers();
		 
		 if(message == null)
			 message = "";
		 
		 for(Long user : authorizedUsers){
			bot.execute(new SendPhoto(user, file).caption(message));
		 }
	}
	
	private static void sendMessage(String message){
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		 List<Long> authorizedUsers = Configurations.authorizedUsers();
		 
		 for(Long user : authorizedUsers){
			 bot.execute(new SendMessage(user, message));
		 }
	}
	
}
