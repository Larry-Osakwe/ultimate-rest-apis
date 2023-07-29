package com.mycompany.api.unit.conversion;

public class WeightConverter {

	static void kilogramToPound(ConversionDetails details) {
		float kg = details.getFromValue();
		float pounds = kg * 2.20462f;
		
		details.setToValue(pounds);
	}
	
	static void poundToKilogram(ConversionDetails details) {
		float pounds = details.getFromValue();
		float kg = pounds * 0.453592f;
		details.setToValue(kg);
	}
}
