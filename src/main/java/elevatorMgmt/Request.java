package elevatorMgmt;

/**
 * Created by Akky on 08-05-2016.
 */
public class Request {
    private int floorNo;
    private Direction direction;

    public Request(int floorNo, Direction direction) {
        this.floorNo = floorNo;
        this.direction = direction;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public Direction getDirection() {
        return direction;
    }

}
