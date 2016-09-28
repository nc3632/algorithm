import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Design a parking lot management system, which would help find a parking space and toll the fee.
 */

enum CarType {
    COMPACT,
    FULL_SIZE,
    TRUCK,
    COMPACT_HANDICAPPED,
    FULL_SIZE_HANDICAPPED,
    TRUCK_HANDICAPPED
}

enum ParkingSpaceType {
    COMPACT,
    FULL_SIZE,
    TRUCK,
    HANDICAPPED
}

class ParkingSpace {
    private int id;
    private ParkingSpaceType type;
    private Date enterTime;
    private Date leaveTime;
    private Calendar calendar;
    private String plateNumber;

    public ParkingSpace(int id, ParkingSpaceType type) {
        this.id = id;
        this.type = type;

        calendar = Calendar.getInstance();
    }

    public int getId() {
        return id;
    }

    public ParkingSpaceType getType() {
        return type;
    }

    public void enter(String plateNumber) {
        this.plateNumber = plateNumber;
        enterTime = calendar.getTime();
    }

    public int leave() {
        leaveTime = calendar.getTime();
        return (int) Math.ceil(TimeUnit.MILLISECONDS.toMinutes(leaveTime.getTime() - enterTime.getTime()) / 60);
    }
}

public class ParkingLot {
    private static Map<CarType, List<ParkingSpaceType>> accessibleParkingSpaceTypes =
            new HashMap<CarType, List<ParkingSpaceType>>() {{
                put(CarType.COMPACT, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.COMPACT,
                        ParkingSpaceType.FULL_SIZE,
                        ParkingSpaceType.TRUCK
                }));
                put(CarType.FULL_SIZE, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.FULL_SIZE,
                        ParkingSpaceType.TRUCK
                }));
                put(CarType.TRUCK, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.TRUCK
                }));
                put(CarType.COMPACT_HANDICAPPED, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.HANDICAPPED,
                        ParkingSpaceType.COMPACT,
                        ParkingSpaceType.FULL_SIZE,
                        ParkingSpaceType.TRUCK
                }));
                put(CarType.FULL_SIZE_HANDICAPPED, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.HANDICAPPED,
                        ParkingSpaceType.FULL_SIZE,
                        ParkingSpaceType.TRUCK
                }));
                put(CarType.TRUCK_HANDICAPPED, Arrays.asList(new ParkingSpaceType[]{
                        ParkingSpaceType.HANDICAPPED,
                        ParkingSpaceType.TRUCK
                }));
            }};

    private static Map<ParkingSpaceType, Double> feeTable = new HashMap<ParkingSpaceType, Double>() {{
        put(ParkingSpaceType.HANDICAPPED, 2.0);
        put(ParkingSpaceType.TRUCK, 5.0);
        put(ParkingSpaceType.FULL_SIZE, 4.0);
        put(ParkingSpaceType.COMPACT, 3.0);
    }};

    private Map<String, ParkingSpace> carLocations = new HashMap<>();
    private Map<ParkingSpaceType, List<ParkingSpace>> availableParkingSpaces = new HashMap<>();

    public ParkingLot() {
        for (ParkingSpaceType type : ParkingSpaceType.values()) {
            availableParkingSpaces.put(type, new ArrayList<>());
        }
    }

    public void addParkingSpaces(ParkingSpaceType type, int num) {
        int start = availableParkingSpaces.get(type).size() + 1;
        for (int i = 0; i < num; i++) {
            availableParkingSpaces.get(type).add(new ParkingSpace(start + i, type));
        }
    }

    public Integer enter(CarType carType, String plateNumber) {
        for (ParkingSpaceType type : accessibleParkingSpaceTypes.get(carType)) {
            if (!availableParkingSpaces.get(type).isEmpty()) {
                ParkingSpace ps = availableParkingSpaces.get(type).remove(0);
                carLocations.put(plateNumber, ps);
                ps.enter(plateNumber);

                return ps.getId();
            }
        }

        return null;
    }

    public double leave(String plateNumber) {
        ParkingSpace ps = carLocations.get(plateNumber);
        int hours = ps.leave();
        availableParkingSpaces.get(ps.getType()).add(ps);

        return hours * feeTable.get(ps.getType());
    }
}
