package com.teads.second_price_sealed_bid_auction.engine;

import java.util.Collections;
import java.util.List;

import com.teads.second_price_sealed_bid_auction.exception.IncorrectListBuyerException;
import com.teads.second_price_sealed_bid_auction.exception.IncorrectReservePriceException;
import com.teads.second_price_sealed_bid_auction.exception.InsufficientNumberOfBuyerException;

public class SecondPriceSealedBidAuctionEngine implements SecondPriceSealedBidAuction {

	public Result getWinnerAndBidAmountToPay(int reservePrice, List<Buyer> listBuyer)
			throws IncorrectReservePriceException, IncorrectListBuyerException, InsufficientNumberOfBuyerException {

		if (reservePrice <= 0) {
			throw new IncorrectReservePriceException();
		} else if (listBuyer == null || listBuyer.size() == 0) {
			throw new IncorrectListBuyerException();
		} else if (listBuyer.size() < 2) {
			throw new InsufficientNumberOfBuyerException();
		}

		int maxBide = 0;
		int effectiveBidePrice = 0;
		String auctionWinner = "";

		for (Buyer bider : listBuyer) {

			Collections.sort(bider.getBideList());

			if (bider.getBideList().size() > 0) {
				int maxBideForBuyer = bider.getBideList().get(bider.getBideList().size() - 1);

				if (maxBideForBuyer > effectiveBidePrice && maxBideForBuyer < maxBide) {
					effectiveBidePrice = maxBideForBuyer;
				}

				if (maxBideForBuyer > maxBide) {

					effectiveBidePrice = maxBide;

					maxBide = maxBideForBuyer;

					auctionWinner = bider.name;
				}
			}
		}

		if (effectiveBidePrice < reservePrice) {
			effectiveBidePrice = reservePrice;
		}

		System.out.println("the auction winner is :" + auctionWinner + " , he will pay :" + effectiveBidePrice);
		return new Result(auctionWinner, effectiveBidePrice);

	}

}
