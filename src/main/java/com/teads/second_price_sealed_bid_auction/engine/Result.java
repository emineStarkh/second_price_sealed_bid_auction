package com.teads.second_price_sealed_bid_auction.engine;

public class Result {
	
	private String auctionWinner;
	private int amountToPay;

	
	public Result(String auctionWinner, int amountToPay) {
		super();
		this.auctionWinner = auctionWinner;
		this.amountToPay = amountToPay;
	}



	public String getAuctionWinner() {
		return auctionWinner;
	}

	public void setBideWinner(String bideWinner) {
		this.auctionWinner = bideWinner;
	}

	public int getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(int amountToPay) {
		this.amountToPay = amountToPay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountToPay;
		result = prime * result + ((auctionWinner == null) ? 0 : auctionWinner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (amountToPay != other.amountToPay)
			return false;
		if (auctionWinner == null) {
			if (other.auctionWinner != null)
				return false;
		} else if (!auctionWinner.equals(other.auctionWinner))
			return false;
		return true;
	}

	
}
