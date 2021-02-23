package com.teads.second_price_sealed_bid_auction.engine;

import java.util.List;

import com.teads.second_price_sealed_bid_auction.exception.IncorrectListBuyerException;
import com.teads.second_price_sealed_bid_auction.exception.IncorrectReservePriceException;
import com.teads.second_price_sealed_bid_auction.exception.InsufficientNumberOfBuyerException;

public interface SecondPriceSealedBidAuction {

	
	public Result getWinnerAndBidAmountToPay(int reservePrice, List<Buyer> listBider) throws IncorrectReservePriceException, IncorrectListBuyerException, InsufficientNumberOfBuyerException;
}
