package airline_queue;

public class Check_In_Queue implements Queue {
	private int size, currMax, totalServiced, arrivalTime, head, tail;
	private Object[] queueArray;
	ArrivalTime time = null;
	
	public Check_In_Queue(int queueSize, ArrivalTime time) {
		queueArray = new Object[queueSize];
		this.time = time;
	}

	public boolean queueIsEmpty() {
		return size == 0;
	}
	public int size() {
		return size;
	}
	public int maxLength() {
		for(int i = 0; i < queueArray.length - 1; i++) {
			if(queueArray.length > currMax)
				currMax = queueArray.length;
		}
		return currMax;
	}
	public int totalServiced() {
		return totalServiced;
	}
	public void setArrivalTime(int m) {
		arrivalTime = m;
	}
	public void addQueue(Object o) {
		if(size == queueArray.length) {
			//do nothing, some exception
		}
		queueArray[tail] = o;
		tail = (tail + 1) % queueArray.length;
		size++;
	}
	
	public Object removeFromQueue() {
		if(size == 0) {
			//do nothing
		}
		Object o = queueArray[head];
		queueArray[head] = null;
		head = (head + 1) % queueArray.length;
		size--;
		totalServiced++;
		return o;
	}
}
