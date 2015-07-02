package com.condivision.rentongo.https.bins;

public class LocationBin {

	private String location;
	private int monthFrom;
	private int yearFrom;
	private int dateFrom;
	private int dateTo;
	private int monthTo;
	private int yearTo;
	private int dayTo;
	private int dayFrom;

	public int getDateTo() {
		return dateTo;
	}

	public void setDateTo(int dateTo) {
		this.dateTo = dateTo;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMonthFrom(int monthFrom) {
		this.monthFrom = monthFrom;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setDateFrom(int dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setMonthTo(int monthTo) {
		this.monthTo = monthTo;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}

	public void setDayTo(int dayTo) {
		this.dayTo = dayTo;
	}

	public void setDayFrom(int dayFrom) {
		this.dayFrom = dayFrom;
	}

	public String getLocation() {
		return location;
	}

	public int getMonthFrom() {
		return monthFrom;
	}

	public int getYearFrom() {
		return yearFrom;
	}

	public int getDateFrom() {
		return dateFrom;
	}

	public int getMonthTo() {
		return monthTo;
	}

	public int getYearTo() {
		return yearTo;
	}

	public int getDayTo() {
		return dayTo;
	}

	public int getDayFrom() {
		return dayFrom;
	}

}
