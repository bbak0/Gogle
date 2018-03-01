import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;

 class Ride {
    int startX;
    int startY;
    int endX;
    int endY;
    int start_time;
    int finish_time;
    int time_needed;
    int id;

     public Integer getStartTime() {
         return start_time;
     }

     public Ride(int sX, int sY, int eX, int eY, int start, int finish, int i) {
        this.startX = sX;
        this.startY = sY;
        this.endX = eX;
        this.endY = eY;
        this.start_time = start;
        this.finish_time = finish;
        time_needed = Math.abs(sX - eX) + Math.abs(sY - eY);
        this.id = i;

    }

 }

class Car {

     int finishTime;
     int xPosition;
     int yPosition;
     ArrayList<Integer> rides = new ArrayList<>();

     public Car() {
         this.xPosition = 0;
         this.yPosition = 0;
         this.finishTime = 0;
     }


}


class RideComparator implements Comparator<Ride> {

     public int compare(Ride a, Ride b) {
         return a.getStartTime().compareTo(b.getStartTime());
    }
}

public class Problem {
    Scanner scan = new Scanner(System.in);
    RideComparator ride_comparator = new RideComparator();
    PriorityQueue<Ride> ridesQueue;

    ArrayList<Car> freeCars = new ArrayList<Car>(1000);
    PriorityQueue<Car> usedCars = new PriorityQueue<Car>();

    int R; //rows
    int C; //columns
    int F; //number of vehicles
    int N; //number of rides
    int B; //Bonus
    int T; //number of steps
    int currentT = 0;

    void readInput() {
        R = scan.nextInt();
        C = scan.nextInt();
        F = scan.nextInt();
        N = scan.nextInt();
        B = scan.nextInt();
        T = scan.nextInt();
        ridesQueue = new PriorityQueue<>(N, ride_comparator);

        for (int i = 0; i < F; i++) {
            freeCars.add(new Car());
        }

        for (int i = 0; i < N; i++) {
            ridesQueue.add(new Ride(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), i));
        }
    }

    void choosingRides() {
        for (Ride rides: ridesQueue) {
            Car carUsed = findClosestCarOPTIMIZED(rides);
            if(carUsed == null) {
                carUsed = findClosestCar(rides);
                if(carUsed != null) {
                    carUsed.xPosition = rides.endX;
                    carUsed.yPosition = rides.endY;
                    carUsed.rides.add(rides.id);
                    freeCars.remove(carUsed);
                    usedCars.add(carUsed);
                }
            } else {
                carUsed.xPosition = rides.endX;
                carUsed.yPosition = rides.endY;
                carUsed.rides.add(rides.id);
                freeCars.remove(carUsed);
                usedCars.add(carUsed);
            }
        }
    }

    int distance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    Car findClosestCar(Ride r){
        for (Car c : freeCars){
            int distanceRadius = r.finish_time - r.time_needed - c.finishTime;
            int dist = distance(c.xPosition, c.yPosition, r.startX, r.startY);
            if (dist <= distanceRadius){
                c.finishTime = Math.max(dist + c.finishTime, r.start_time) + r.time_needed;
                return c;
            }
        }
        return null;
    }

    Car findClosestCarOPTIMIZED(Ride r){
        int j;
        for (Car c : freeCars){
            int distanceRadius = r.start_time - c.finishTime;
            int dist = distance(c.xPosition, c.yPosition, r.startX, r.startY);
            if (dist <= distanceRadius){
                c.finishTime = r.start_time + r.time_needed;
                return c;
            }
        }
        return null;
    }

    void printAnswer() {
        for (Car c : freeCars) {
            int n = c.rides.size();
            System.out.print(n + " ");
            for (int i = 0; i < n; i++) {
                System.out.print(c.rides.get(i) + " ");
            }
            System.out.println();
        }
        for (Car c : usedCars) {
            int n = c.rides.size();
            System.out.print(n + " ");
            for (int i = 0; i < n; i++) {
                System.out.print(c.rides.get(i) + " ");
            }
            System.out.println();
        }
    }

    void run() {
        readInput();
        choosingRides();
        printAnswer();
    }

    public static void main(String [ ] args) {
        (new Problem()).run();
    }
}

