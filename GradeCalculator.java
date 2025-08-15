import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int subjectCount = input.nextInt();

        int totalMarks = 0; 
        double percentage;  

        for (int i = 1; i <= subjectCount; i++) {
            System.out.print("Enter marks for subject " + i + " (0 to 100): ");
            int marks = input.nextInt();

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Please enter between 0 and 100.");
                i--; 
                continue;
            }

            totalMarks += marks; 
        }

        percentage = (double) totalMarks / subjectCount;

        String grade;
        if (percentage >= 90) {
            grade = "A";
        } else if (percentage >= 80) {
            grade = "B";
        } else if (percentage >= 70) {
            grade = "C";
        } else if (percentage >= 60) {
            grade = "D";
        } else if (percentage >= 50) {
            grade = "E";
        } else {
            grade = "F";
        }

        System.out.println("\n------ RESULT ------");
        System.out.println("Total Marks = " + totalMarks);
        System.out.printf("Average Percentage = %.2f%%\n", percentage);
        System.out.println("Grade = " + grade);

        input.close();}
}