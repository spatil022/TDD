package com.blz.InvoiceService;

public class InvoiceSummary {

	public int numOfRides;
	public double totalFare;
	public double averageFare;

	public InvoiceSummary(int numOfRides, double totalFare) {
		this.numOfRides = numOfRides;
		this.totalFare = totalFare;
		this.averageFare = this.totalFare / this.numOfRides;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || getClass() != obj.getClass())
			return false;
		InvoiceSummary summary = (InvoiceSummary) obj;
		if(Double.doubleToLongBits(totalFare) != Double.doubleToLongBits(summary.totalFare))
			return false;
		if(numOfRides != summary.numOfRides)
			return false;
		return true;
	}	
}