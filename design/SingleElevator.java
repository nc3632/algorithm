import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Design an elevator system. The basic algorithm is that
 * 1. Keep going in the current direction until there are no more requests at this direction
 * 2. If there are no more requests, then idle, or start moving in the opposite direction if needed.
 */

public class SingleElevator implements Runnable {
    private enum State {
        MOVE_UP, MOVE_DOWN, IDLE
    }

    private Queue<Integer> upRequests, upRequestsUnServed;
    private Set<Integer> upRequestsSet;

    private Queue<Integer> downRequests, downRequestsUnServed;
    private Set<Integer> downRequestsSet;

    private int currFloor;

    private State state;

    public SingleElevator() {
        currFloor = 1;

        state = State.IDLE;

        upRequests = new PriorityQueue<>();
        upRequestsUnServed = new PriorityQueue<>();
        upRequestsSet = new HashSet<>();

        downRequests = new PriorityQueue<>((Integer i1, Integer i2) -> i2.compareTo(i1));
        downRequestsUnServed = new PriorityQueue<>((Integer i1, Integer i2) -> i2.compareTo(i1));
        downRequestsSet = new HashSet<>();
    }

    public synchronized void upCall(int floor) {
        // Ignore the call if the elevator is at this floor or it has already been requested and not served.
        if (currFloor == floor || upRequestsSet.contains(floor)) {
            return;
        }

        upRequestsSet.add(floor);

        if ((state == State.MOVE_UP)) {
            // If this trip can pass this floor, put it in the request queue.
            // Otherwise, save it to the backup queue and wait for the next "up" trip.
            if (floor > currFloor) {
                upRequests.offer(floor);
            } else if (floor < currFloor) {
                upRequestsUnServed.offer(floor);
            }
        } else {
            // As long as the elevator is not going UP, we save the call to the request queue
            // so it will be served whenever the elevator goes up.
            upRequests.offer(floor);
        }
    }

    public synchronized void downCall(int floor) {
        // Ignore this request if elevator is at this floor or it has already been requested but not served.
        if (currFloor == floor || downRequestsSet.contains(floor)) {
            return;
        }

        if (state == State.MOVE_DOWN) {
            // Put the request to the queue if it can be served in this trip
            // Otherwise, put it in the backup queue so it could be served in the next trip.
            if (floor < currFloor) {
                downRequests.offer(floor);
            } else if (floor > currFloor) {
                downRequestsUnServed.offer(floor);
            }
        } else {
            // As long as the elevator is not going down, save it to the request queue.
            downRequests.offer(floor);
        }
    }

    public synchronized void offCall(int floor) {
        // Ignore if elevator is already at this floor.
        if (floor == currFloor) {
            return;
        }

        // Where this call goes depends on how to serve it as soon as possible.
        if (floor > currFloor) {
            upRequests.add(floor);
        } else {
            downRequests.add(floor);
        }
    }

    // Returns true if door is opened.
    private synchronized boolean moveUp() {
        currFloor++;

        if (currFloor == upRequests.peek()) {
            openDoor();
            return true;
        }

        return false;
    }

    private synchronized boolean moveDown() {
        currFloor--;

        if (currFloor == downRequests.peek()) {
            openDoor();
            return true;
        }

        return false;
    }

    private synchronized void openDoor() {
        if (state == State.MOVE_UP) {
            upRequestsSet.remove(currFloor);
            upRequests.poll();
        } else if (state == State.MOVE_DOWN) {
            downRequestsSet.remove(currFloor);
            downRequests.poll();
        }

        System.out.println("Served floor " + currFloor);
    }

    private synchronized void calcNextMove() {
        if (state == State.MOVE_UP) {
            // Still have up requests, keep going up.
            if (!upRequests.isEmpty()) {
                return;
            }

            if (!downRequests.isEmpty()) {
                // The down request is at upper level, keep going up.
                if (currFloor < downRequests.peek()) {
                    return;
                }

                // Change the status to going down.
                state = state.MOVE_DOWN;
                Queue<Integer> temp = upRequests;
                upRequests = upRequestsUnServed;
                upRequestsUnServed = temp;

                // Need to check if needs to open the door at this floor.
                if (currFloor == downRequests.peek()) {
                    openDoor();
                }
            }

            // If down queue is also empty, put elevator in
            if (downRequests.isEmpty()) {
                state = State.IDLE;
            }
        } else if (state == State.MOVE_DOWN) {
            // Still have down requests, keep going down.
            if (!downRequests.isEmpty()) {
                return;
            }

            if (!upRequests.isEmpty()) {
                // Keep going down as the up request is at lower level
                if (currFloor > upRequests.peek()) {
                    return;
                }

                // Change to going up.
                state = State.MOVE_UP;
                Queue<Integer> temp = downRequests;
                downRequests = downRequestsUnServed;
                downRequestsUnServed = temp;

                // See if door needs to be opened to serve the first up request.
                if (currFloor == upRequests.peek()) {
                    openDoor();
                }
            }

            // If all up requests is gone, change to IDLE state
            if (upRequests.isEmpty()) {
                state = State.IDLE;
            }
        } else {
            if (!upRequests.isEmpty()) {
                state = State.MOVE_UP;
            } else if (!downRequests.isEmpty()) {
                state = state.MOVE_DOWN;
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (state == State.MOVE_UP) {
                    // Simulate moving to the next floor
                    Thread.sleep(1000);

                    boolean doorOpened = moveUp();

                    if (doorOpened) {
                        // Simulate customer entering and closing the door.
                        Thread.sleep(1000);

                        calcNextMove();
                    }
                } else if (state == State.MOVE_DOWN) {
                    // Simulate moving to the next floor
                    Thread.sleep(1000);

                    boolean doorOpened = moveDown();

                    if (doorOpened) {
                        // Simulate customer entering and closing the door.
                        Thread.sleep(1000);

                        // Customer going in the opposite direction might get a chance getting in.
                        calcNextMove();
                    }
                } else {
                    calcNextMove();
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SingleElevator singleElevator = new SingleElevator();
        new Thread(singleElevator).start();

        singleElevator.upCall(5);
        singleElevator.offCall(10);

        singleElevator.upCall(2);
        singleElevator.offCall(7);

        Thread.sleep(30000);
    }
}
