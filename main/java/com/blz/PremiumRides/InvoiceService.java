package com.blz.PremiumRides;

public class InvoiceService {

	private static final double MINIMUM_COST_PER_KM = 10.0;
	private static final int COST_PER_TIME = 1;
	private static final double MINIMUM_FARE = 5;
	private RideRepository rideRepository;

	public InvoiceService() {
		this.rideRepository = new RideRepository();
	}

	public double calculateFare(double distance, int time) {
		double fare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
		if (fare < MINIMUM_FARE)
			return MINIMUM_FARE;
		return fare;
	}
	public double calculateFare(double distance, int time,boolean premiumRide) {
	private static final double NORMAL_COST_PER_KM = 10.0;
	private static final int NORMAL_COST_PER_TIME = 1;
	private static final double NORMAL_MINIMUM_FARE = 5;
	private static final double PREMIUM_COST_PER_KM = 15.0;
	private static final int PREMIUM_COST_PER_TIME = 2;
	private static final double PREMIUM_MINIMUM_FARE = 20;
	

		double fare = 0;
		for (Ride ride : rides) {
			fare += this.calculateFare(ride.distance, ride.time);
		if(premiumRide) {
			fare = distance * PREMIUM_COST_PER_KM + time * PREMIUM_COST_PER_TIME;
			if (fare < PREMIUM_MINIMUM_FARE)
				return PREMIUM_MINIMUM_FARE;
			return fare;
		} else {
			fare = distance * NORMAL_COST_PER_KM + time * NORMAL_COST_PER_TIME;
			if(fare < NORMAL_MINIMUM_FARE)
				return NORMAL_MINIMUM_FARE;
			return fare;
		}
		
	}}


	public InvoiceSummary calculateSummaryFare(Ride[] rides, boolean premiumRide) {
		double fare = 0;
		for (Ride ride : rides) {
			fare += this.calculateFare(ride.distance, ride.time, premiumRide);
		}
		return new InvoiceSummary(rides.length, fare);
	}

	public void addRides(String userId, Ride[] rides) {
		rideRepository.addRides(userId, rides);
	}

	public InvoiceSummary getInvoiceSummary(String userId) {
		return this.calculateSummaryFare(rideRepository.getRides(userId));
	}

	private InvoiceSummary calculateSummaryFare(Ride[] rides) {
		// TODO Auto-generated method stub
		return null;
	}
}