package com.teads.second_price_sealed_bid_auction.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.teads.second_price_sealed_bid_auction.engine.Buyer;
import com.teads.second_price_sealed_bid_auction.engine.Result;
import com.teads.second_price_sealed_bid_auction.engine.SecondPriceSealedBidAuctionEngine;
import com.teads.second_price_sealed_bid_auction.exception.IncorrectListBuyerException;
import com.teads.second_price_sealed_bid_auction.exception.IncorrectReservePriceException;
import com.teads.second_price_sealed_bid_auction.exception.InsufficientNumberOfBuyerException;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class SecondPriceSealedBidAuctionTest {

	List<Buyer> listBuyer;

	@Mock
	SecondPriceSealedBidAuctionEngine engine;

	@BeforeEach
	void populateInput() {
		engine = new SecondPriceSealedBidAuctionEngine();
	}

	@Test
	@DisplayName("Test reserve price = 0")
	void shouldThrowExceptionWhenReservePriceEquals0() throws IncorrectReservePriceException {
		// prepare
		int reservePrice = 0;

		listBuyer = new ArrayList<Buyer>();

		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		listBuyer.add(new Buyer("B", new ArrayList<>()));

		listBuyer.add(new Buyer("C", new ArrayList<>(Arrays.asList(125))));

		listBuyer.add(new Buyer("D", new ArrayList<>(Arrays.asList(105, 115, 90))));

		listBuyer.add(new Buyer("E", new ArrayList<>(Arrays.asList(132, 135, 140))));
		// act
		Result result = null;

		// assert
		Assertions.assertThrows(IncorrectReservePriceException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});
	}

	@Test
	@DisplayName("Test reserve price < 0")
	void shouldThrowExceptionWhenReservePriceLessThan0() throws IncorrectReservePriceException {
		// prepare
		int reservePrice = -50;

		listBuyer = new ArrayList<Buyer>();

		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		listBuyer.add(new Buyer("B", new ArrayList<>()));

		listBuyer.add(new Buyer("C", new ArrayList<>(Arrays.asList(125))));

		listBuyer.add(new Buyer("D", new ArrayList<>(Arrays.asList(105, 115, 90))));

		listBuyer.add(new Buyer("E", new ArrayList<>(Arrays.asList(132, 135, 140))));
		// act
		Result result = null;

		// assert
		Assertions.assertThrows(IncorrectReservePriceException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});
	}

	@Test
	@DisplayName("Test list of buyer empty")
	void shouldThrowExceptionWhenListOfBuyerIsEmpty() throws IncorrectListBuyerException {
		// prepare
		int reservePrice = 100;
		listBuyer = new ArrayList<Buyer>();

		// assert
		Assertions.assertThrows(IncorrectListBuyerException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});
	}

	@Test
	@DisplayName("Test list of Buyer null")
	void shouldThrowExceptionWhenListOfBuyerIsNull() throws IncorrectListBuyerException {
		// prepare
		int reservePrice = 100;
		listBuyer = null;

		// assert
		Assertions.assertThrows(IncorrectListBuyerException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});
	}

	@Test
	@DisplayName("Test number of Buyer less than 2")
	void shouldThrowExceptionWhenListOfBuyerSizeLessThan2() throws IncorrectListBuyerException {
		// prepare
		int reservePrice = 100;
		listBuyer = new ArrayList<Buyer>();
		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		// assert
		Assertions.assertThrows(InsufficientNumberOfBuyerException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});
	}

	@Test
	@DisplayName("Test winning Price < Reserve Price")
	void shouldReturnReservePriceWhenWinningPriceLessThanReservePrice() {
		// prepare
		int reservePrice = 100;

		listBuyer = new ArrayList<Buyer>();

		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(11, 13))));

		listBuyer.add(new Buyer("B", new ArrayList<>()));

		listBuyer.add(new Buyer("C", new ArrayList<>(Arrays.asList(25))));

		listBuyer.add(new Buyer("D", new ArrayList<>(Arrays.asList(5, 15, 3))));

		listBuyer.add(new Buyer("E", new ArrayList<>(Arrays.asList(13, 35, 40))));

		Result result = null;

		// act

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// assert
		Assertions.assertEquals(100, result.getAmountToPay());
	}

	@Test
	@DisplayName("Test use case")
	void shouldReturn130AsWinningPriceAndEAsBidWinnerWhenActualConfig() {
		// prepare
		int reservePrice = 100;

		listBuyer = new ArrayList<Buyer>();

		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		listBuyer.add(new Buyer("B", new ArrayList<>()));

		listBuyer.add(new Buyer("C", new ArrayList<>(Arrays.asList(125))));

		listBuyer.add(new Buyer("D", new ArrayList<>(Arrays.asList(105, 115, 90))));

		listBuyer.add(new Buyer("E", new ArrayList<>(Arrays.asList(132, 135, 140))));

		Result result = null;

		// act
		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// assert
		Assertions.assertEquals("E", result.getAuctionWinner());
		Assertions.assertEquals(130, result.getAmountToPay());
	}

	@Test
	//@DisplayName("Test use case")
	void shouldReturnFirstWinnerWhenTwoBuyerBidSameAmount() {
		// prepare
		int reservePrice = 100;

		listBuyer = new ArrayList<Buyer>();

		listBuyer.add(new Buyer("A", new ArrayList<>(Arrays.asList(110, 130))));

		listBuyer.add(new Buyer("B", new ArrayList<>()));

		listBuyer.add(new Buyer("C", new ArrayList<>(Arrays.asList(125))));

		listBuyer.add(new Buyer("D", new ArrayList<>(Arrays.asList(105, 115, 90))));

		listBuyer.add(new Buyer("E", new ArrayList<>(Arrays.asList(132, 135, 140))));
		
		listBuyer.add(new Buyer("F", new ArrayList<>(Arrays.asList(120, 140))));

		Result result = null;

		// act
		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// assert
		Assertions.assertEquals("E", result.getAuctionWinner());
		Assertions.assertEquals(130, result.getAmountToPay());
	}
	
	
	@Test
	// @DisplayName("Test use case")
	void shouldReturnOrThrowExceptionInDynamic() {
		// prepare
		int reservePrice = 100;

		listBuyer = new ArrayList<Buyer>();
		// assert
		Assertions.assertThrows(IncorrectListBuyerException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});

		// **************************************************************
		Buyer dBuyer = new Buyer("D", new ArrayList<>(Arrays.asList(90)));
		listBuyer.add(dBuyer);
		Assertions.assertThrows(InsufficientNumberOfBuyerException.class, () -> {
			engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		});

		// **************************************************************
		Buyer bBuyer = new Buyer("B", new ArrayList<>(Arrays.asList()));
		listBuyer.add(bBuyer);
		Result result = null;
		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("D", result.getAuctionWinner());
		Assertions.assertEquals(100, result.getAmountToPay());

		// **************************************************************
		Buyer aBuyer = new Buyer("A", new ArrayList<>(Arrays.asList(110)));
		listBuyer.add(aBuyer);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals(100, result.getAmountToPay());
		Assertions.assertEquals("A", result.getAuctionWinner());

		// **************************************************************
		dBuyer.getBideList().add(115);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("D", result.getAuctionWinner());
		Assertions.assertEquals(110, result.getAmountToPay());

		// **************************************************************
		dBuyer.getBideList().add(105);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("D", result.getAuctionWinner());
		Assertions.assertEquals(110, result.getAmountToPay());

		// **************************************************************
		Buyer cBuyer = new Buyer("C", new ArrayList<>(Arrays.asList(125)));
		listBuyer.add(cBuyer);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("C", result.getAuctionWinner());
		Assertions.assertEquals(115, result.getAmountToPay());

		// **************************************************************
		aBuyer.getBideList().add(130);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("A", result.getAuctionWinner());
		Assertions.assertEquals(125, result.getAmountToPay());

		// **************************************************************
		Buyer eBuyer = new Buyer("E", new ArrayList<>(Arrays.asList(132)));
		listBuyer.add(eBuyer);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("E", result.getAuctionWinner());
		Assertions.assertEquals(130, result.getAmountToPay());

		// **************************************************************
		eBuyer.getBideList().add(135);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("E", result.getAuctionWinner());
		Assertions.assertEquals(130, result.getAmountToPay());

		// **************************************************************
		eBuyer.getBideList().add(140);

		try {
			result = engine.getWinnerAndBidAmountToPay(reservePrice, listBuyer);
		} catch (IncorrectReservePriceException | IncorrectListBuyerException | InsufficientNumberOfBuyerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assertions.assertEquals("E", result.getAuctionWinner());
		Assertions.assertEquals(130, result.getAmountToPay());

	}

}
