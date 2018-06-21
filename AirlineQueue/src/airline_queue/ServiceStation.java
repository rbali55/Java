package airline_queue;

public class ServiceStation {
	Passenger currentPassenger = null;
	private int passengerArrivalTime = 0, timePassengerFinished = 0;
	int totalServiceTime = 0;
	ArrivalTime time = null;
	
	public ServiceStation(ArrivalTime time) {
		this.time = time;
	}
	
	public void addPassenger(Passenger p) {
		currentPassenger = p;
		passengerArrivalTime = time.getArrivalTime();
		int timeToServe = randomServiceTime(1, 0);
		if(timeToServe < 1/6) {
			timePassengerFinished = passengerArrivalTime + timeToServe;
			currentPassenger.setServiceTime(timeToServe);
			currentPassenger.isFirst.equals(true);			
		}
		if(timeToServe < 1/2) {
			timePassengerFinished = passengerArrivalTime + timeToServe;
			currentPassenger.setServiceTime(timeToServe);
			currentPassenger.isFirst.equals(false);
		}
	}

	public Passenger removePassenger() {
		Passenger p = currentPassenger;
		currentPassenger = null;
		return p;
	}
	void getTotalServiceTime(int m) {
		totalServiceTime = m;
	}
	public int randomServiceTime(int max, int min) {
		int range = (max - min) + 1;
		return ((int)(Math.random()*range) + min);
	}
	Boolean isOccupied() {
		return (currentPassenger != null);
	}
	Boolean isFinished() {
		return (isOccupied() && (time.getArrivalTime() >= timePassengerFinished));
	}
}
