package com.teads.second_price_sealed_bid_auction.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.teads.second_price_sealed_bid_auction.exception.IncorrectListBuyerException;
import com.teads.second_price_sealed_bid_auction.exception.IncorrectReservePriceException;
import com.teads.second_price_sealed_bid_auction.exception.InsufficientNumberOfBuyerException;
import com.teads.second_price_sealed_bid_auction.sparkServices.SparkServices;

/**
 * Hello world!
 *
 */
public class MainApp 
{
    public static void main( String[] args )
    {
        System.out.println( "begin main" );
                
        SecondPriceSealedBidAuctionEngine engine = new SecondPriceSealedBidAuctionEngine();
        
        List<Buyer> buyerList = populateInput(); 
        
        Result result =null;
        
        try {
			result = engine.getWinnerAndBidAmountToPay(100, buyerList);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	private static List<Buyer> populateInput() {
		
		List<Buyer> buyerList = new ArrayList<>();

		buyerList = new ArrayList<Buyer>();

		buyerList.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		buyerList.add(new Buyer("B", new ArrayList<>()));

		buyerList.add(new Buyer("C", new ArrayList<>(Arrays.asList(125))));

		buyerList.add(new Buyer("D", new ArrayList<>(Arrays.asList(105, 115, 90))));

		buyerList.add(new Buyer("E", new ArrayList<>(Arrays.asList(132, 135, 140))));
		
		buyerList.add(new Buyer("F", new ArrayList<>(Arrays.asList(120, 140))));

        
		return buyerList;
	}

	
	
	
}


