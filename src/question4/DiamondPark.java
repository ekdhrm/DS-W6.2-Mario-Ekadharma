package question4;

import java.util.*;

public class DiamondPark {

    static class Visitor {
        String visitorName;
        int moneyCarried;

        Visitor(String visitorName, int moneyCarried) {
            this.visitorName  = visitorName;
            this.moneyCarried = moneyCarried;
        }

        boolean isBlacklisted() { return visitorName.equals("Jeff"); }

        @Override
        public String toString() { return visitorName; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalVisitors = sc.nextInt();
        sc.nextLine();

        String[] visitorNames   = sc.nextLine().split(",\\s*");
        String[] moneyAmounts   = sc.nextLine().split(",\\s*");

        PriorityQueue<Visitor> wealthSortedQueue = new PriorityQueue<>(
            (first, second) -> second.moneyCarried - first.moneyCarried
        );

        for (int i = 0; i < totalVisitors; i++) {
            Visitor visitor = new Visitor(visitorNames[i].trim(), Integer.parseInt(moneyAmounts[i].trim()));
            if (!visitor.isBlacklisted()) wealthSortedQueue.add(visitor);
        }

        List<String> sortedVisitorNames = new ArrayList<>();
        while (!wealthSortedQueue.isEmpty()) sortedVisitorNames.add(wealthSortedQueue.poll().toString());

        System.out.println(sortedVisitorNames);
        sc.close();
    }
}