package airline_queue;

public interface Queue {
	void addQueue(Object o);
	Object removeFromQueue();
	int size();
	boolean queueIsEmpty();
}
