package org.mycompany.currencyconverter;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.text.DecimalFormat;

@WebServlet(name = "currencyConverterServlet", value = "/currency-converter-servlet")
public class CurrencyConverterServlet extends HttpServlet {
	public void init() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String originalCurrencyStr = request.getParameter("oCurrency");
		String fromCurrency = request.getParameter("fromCurrency");
		String toCurrency = request.getParameter("toCurrency");
		try {
			double originalCurrency = Double.parseDouble(originalCurrencyStr);
			double convertedCurrency = CurrencyConverter.convert(originalCurrency, fromCurrency, toCurrency);
			DecimalFormat df = new DecimalFormat("#.##");
			convertedCurrency = Double.valueOf(df.format(convertedCurrency));

			// generate the response HTML
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String htmlResponse = "<html>";
			htmlResponse="<style> h2{font-size: 40px; text-align:center;} p{text-align:center;}</style>";
			htmlResponse += "<body><h2>Conversion Result:</h2>";
			htmlResponse += "<p>Original Currency: " + originalCurrency + " " + fromCurrency + " = " + convertedCurrency +
					" " + toCurrency + "</p>";
			htmlResponse += "</body></html>";
			out.println(htmlResponse);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("Error: " + e.getMessage());
		}
	}

	public void destroy() {
	}
}