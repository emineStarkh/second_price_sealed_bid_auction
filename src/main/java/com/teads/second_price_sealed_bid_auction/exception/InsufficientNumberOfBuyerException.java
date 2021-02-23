package com.teads.second_price_sealed_bid_auction.exception;

public class InsufficientNumberOfBuyerException extends Exception {

	public InsufficientNumberOfBuyerException() {
	        super("List of bider must be not null or empty");
	    }
}
