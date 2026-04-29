import java.util.*;
class Student
{
    int id;
    String name;
    Map<String, Integer> subjectMarks = new HashMap<>();
    Map<String, String> subjectGrades = new HashMap<>();
    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
class GradeCalculator {
    public static String getGrade(int marks) {
        if (marks >= 90) return "O";
        else if (marks >= 80) return "A+";
        else if (marks >= 70) return "A";
        else if (marks >= 60) return "B+";
        else if (marks >= 50) return "B";
        else if (marks >= 40) return "C";
        else return "F";
    }
}
class MarkManager 
{
    Map<Integer, Student> students = new HashMap<>();
    public void addMarks(int id, String name, String subject, int marks) 
    {
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks value");
            return;
        }
        Student s = students.get(id);
        if (s == null) {
            s = new Student(id, name);
            students.put(id, s);
        }
        if (s.subjectMarks.containsKey(subject)) {
            System.out.println("Record already exists for subject: " + subject);
            return;
        }
        s.subjectMarks.put(subject, marks);
        s.subjectGrades.put(subject, GradeCalculator.getGrade(marks));
        System.out.println("Marks saved for " + subject);
    }
    public void displayReport() {
        for (Student s : students.values()) {
            System.out.println("\nID: " + s.id + ", Name: " + s.name);
            for (String sub : s.subjectMarks.keySet()) {
                System.out.println("Subject: " + sub +
                        ", Marks: " + s.subjectMarks.get(sub) +
                        ", Grade: " + s.subjectGrades.get(sub));
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MarkManager manager = new MarkManager();
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) 
            {
            System.out.print("\nEnter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter number of subjects: ");
            int subCount = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < subCount; j++) {
                System.out.print("Enter Subject Name: ");
                String subject = sc.nextLine();
                System.out.print("Enter Marks: ");
                int marks = sc.nextInt();
                sc.nextLine();
                manager.addMarks(id, name, subject, marks);
            }
        }
        System.out.println("\nReport Cards");
        manager.displayReport();
        sc.close();
    }
}