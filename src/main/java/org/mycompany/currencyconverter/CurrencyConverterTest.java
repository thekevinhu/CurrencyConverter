package org.mycompany.currencyconverter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CurrencyConverterTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvert() {
		double amount = 1.00;
		String fromCurrency = "USD";
		String toCurrency = "EUR";
		double expected = 0.92;

		double result = CurrencyConverter.convert(amount, fromCurrency, toCurrency);

		assertEquals(expected, result, 0.1);
	}
}
