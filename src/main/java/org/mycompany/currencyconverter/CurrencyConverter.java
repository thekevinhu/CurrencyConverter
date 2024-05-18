package org.mycompany.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {
	private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/5d312ed4aad883a57f84e573/latest/";
	private static Map<String, Double> exchangeRates = new HashMap<String, Double>();

	public static double convert(double originalCurrency, String fromCurrency, String toCurrency) {
		fetchExchangeRates(fromCurrency);
		String currencyPair = fromCurrency + "-" + toCurrency;
		Double rate = exchangeRates.get(currencyPair);
		if (rate != null) {
			return rate * originalCurrency;
		} else {
			throw new IllegalArgumentException("Invalid currency pair: " + currencyPair);
		}
	}

	public static void fetchExchangeRates(String baseCurrency) {
		try {
			URL url = new URL(API_BASE_URL + baseCurrency);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				parseExchangeRates(response.toString(), baseCurrency);
			} else {
				System.out.println("Error fetching exchange rates: " + responseCode);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void parseExchangeRates(String response, String baseCurrency) {
		JsonParser parser = new JsonParser();
		JsonObject jsonResponse = parser.parse(response).getAsJsonObject();
		JsonObject rates = jsonResponse.get("conversion_rates").getAsJsonObject();
		rates.entrySet().forEach(entry -> {
			String currencyCode = entry.getKey();
			double rate = entry.getValue().getAsDouble();
			exchangeRates.put(baseCurrency + "-" + currencyCode, rate);
			exchangeRates.put(currencyCode + "-" + baseCurrency, 1/rate);
		});
	}
}
