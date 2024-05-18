package org.mycompany.currencyconverter;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
	private String message;

	public void init() {
		message = "Hello World!";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String originalCurrencyStr = request.getParameter("oCurrency");
		String fromCurrency = request.getParameter("fromCurrency");
		String toCurrency = request.getParameter("toCurrency");
		try {
			double originalCurrency = Double.parseDouble(originalCurrencyStr);
			double convertedCurrency = CurrencyConverter.convert(originalCurrency, fromCurrency, toCurrency);

			// generate the response HTML
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String htmlResponse = "<html>";
			htmlResponse += "<body><h2>Conversion Result</h2>";
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