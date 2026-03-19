package question3;

import java.util.*;

public class DoorResponse {

    static class Student {
        String studentName;
        int remainingChances;

        Student(String studentName, int remainingChances) {
            this.studentName      = studentName;
            this.remainingChances = remainingChances;
        }

        String takeTurn() {
            remainingChances--;
            return remainingChances > 0
                ? studentName + "|Try Again|" + remainingChances
                : studentName + "|Get Out|0";
        }

        boolean hasRemainingChances() { return remainingChances > 0; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalStudents = sc.nextInt();

        String[] studentNames    = new String[totalStudents];
        int[]    chanceAllotment = new int[totalStudents];

        for (int i = 0; i < totalStudents; i++) studentNames[i]    = sc.next();
        for (int i = 0; i < totalStudents; i++) chanceAllotment[i] = sc.nextInt();

        Queue<Student> consultationQueue = new LinkedList<>();
        for (int i = 0; i < totalStudents; i++)
            consultationQueue.add(new Student(studentNames[i], chanceAllotment[i]));

        while (!consultationQueue.isEmpty()) {
            Student currentStudent = consultationQueue.poll();
            System.out.println(currentStudent.takeTurn());
            if (currentStudent.hasRemainingChances()) consultationQueue.add(currentStudent);
        }

        sc.close();
    }
}