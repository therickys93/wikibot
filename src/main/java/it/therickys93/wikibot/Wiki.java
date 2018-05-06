package it.therickys93.wikibot;

import java.io.IOException;

import it.therickys93.wikiapi.controller.WikiRequest;
import it.therickys93.wikiapi.controller.WikiResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Wiki {

	private static final String APPLICATION_JSON = "application/json; charset=utf-8";
	private String server;
	private OkHttpClient client;
	
	public Wiki(String server){
		this.server = server;
		this.client = new OkHttpClient();
	}
	
	public String executeMessage(String message) throws IOException{
		String response = this.postMethod(WikiRequest.writeMessage(message));
		return WikiResponse.readMessage(response);
	}
	
	private String postMethod(String request) throws IOException {
		final MediaType JSON = MediaType.parse(APPLICATION_JSON);
		RequestBody body = null;
		body = RequestBody.create(JSON, request);
		Request requestAdd = new Request.Builder().url(this.server + "/v1/wiki").post(body).build();
		Response response = client.newCall(requestAdd).execute();
		return response.body().string();
	}
	
}
