package com.blz.PremiumRides;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
public class InvoiceServiceTest {
	static InvoiceService invoiceService;

	@BeforeClass
	public static void createInvoiceServiceObj() {
		invoiceService = new InvoiceService();
		System.out.println("Welcome to Cab Invoice Generator Program");
	}
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		double distance = 2.0;
		int time = 5;
		double fare = invoiceService.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}

	@Test
	public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
		double distance = 0.1;
		int time = 1;
		double fare = invoiceService.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_ShouldReturnTotalFare() {
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		double fare = invoiceService.calculateMultipleRidesFare(rides);
		Assert.assertEquals(30, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_ShouldReturnInvoiceSummary() {
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		InvoiceSummary actualInvoiceSummary = invoiceService.calculateSummaryFare(rides);
		Ride[] rides = {
					new Ride(2.0, 5),
					new Ride(0.1, 1)
				};
		InvoiceSummary actualInvoiceSummary = invoiceService.calculateSummaryFare(rides, false);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
	}

	@Test
	public void givenUserIdAndRide_ShouldReturnInvoiceSummary() {
		String userId = "abc.com";
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		invoiceService.addRides(userId, rides);
		InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, summary);
		RideRepository[] rideRepository = {
					new RideRepository(1, new Ride[] { new Ride(4.0, 6), new Ride(10.0, 12)}),
					new RideRepository(2, new Ride[] { new Ride(2.0, 5), new Ride(0.1, 1), new Ride(5.0, 5)}),
					new RideRepository(3, new Ride[] { new Ride(5.0, 10), new Ride(10.0, 20)})
				};
		
		InvoiceSummary expectedInvoiceSummary1 = new InvoiceSummary(3, 85);
		Assert.assertEquals(expectedInvoiceSummary1, actualInvoiceSummary);
	}

	@Test
	public void givenDistanceAndTime_ShouldReturnPremiumFare() {
		double fare = invoiceService.calculateFare(5.0, 5, true);
		Assert.assertEquals(85, fare, 0.0);
	}

	@Test
	public void givenLessDistanceAndTime_ShouldReturnPremiumMinimumFare() {
		double fare = invoiceService.calculateFare(0.1, 1, true);
		Assert.assertEquals(20, fare, 0.0);
	}

	@Test
	public void givenMultipleRides_ShouldReturnPremiumInvoiceSummary() {
		Ride[] rides = {
					new Ride(2.0, 5),
					new Ride(0.1, 1)
			};
		InvoiceSummary actualInvoiceSummary = invoiceService.calculateSummaryFare(rides, true);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60);
		Assert.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
	}

	@Test
	public void givenUserIdAndRide_ShouldReturnPremiumUserInvoiceSummary() {
		RideRepository[] rideRepository = {
				new RideRepository(1, new Ride[] { new Ride(4.0, 6), new Ride(10.0, 12)}),
				new RideRepository(2, new Ride[] { new Ride(2.0, 5), new Ride(0.1, 1), new Ride(6.0, 5)}),
				new RideRepository(3, new Ride[] { new Ride(5.0, 10), new Ride(10.0, 20)})
			};
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator(Arrays.asList(rideRepository));
		InvoiceSummary actualInvoiceSummary = invoiceGenerator.getInvoice(3, true);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 285);
		Assert.assertEquals(expectedInvoiceSummary, actualInvoiceSummary);
	}
}