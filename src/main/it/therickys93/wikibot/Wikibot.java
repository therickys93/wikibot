package it.therickys93.wikibot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class Wikibot {

	public static void main(String[] args){
		TelegramBot bot = new TelegramBot(Configurations.telegramBotToken());
		
		bot.setUpdatesListener(new UpdatesListener() {
		    @Override
		    public int process(List<Update> updates) {
		    	System.out.println(updates.toString());
		    	
		    	for(Update update : updates){
		    		bot.execute(new SendMessage(update.message().chat().id(), update.message().text()));
		    	}

		        return UpdatesListener.CONFIRMED_UPDATES_ALL;
		    }
		});
		
		
		System.out.println("Wiki Telegram Bot started");
	}
	
}