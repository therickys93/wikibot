package it.therickys93.wikibot;

import java.io.IOException;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Wikibot {

	public static void main(String[] args){
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		List<Long> authorizedUsers = Configurations.authorizedUsers();
		
		bot.setUpdatesListener(new UpdatesListener() {
		    @Override
		    public int process(List<Update> updates) {
		    	System.out.println(updates.toString());
		    	
		    	for(Update update : updates){
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
	
}