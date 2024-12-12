package com.tehranuniversity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class UniversityStudentsDataBase {
    private static final Map<Integer, String> students = new HashMap<>();
    private static final String FILE_PATH = "src/main/resources/students.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== Student Database ===");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Show All Students");
            System.out.println("5. Save to Fil");
            System.out.println("6. for  Exit");
            System.out.print("Choose an option: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        addStudent(scanner);
                        break;
                    case 2:
                        searchStudent(scanner);
                        break;
                    case 3:
                        deleteStudent(scanner);
                        break;
                    case 4:
                        showAllStudents();
                        break;
                    case 5:
                        saveToFile();
                        System.out.println("Data saved! successfully!");
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice!. please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a number." + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Error: Invalid input .Please enter a number.");
                e.getCause();
            } catch (NullPointerException e) {
                System.out.println("Null Pointer Error:: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            } finally {
                System.out.println("Returning to main menu...");
            }

        }


    }

    private static void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Map.Entry<Integer, String> entry : students.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n" + entry.getClass() + "\n" + entry.hashCode()
                        + "\n " + entry.getClass().getName());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a number." + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error: Invalid input .Please enter a number.");
            e.getCause();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer Error:: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }

    }

    private static void showAllStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students in the database.");
            } else {
                students.forEach((id, name) -> System.out.println("ID : " + id + "   " + "Name :" + " " + name));
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format.");
        } catch (NullPointerException e) {
            System.out.println("Error :  NullPointerException " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + " " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }
    }

    private static void deleteStudent(Scanner scanner) {
        try {
            System.out.print("Enter student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (!students.containsKey(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " " + " not found.");
            }
            students.remove(id);
            System.out.println("Student deleted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format.");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + " " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }
    }

    private static void searchStudent(Scanner scanner) {
        try {
            System.out.println("Enter student ID to search:");
            int id = Integer.parseInt(scanner.nextLine());

            if (!students.containsKey(id)) {
                throw new StudentNotFoundException("Student with ID " + id + " " + " not found.");
            }
            System.out.println("Student founded with id : " + id + " " + students.get(id));

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid ID format.");
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + " " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }
    }


    private static void addStudent(Scanner scanner) {

        try {
            System.out.println("Enter student ID:");
            int id = Integer.parseInt(scanner.nextLine());
            if (students.containsKey(id)) {
                throw new IllegalArgumentException("Student with this ID already exists.");
            } else {
                System.out.println("Enter student name:");
                String name = scanner.nextLine();
                students.put(id, name);
                System.out.println("Student added successfully!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input . Please enter a valid number." + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error IllegalArgumentException.  " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + " " + e.getMessage());
        } finally {
            System.out.println("Returning to main menu...");
        }

    }


    // custom Exception
    private static class StudentNotFoundException extends Exception {
        public StudentNotFoundException(String message) {
            super(message);
        }

    }

}

