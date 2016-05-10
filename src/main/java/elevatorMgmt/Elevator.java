package elevatorMgmt;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Akky on 08-05-2016.
 */
public class Elevator implements Runnable {

    private ElevatorState state = ElevatorState.IDLE;
    private Direction direction = Direction.UNKNOWN;
    private int currentFloor;

    private class RequestElement {
        int floorNo;
        int distance;

        public RequestElement(int floorNo) {
            this.floorNo = floorNo;
            this.distance = currentFloor - floorNo;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }


    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    private Comparator<RequestElement> comparatorUp = (o1, o2) -> o1.distance - o2.distance;

    private Comparator<RequestElement> comparatorDown = (o1, o2) -> o2.distance - o1.distance;

    private PriorityBlockingQueue<RequestElement> processingQueue = new PriorityBlockingQueue<RequestElement>
            (ElevatorProperty.NO_OF_FLOORS);


    public Elevator(int currentFloor) {
        this.currentFloor = currentFloor;
    }


    public boolean moveUp() {
        if (currentFloor == ElevatorProperty.MAX_FLOOR) {
            return false;
        }
        ++currentFloor;
        return true;
    }


    public boolean moveDown() {
        if (currentFloor == ElevatorProperty.MIN_FLOOR) {
            return false;
        }
        --currentFloor;
        return true;
    }

    private void prioritizeQueue() {
        synchronized (processingQueue) {
            Comparator<RequestElement> comparator = Direction.UP.equals(direction) ? comparatorUp : comparatorDown;

            if (comparator.equals(processingQueue.comparator())) {
                processingQueue = new PriorityBlockingQueue<RequestElement>(processingQueue);
            } else {
                PriorityBlockingQueue<RequestElement> tempQueue;
                tempQueue = new PriorityBlockingQueue<>(ElevatorProperty.NO_OF_FLOORS, comparator);
                tempQueue.addAll(processingQueue);
                processingQueue = tempQueue;
            }
        }
    }

    public boolean processRequest(Request request) {


        return true;
    }

    public boolean queueRequest(Request request) {

        direction = request.getDirection();
        prioritizeQueue();
        processingQueue.add(new RequestElement(request.getFloorNo()));
        return true;
    }

    public void run() {

        do {


        } while (true);
    }
}
