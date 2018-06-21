package airline_queue;

public class ServicedPassengers {
	private Passenger[] passArray;
	int totalPassengers = 0;
	int length = 10000;
	int numFirst = 0, numCoach = 0;
	
	public ServicedPassengers() {
		passArray = new Passenger[length];
	}
	
	void addPassenger(Passenger p) {
		try {
			passArray[totalPassengers] = p;
		}catch(Exception e) {
			length = 2*length;
			Passenger[] temp =  new Passenger[length];
			
			for(int i = 0; i < passArray.length; i++)
				temp[i] = passArray[i];
			
			passArray = temp;
		}
		totalPassengers++;
		
		if(p.isFirst()) {
			numFirst++;
		}else {
			numCoach++;
		}
	}
	
	int maximumServiceTime() {
		int currMax = 0;
		for(int i = 0; i < totalPassengers - 1; i++) {
			if(passArray[i].getServiceTime() > currMax)
				currMax = passArray[i].getServiceTime();
		}
		return currMax;
	}
	
	int averageServiceTime() {
		int total = 0;
		for(int i = 0; i < totalPassengers - 1; i++)
			total += passArray[i].getServiceTime();
		return (total/totalPassengers);
	}
	
	int numFirstClass() {
		return numFirst;
	}
	int numCoachClass() {
		return numCoach;
	}

}
