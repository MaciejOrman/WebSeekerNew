package pl.wroc.pwr.searchengine;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import pl.wroc.pwr.searchengine.dto.FarooResults;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FarooSearch {

	public FarooResults searchInFaroo(String query)
			throws UnirestException {
		HttpResponse<String> response;
		String queryModified = query.replace(" ", "+");


		response = Unirest
				.get("https://faroo-faroo-web-search.p.mashape.com/api?q="
						+ queryModified)
				.header("X-Mashape-Key",
						"EQ0BgAHsmxmsh6FylZTqSqqkTF7op1ov4BzjsntBXusNcAgWbU")
				.header("Accept", "application/json").asString();
		System.out.println(response.getBody());
		return map(response);
		


	}

	private FarooResults map(HttpResponse<String> response) {
		ObjectMapper mapper = new ObjectMapper();
		FarooResults farooLinks = null;
		try {
			farooLinks = mapper.readValue(response.getBody(), FarooResults.class);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return farooLinks;
	}
	
	
}
