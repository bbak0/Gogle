import java.util.Scanner;

public class Problem {
    Scanner scan = new Scanner(System.in);

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
        Ride[] rides = new Ride[N];

        for (int i = 0; i < N; i++) {
            rides[i] = new Ride(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
        }
    }

    void run() {
        readInput();
    }
}
