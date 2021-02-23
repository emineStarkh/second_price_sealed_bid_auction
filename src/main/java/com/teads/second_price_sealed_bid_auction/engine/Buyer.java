package com.teads.second_price_sealed_bid_auction.engine;

import java.util.ArrayList;
import java.util.List;

public class Buyer{
	String name;
	List<Integer> bideList = new ArrayList<Integer>();
	
	public Buyer(String name, List<Integer> bideList) {
		super();
		this.name = name;
		this.bideList = bideList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getBideList() {
		return bideList;
	}

	public void setBideList(List<Integer> bideList) {
		this.bideList = bideList;
	}
	
}