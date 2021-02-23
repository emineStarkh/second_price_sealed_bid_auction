package com.teads.second_price_sealed_bid_auction.sparkServices;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.teads.second_price_sealed_bid_auction.engine.Buyer;

public class SparkServices {

	public void parseFile() {
		System.out.println("begin parsing ");
		SparkSession spark = SparkSession.builder().appName("second_price_sealed_bid_auction").getOrCreate();

		Dataset<Row> df = spark.read().option("delimiter", ";").csv("D:\\workspace\\second-price-sealed-bid-auction\\src\\main\\resources\\input.csv");
		
		df.show();
		
		Dataset<Row> df1 = df.groupBy("_c0").agg(org.apache.spark.sql.functions.collect_list("_c1")).toDF("_c0","_c1");
		
		df1.show();
		
		df1.printSchema();
		
		Encoder<Buyer> buyerEncoder = Encoders.bean(Buyer.class);
		
		List<Buyer> list = df1.as(buyerEncoder).collectAsList();
			
		for(Buyer b: list){
			System.out.println("-------->"+b.getName());
		}
	}

}