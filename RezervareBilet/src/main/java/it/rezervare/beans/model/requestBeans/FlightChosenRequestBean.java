package it.rezervare.beans.model.requestBeans;

public class FlightChosenRequestBean {
	private Integer departurFlight;
	private Integer returFlight;
	private String departureCompannies;
	private String returnCompannies;
	private Integer passengers;
	private Integer packageChosen;
	
	public Integer getDeparturFlight() {
		return departurFlight;
	}
	public void setDeparturFlight(final Integer departurFlight) {
		this.departurFlight = departurFlight;
	}
	public Integer getReturFlight() {
		return returFlight;
	}
	public void setReturFlight(final Integer returFlight) {
		this.returFlight = returFlight;
	}
	public String getDepartureCompannies() {
		return departureCompannies;
	}
	public void setDepartureCompannies(final String departureCompannies) {
		this.departureCompannies = departureCompannies;
	}
	public String getReturnCompannies() {
		return returnCompannies;
	}
	public void setReturnCompannies(final String returnCompannies) {
		this.returnCompannies = returnCompannies;
	}
	public Integer getPassengers() {
		return passengers;
	}
	public void setPassengers(final Integer passengers) {
		this.passengers = passengers;
	}
	@Override
	public String toString() {
		return "FlightChosenRequestBean [departurFlight=" + departurFlight + ", returFlight=" + returFlight
				+ ", departureCompannies=" + departureCompannies + ", returnCompannies=" + returnCompannies
				+ ", passengers=" + passengers + "]";
	}
	public Integer getPackageChosen() {
		return packageChosen;
	}
	public void setPackageChosen(final Integer packageChosen) {
		this.packageChosen = packageChosen;
	}

}
