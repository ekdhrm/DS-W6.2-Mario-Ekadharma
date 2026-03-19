package question2;

import java.util.*;

public class Respin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int totalOperations = sc.nextInt();
            List<int[]> operationList = new ArrayList<>();
            for (int i = 0; i < totalOperations; i++)
                operationList.add(new int[]{sc.nextInt(), sc.nextInt()});
            System.out.println(classify(operationList));
        }
        sc.close();
    }

    static String classify(List<int[]> operationList) {
        boolean isStack = checkStack(operationList);
        boolean isQueue = checkQueue(operationList);
        boolean isPQ    = checkMaxPQ(operationList);

        int matchCount = (isStack ? 1 : 0) + (isQueue ? 1 : 0) + (isPQ ? 1 : 0);

        if (matchCount == 0) return "tidak ada";
        if (matchCount > 1)  return "tidak yakin";
        if (isStack)         return "stack";
        if (isQueue)         return "queue";
        return "priority queue";
    }

    static boolean checkStack(List<int[]> operationList) {
        Deque<Integer> lifoStorage = new ArrayDeque<>();
        for (int[] operation : operationList) {
            if (operation[0] == 1) {
                lifoStorage.push(operation[1]);
            } else {
                // intValue() used to force unboxing, avoids Integer reference comparison bug above 127
                if (lifoStorage.isEmpty() || lifoStorage.peek().intValue() != operation[1]) return false;
                lifoStorage.pop();
            }
        }
        return true;
    }

    static boolean checkQueue(List<int[]> operationList) {
        Queue<Integer> fifoStorage = new LinkedList<>();
        for (int[] operation : operationList) {
            if (operation[0] == 1) {
                fifoStorage.add(operation[1]);
            } else {
                // intValue() used to force unboxing, avoids Integer reference comparison bug above 127
                if (fifoStorage.isEmpty() || fifoStorage.peek().intValue() != operation[1]) return false;
                fifoStorage.poll();
            }
        }
        return true;
    }

    static boolean checkMaxPQ(List<int[]> operationList) {
        PriorityQueue<Integer> maxHeapStorage = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] operation : operationList) {
            if (operation[0] == 1) {
                maxHeapStorage.add(operation[1]);
            } else {
                // intValue() used to force unboxing, avoids Integer reference comparison bug above 127
                if (maxHeapStorage.isEmpty() || maxHeapStorage.peek().intValue() != operation[1]) return false;
                maxHeapStorage.poll();
            }
        }
        return true;
    }
}