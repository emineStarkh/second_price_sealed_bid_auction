package com.teads.second_price_sealed_bid_auction.exception;

public class IncorrectReservePriceException extends Exception {

	public IncorrectReservePriceException() {
	        super("Reserve price should be > 0");
	    }
}
