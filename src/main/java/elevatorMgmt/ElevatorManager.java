package elevatorMgmt;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Akky on 08-05-2016.
 */
public class ElevatorManager {

    private static List<Elevator> elevators;
    private static boolean started = true;
    private static BlockingQueue<Request> requests = new ArrayBlockingQueue<Request>(ElevatorProperty.NO_OF_FLOORS);

    public static void main(String[] args) {

        for (int i = 0; i < ElevatorProperty.NO_OF_ELEVATOR; i++) {
            elevators.add(new Elevator(0));
        }

        for (Elevator elevator : elevators) {
            new Thread(elevator).start();
        }

        do {
            try {
                Request req = requests.take();
                assignRequestToElevator(req);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }while (started);

    }

    private static void assignRequestToElevator(Request req) {

        for (Elevator elevator : elevators) {
            if (elevator.getState().equals(ElevatorState.IDLE)){
                elevator.queueRequest(req);
            }
        }
    }

    public boolean queueRequest(Request request){
        if(!requests.contains(request)){
            requests.add(request);
            return true;
        }
        return false;
    }

}
