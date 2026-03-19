package question1;
import java.util.*;

public class LoveLetter {

    static class Letter implements Comparable<Letter> {
        String name;
        int duration, priority;
        String status = "QUEUED";

        Letter(String name, int duration, int priority) {
            this.name = name;
            this.duration = duration;
            this.priority = priority;
        }

        @Override
        public int compareTo(Letter other) {
            return Integer.compare(this.priority, other.priority);
        }

        @Override
        public String toString() {
            return priority + " | " + name + " | " + status;
        }
    }

    static List<Letter> allLetters     = new ArrayList<>();
    static PriorityQueue<Letter> queue = new PriorityQueue<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            Letter l = new Letter(sc.next(), sc.nextInt(), sc.nextInt());
            allLetters.add(l);
            queue.add(l);
        }

        int time = 0;
        while (!queue.isEmpty()) {
            Letter current = queue.poll();
            current.status = "PENDING";
            printState(time);

            time += current.duration;
            current.status = "SENT";
        }

        printState(time);
        sc.close();
    }

    static void printState(int time) {
        List<Letter> sorted = new ArrayList<>(allLetters);
        sorted.sort(Comparator.comparingInt(l -> l.priority));
        System.out.println("Time : " + time);
        for (Letter l : sorted) System.out.println(l);
        System.out.println();
    }
}