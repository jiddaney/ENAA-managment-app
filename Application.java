import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagementSystem managementSystem = new ManagementSystem(scanner);
        managementSystem.run();
    }
}

class ManagementSystem {  //adding managment system

    private final Scanner scanner;
    private final ArrayList<Classe> classes;
    private final ArrayList<Student> students;
    private final ArrayList<Trainer> trainers;
    private int studentIdCounter;
    private int trainerIdCounter;

    public ManagementSystem(Scanner scanner) {
        this.scanner = scanner;
        this.classes = new ArrayList<>();
        this.students = new ArrayList<>();
        this.trainers = new ArrayList<>();
        this.studentIdCounter = 1;
        this.trainerIdCounter = 1;
    }

    public void run() {
        while (true) {
              //adding menu
            try {
                System.out.println("\nWelcome to the ENAA Management Application");
                System.out.println("1. Manage Classes");
                System.out.println("2. Display Classes");
                System.out.println("3. Show All Students");
                System.out.println("4. Manage Trainers");
                System.out.println("5. Manage Students");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = getValidInt();

                switch (choice) {
                    //adding options
                    case 1 -> manageClasses();
                    case 2 -> displayClasses();
                    case 3 -> displayStudents();
                    case 4 -> manageTrainers();
                    case 5 -> manageStudents();
                    case 6 -> {
                        System.out.println("Exiting application. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace(); // Debugging purposes
            }
        }
    }

    private void manageClasses() {
        while (true) {
            try {
                //adding menu #2
                System.out.println("\nClass Management Menu:");
                System.out.println("1. Create Class");
                System.out.println("2. Modify Class Information");
                System.out.println("3. Delete a Class");
                System.out.println("4. Return to Main Menu");
                System.out.print("Choose an option: ");

                int choice = getValidInt();

                switch (choice) {
                    case 1 -> createClass();
                    case 2 -> modifyClass();
                    case 3 -> deleteClass();
                    case 4 -> {
                        System.out.println("Returning to main menu...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while managing classes: " + e.getMessage());
            }
        }
    }

    private void createClass() {
        try {
            //adding class creation
            System.out.print("Enter the class name: ");
            String className = scanner.next();

            if (trainers.isEmpty()) {
                System.out.println("No trainers available. Please add a trainer first.");
                return;
            }

            System.out.println("Available Trainers:");
            for (Trainer trainer : trainers) {
                System.out.println(trainer);
            }

            System.out.print("Enter the trainer ID for this class: ");
            int trainerId = getValidInt();

            Trainer trainer = Trainer.findTrainerById(trainerId, trainers);
            if (trainer == null) {
                System.out.println("Trainer not found. Please add the trainer first.");
                return;
            }

            Classe classe = new Classe(className, trainer);
            classes.add(classe);
            System.out.println("Class created successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while creating the class: " + e.getMessage());
        }
    }

    private void modifyClass() {
        try {
            //adding class modification
            System.out.print("Enter the class name to modify: ");
            String className = scanner.next();

            Classe foundClass = Classe.findClassByName(className, classes);
            if (foundClass == null) {
                System.out.println("Class not found!");
                return;
            }

            System.out.print("Enter the new name for this class: ");
            String newName = scanner.next();
            foundClass.setClassName(newName);
            System.out.println("Class information updated successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while modifying the class: " + e.getMessage());
        }
    }

    private void deleteClass() {
        try {
            System.out.print("Enter the class name to delete: ");
            String className = scanner.next();

            Classe classe = Classe.findClassByName(className, classes);
            if (classe == null) {
                System.out.println("Class not found.");
            } else {
                classes.remove(classe);
                System.out.println("Class deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the class: " + e.getMessage());
        }
    }

    private void manageTrainers() {
        while (true) {
            try {
                //adding managment trainers menu
                System.out.println("\nTrainer Management Menu:");
                System.out.println("1. Add Trainer");
                System.out.println("2. Display Trainers");
                System.out.println("3. Return to Main Menu");
                System.out.print("Choose an option: ");

                int choice = getValidInt();

                switch (choice) {
                    case 1 -> addTrainer();
                    case 2 -> displayTrainers();
                    case 3 -> {
                        System.out.println("Returning to main menu...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while managing trainers: " + e.getMessage());
            }
        }
    }

    private void addTrainer() {
        try {
            int trainerId = trainerIdCounter++;
            System.out.print("Enter trainer's first name: ");
            String firstName = scanner.next();
            System.out.print("Enter trainer's last name: ");
            String lastName = scanner.next();
            System.out.print("Enter trainer's email: ");
            String email = scanner.next();
            System.out.print("Enter trainer's phone number: ");
            String phoneNumber = scanner.next();

            Trainer trainer = new Trainer(trainerId, firstName, lastName, email, phoneNumber);
            trainers.add(trainer);
            System.out.println("Trainer added successfully with ID: " + trainerId);
        } catch (Exception e) {
            System.out.println("An error occurred while adding the trainer: " + e.getMessage());
        }
    }

    private void displayTrainers() {
        try {
            if (trainers.isEmpty()) {
                System.out.println("No trainers to display.");
            } else {
                System.out.println("\nList of Trainers:");
                for (Trainer trainer : trainers) {
                    System.out.println(trainer);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying trainers: " + e.getMessage());
        }
    }

    private void manageStudents() {
        while (true) {
            try {
                System.out.println("\nStudent Management Menu:");
                System.out.println("1. Add Student");
                System.out.println("2. Modify Student Information");
                System.out.println("3. Delete a Student");
                System.out.println("4. Return to Main Menu");
                System.out.print("Choose an option: ");

                int choice = getValidInt();

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> modifyStudent();
                    case 3 -> deleteStudent();
                    case 4 -> {
                        System.out.println("Returning to main menu...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while managing students: " + e.getMessage());
            }
        }
    }

    private void addStudent() {
        try {
            int studentId = studentIdCounter++;
            System.out.print("Enter student's first name: ");
            String firstName = scanner.next();
            System.out.print("Enter student's last name: ");
            String lastName = scanner.next();
            System.out.print("Enter student's email: ");
            String email = scanner.next();
            System.out.print("Enter student's phone number: ");
            String phoneNumber = scanner.next();

            if (classes.isEmpty()) {
                System.out.println("No classes available. Please create a class first!");
                return;
            }

            System.out.println("Available classes:");
            for (Classe classe : classes) {
                System.out.println("- " + classe.getClassName());
            }

            System.out.print("Enter the class name: ");
            String className = scanner.next();

            Classe foundClass = Classe.findClassByName(className, classes);
            if (foundClass == null) {
                System.out.println("Class not found! Student was not added.");
                return;
            }

            Student student = new Student(studentId, firstName, lastName, email, phoneNumber, className);
            students.add(student);
            System.out.println("Student added successfully with ID: " + studentId);
        } catch (Exception e) {
            System.out.println("An error occurred while adding the student: " + e.getMessage());
        }
    }

    private void modifyStudent() {
        try {
            System.out.print("Enter the Student ID to modify: ");
            int studentId = getValidInt();

            Student student = Student.findStudentById(studentId, students);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter new first name: ");
            student.setFirstName(scanner.next());
            System.out.print("Enter new last name: ");
            student.setLastName(scanner.next());
            System.out.print("Enter new email: ");
            student.setEmail(scanner.next());
            System.out.print("Enter new phone number: ");
            student.setPhoneNumber(scanner.next());
            System.out.println("Student information updated successfully!");
        } catch (Exception e) {
            System.out.println("An error occurred while modifying the student: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try {
            System.out.print("Enter the Student ID to delete: ");
            int studentId = getValidInt();

            Student student = Student.findStudentById(studentId, students);
            if (student == null) {
                System.out.println("Student not found.");
            } else {
                students.remove(student);
                System.out.println("Student deleted successfully.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the student: " + e.getMessage());
        }
    }

    private int getValidInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private void displayStudents() {
        try {
            if (students.isEmpty()) {
                System.out.println("No students to display.");
            } else {
                System.out.println("\nList of Students:");
                for (Student student : students) {
                    System.out.println(student);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying students: " + e.getMessage());
        }
    }

    private void displayClasses() {
        try {
            if (classes.isEmpty()) {
                System.out.println("No classes to display.");
            } else {
                System.out.println("\nList of Classes:");
                for (Classe classe : classes) {
                    System.out.println(classe);
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while displaying classes: " + e.getMessage());
        }
    }
}

