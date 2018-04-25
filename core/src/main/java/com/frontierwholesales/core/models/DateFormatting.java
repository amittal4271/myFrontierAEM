package com.frontierwholesales.core.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import com.adobe.cq.sightly.WCMUsePojo;

public class DateFormatting extends WCMUsePojo {

	
	private Calendar date;

	private String dateFormat;
	
	public String formattedValue;
	
	@Override
	public void activate() throws Exception {
		date = get("date",Calendar.class);
		dateFormat = get("dateFormat",String.class);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		formattedValue = formatter.format(date.getTime());
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getFormattedValue() {
		return formattedValue;
	}

	public void setFormattedValue(String formattedValue) {
		this.formattedValue = formattedValue;
	}
	
	
}
