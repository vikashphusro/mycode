package com.condivision.rentongo.https.bins;

import java.io.Serializable;

public class BikeBrandBin implements Serializable {

	private int id;
	private String brandName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
