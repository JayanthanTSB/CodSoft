class Student {
	    private String name;
	    private String rollNumber;
	    private String grade;
	    private String details;

	    public Student(String name, String rollNumber, String grade, String details) {
	        this.name = name;
	        this.rollNumber = rollNumber;
	        this.grade = grade;
	        this.details = details;
	    }

	    @Override
	    public String toString() {
	        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + ", Details: " + details;
	    }

	    public String getRollNumber() {
	        return rollNumber;
	    }
	

}


class SMS {
	    private List<Student> students;
	    private String fileName;

	    public SMS(String fileName) {
	        this.fileName = fileName;
	        students = loadStudents();
	    }

	    private List<Student> loadStudents() {
	        List<Student> students = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                students.add(new Student(data[0], data[1], data[2], data[3]));
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return students;
	    }

	    private void saveStudents() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
	            for (Student student : students) {
	                bw.write(student.toString());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void addStudent(Student student) {
	        students.add(student);
	        saveStudents();
	    }

	    public void removeStudent(String rollNumber) {
	        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
	        saveStudents();
	    }

	    public Student searchStudent(String rollNumber) {
	        for (Student student : students) {
	            if (student.getRollNumber().equals(rollNumber)) {
	                return student;
	            }
	        }
	        return null;
	    }

	    public void displayAllStudents() {
	        for (Student student : students) {
	            System.out.println(student);
	        }
	    }
	}


public class Management {
	public static void main(String[] args) {
        SMS studentSystem = new SMS("students.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Exit\n");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("\nEnter student's name: ");
                    String name = scanner.nextLine();
                    System.out.print("\nEnter student's roll number: ");
                    String rollNumber = scanner.nextLine();
                    System.out.print("\nEnter student's grade: ");
                    String grade = scanner.nextLine();
                    System.out.print("\nEnter additional details (optional): ");
                    String details = scanner.nextLine();
                    Student newStudent = new Student(name, rollNumber, grade, details);
                    studentSystem.addStudent(newStudent);
                    System.out.println("\nStudent added successfully.");
                    break;
                case "2":
                    System.out.print("\nEnter the roll number of the student to remove: ");
                    String rollToRemove = scanner.nextLine();
                    studentSystem.removeStudent(rollToRemove);
                    System.out.println("\nStudent removed successfully.");
                    break;
                case "3":
                    System.out.print("\nEnter the roll number of the student to search: ");
                    String rollToSearch = scanner.nextLine();
                    Student foundStudent = studentSystem.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("\nStudent found:");
                        System.out.println(foundStudent);
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;
                case "4":
                    System.out.println("\nAll students:");
                    studentSystem.displayAllStudents();
                    break;
                case "5":
                    System.out.println("\nExiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
