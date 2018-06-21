package airline_queue;

import java.util.Scanner;

public class Simulator {

	static ArrivalTime at = new ArrivalTime();
	static ServicedPassengers serviced = new ServicedPassengers() {};
	static int simulationTime = 40, numFirst = 0, numCoach = 0;
	
	//first class queue
	static Check_In_Queue firstClassQueue = new Check_In_Queue(5000, at) {};
	static Check_In_Queue coachClassQueue = new Check_In_Queue(5000, at) {};
	
	//first class has two service stations
	static ServiceStation fcSS1 = new ServiceStation(at) {};
	static ServiceStation fcSS2 = new ServiceStation(at) {};
	
	//coach class has three service stations
	static ServiceStation ccSS1 = new ServiceStation(at) {};
	static ServiceStation ccSS2 = new ServiceStation(at) {};
	static ServiceStation ccSS3 = new ServiceStation(at) {};
	
	public Simulator() {
		
	}
	
	public static void main(String[] args) {
		//ystem.out.println("time till boarding: 40 minutes");
		System.out.println("time to simulate: ");
		Scanner scan = new Scanner(System.in);
		String simIN = scan.nextLine();
		simulationTime = Integer.parseInt(simIN);		
		
		System.out.println("average arrival rate for first class: ");
		String fcIN = scan.nextLine();
		int atFC = Integer.parseInt(fcIN);		
		System.out.println("average service rate for first class: ");
		String afcIN = scan.nextLine();
		int asrFC = Integer.parseInt(afcIN);
		
		System.out.println("average arrival rate for coach class: ");
		String ccIN = scan.nextLine();
		int atCC = Integer.parseInt(ccIN);
		System.out.println("average service rate for coach class: ");
		String asrCCIN = scan.nextLine();
		int asrCC = Integer.parseInt(asrCCIN);
		
		firstClassQueue.setArrivalTime(atFC);
		coachClassQueue.setArrivalTime(atCC);
		
		run();
	}

	public static void run() {
		while(at.getArrivalTime() < simulationTime) {
			addPassengers();
			servicePassengers();
			boardPassengers();
			at.incTime();
		}
		getServiceTimes();
	}

	private static void getServiceTimes() {
		System.out.println("Simulation duration: " + serviced.maximumServiceTime());
		System.out.println("Maximum length of first class queue: " + firstClassQueue.maxLength());
		System.out.println("Maximum length of coach class queue: " + coachClassQueue.maxLength());
		System.out.println("Max service time: " + serviced.maximumServiceTime());
		System.out.println("Average service time: " + serviced.averageServiceTime());
		System.out.println("Num of first class: " + numFirst);
		System.out.println("Num of coach class: " + numCoach);
	}

	public static void addPassengers() {
		//create passenger arrival time
		Passenger passenger = new Passenger();
		int passengerAT = passenger.randomArrivalTime(simulationTime, 1);
		passenger.setServiceTime(passengerAT);
		//create passenger type
		int passengerClass = (int)(Math.random()*2);
		
		//add to queue
		if(passengerClass == 1) {
			passenger.setServiceTime(passengerAT);
			firstClassQueue.addQueue(passenger);
		}else {
			passenger.setServiceTime(passengerAT);
			coachClassQueue.addQueue(passenger);
		}
	}
	
	public static void servicePassengers() {
		if(fcSS1.isOccupied() == false) {
			if(!firstClassQueue.queueIsEmpty()) {
				Passenger p = (Passenger) firstClassQueue.removeFromQueue();
				fcSS1.addPassenger(p);
			}
		}
		if(fcSS2.isOccupied() == false) {
			if(!firstClassQueue.queueIsEmpty()) {
				Passenger p = (Passenger) firstClassQueue.removeFromQueue();
				fcSS2.addPassenger(p);
			}
		}
		if(ccSS1.isOccupied() == false) {
			if(!coachClassQueue.queueIsEmpty()) {
				Passenger p = (Passenger) coachClassQueue.removeFromQueue();
				ccSS1.addPassenger(p);
			}
		}
		if(ccSS2.isOccupied() == false) {
			if(!coachClassQueue.queueIsEmpty()) {
				Passenger p = (Passenger) coachClassQueue.removeFromQueue();
				ccSS1.addPassenger(p);
			}
		}
		if(ccSS3.isOccupied() == false) {
			if(!coachClassQueue.queueIsEmpty()) {
				Passenger p = (Passenger) coachClassQueue.removeFromQueue();
				ccSS3.addPassenger(p);
			}
		}	
	}	

	public static void boardPassengers() {
		if(fcSS1.isFinished()) {
			Passenger passenger = fcSS1.removePassenger();
			serviced.addPassenger(passenger);
			numFirst++;
		}
		if(fcSS2.isFinished()) {
			Passenger passenger = fcSS2.removePassenger();
			serviced.addPassenger(passenger);
			numFirst++;
		}
		if(ccSS1.isFinished()) {
			Passenger passenger = ccSS1.removePassenger();
			serviced.addPassenger(passenger);
			numCoach++;
		}
		if(ccSS2.isFinished()) {
			Passenger passenger = ccSS2.removePassenger();
			serviced.addPassenger(passenger);
			numCoach++;
		}
		if(ccSS3.isFinished()) {
			Passenger passenger = ccSS3.removePassenger();
			serviced.addPassenger(passenger);
			numCoach++;
		}
	}
}
