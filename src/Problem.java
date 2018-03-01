import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

 class Ride {
    int startX;
    int startY;
    int endX;
    int endY;
    int start_time;
    int finish_time;
    int time_needed;

     public Integer getStartTime() {
         return start_time;
     }

     public Ride(int sX, int sY, int eX, int eY, int start, int finish) {
        this.startX = sX;
        this.startY = sY;
        this.endX = eX;
        this.endY = eY;
        this.start_time = start;
        this.finish_time = finish;
        time_needed = Math.abs(sX - eX) + Math.abs(sY - eY);

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

    int R; //rows
    int C; //columns
    int F; //number of vehicles
    int N; //number of rides
    int B; //Bonus
    int T; //number of steps

    void readInput() {
        R = scan.nextInt();
        C = scan.nextInt();
        F = scan.nextInt();
        N = scan.nextInt();
        B = scan.nextInt();
        T = scan.nextInt();
        ridesQueue = new PriorityQueue<>(N, ride_comparator);

        for (int i = 0; i < N; i++) {
            ridesQueue.add(new Ride(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
    }

    void run() {
        readInput();
    }
}
