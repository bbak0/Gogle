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

     private int finishTime;
     private int xPosition;
     private int yPosition;
     ArrayList<Integer> rides = new ArrayList<>();

     public Car() {
         this.xPosition = 0;
         this.yPosition = 0;
         this.finishTime = 0;
     }


    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getFinishTime() {

       return finishTime;
    }


    class RideComparator implements Comparator<Ride> {

     public int compare(Ride a, Ride b) {
         return a.getStartTime().compareTo(b.getStartTime());
    }
}

    class CarComparatorByFinishTime implements  Comparator<Car> {

        @Override
        public int compare(Car car1, Car car2) {
            return car1.getFinishTime().compareTo(car2.getFinishTime());
        }
    }

public class Problem {
    Scanner scan = new Scanner(System.in);
    RideComparator ride_comparator = new RideComparator();
    CarComparatorByFinishTime car_comparator = new CarComparatorByFinishTime();
    PriorityQueue<Ride> ridesQueue;

    ArrayList<Car> freeCars = new ArrayList<Car>(1000);
    PriorityQueue<Car> usedCars = new PriorityQueue<Car>(1000, car_comparator);

    int R; //rows
    int C; //columns
    int F; //number of vehicles
    int N; //number of rides
    int B; //Bonus
    int T; //number of steps
    Car[] cars;

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

    void placeFreeCars(int T) {

        if (usedCars.peek().finishTime <= T) {

                freeCars.add(usedCars.poll());

           }

       }
    }

    void choosingRides() {
        for (Ride rides: ridesQueue) {
            Car carUsed = null;
            if(carUsed != null) {
                carUsed.xPosition = rides.endX;
                carUsed.yPosition = rides.endY;
                carUsed.rides.add(rides.id);
            }
        }
    }

    void run() {
        readInput();
    }
}

