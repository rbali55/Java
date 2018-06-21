package airline_queue;

public class Passenger {
	int serviceTime = 0;
	Boolean isFirst = false;

	public Passenger() {
	}
	public Passenger(Boolean isFirst) {
		this.isFirst = isFirst;
	}
	int getServiceTime() {
		return serviceTime;
	}
	void setServiceTime(int m) {
		serviceTime = m;
	}
	Boolean isFirst() {
		return isFirst;
	}
	public int randomArrivalTime(int max, int min) {
		int range = (max - min) + 1;
		return ((int)(Math.random()*range) + min);
	}
}
