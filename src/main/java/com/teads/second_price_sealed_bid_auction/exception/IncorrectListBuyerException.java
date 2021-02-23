package com.teads.second_price_sealed_bid_auction.exception;

public class IncorrectListBuyerException extends Exception {

	public IncorrectListBuyerException() {
	        super("List of bider must be not null or empty");
	    }
}
