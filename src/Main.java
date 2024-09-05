import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //createAdmin();
        principalMenu();
    }

    public static void principalMenu() {
        Borrow.loadBorrow();
        Story.loadStory();
        Borrow.loadBorrowed();
        Book.loadBooks();
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println("************************************************");
                System.out.println("**HELLO WELCOME TO LIBRARY MANAGEMENT SYSTEM !**");
                System.out.println("********* by Tholde Pathfinder Rftn ************");
                System.out.println("************************************************");
                System.out.println("****************** MENU PAGE *******************");
                System.out.println("************************************************");
                System.out.println("*********         You are :           **********");
                System.out.println("*********         1.Admin             **********");
                System.out.println("*********         2.Biblioman         **********");
                System.out.println("*********         3.Student           **********");
                System.out.println("*********         4.Professor         **********");
                System.out.println("*********         5.Exit              **********");
                System.out.println("************************************************");
                System.out.print("Your choice : ");
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        adminAuthenticationLogin();
                        break;
                    case 2:
                        bibliomanAuthenticationLogin();
                        break;
                    case 3:
                        studentAuthenticationLogin();
                        break;
                    case 4:
                        professorAuthenticationLogin();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid Choice !");
                }
            } catch (Exception e) {
                System.out.println("=>>Input Error");
            }
        }
    }

    /*public static void createAdmin(){
        Admin admin = new Admin();
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Your username : ");
        String username = scan.next();
        System.out.println("Your Password : ");
        String password = scan.next();
        try {
            String passEncrpt = Biblioman.encryptedPassword(password);
            admin.setUsername(username);
            admin.setPassword(passEncrpt);
            Admin.adminList.add(admin);
            Admin.loadAdmin();
            Admin.saveAdmin();
            System.out.println("Admin registered !");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }*/
    public static void adminAuthenticationLogin() {
        Admin.loadAdmin();
        System.out.println("WELCOME TO ADMIN LOGIN PAGE :");
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter your username : ");
        String username = scan.next();
        System.out.print("Enter your Password : ");
        String password = scan.next();
        Admin adminUsername = Admin.findAdminByUsername(username);
        Admin adminPassword = Admin.findAdminByPassword(password);
        if (adminUsername != null && adminPassword == null) {
            System.out.println("Password not found !");
            principalMenu();
        } else if (adminUsername == null && adminPassword != null) {
            System.out.println("Username not found");
            principalMenu();
        } else if (adminUsername == null && adminPassword == null) {
            System.out.println("Username and Password not found !");
            principalMenu();
        } else {
            System.out.println("You are welcome !");
            menuCrudBiblioman();
        }
    }

    public static void bibliomanAuthenticationLogin() {
        /*List<Biblioman> biblioman = Biblioman.readBibliomanInFile();
        if (biblioman.size() == 0) {
            System.out.println("There is not biblioman at the file!");
        } else {*/
            Biblioman.loadBiblioman();
            System.out.println("WELCOME TO BIBLIOMAN LOGIN PAGE :");
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Enter your username : ");
            String username = scan.next();
            System.out.print("Enter your Password : ");
            String password = scan.next();
            Biblioman adminUsername = Biblioman.findBibliomanByUsername(username);
            Biblioman adminPassword = Biblioman.findBibliomanByPassword(password);
            if (adminUsername != null && adminPassword == null) {
                System.out.println("Password not found !");
                principalMenu();
            } else if (adminUsername == null && adminPassword != null) {
                System.out.println("Username not found");
                principalMenu();
            }
            else if (adminUsername == null && adminPassword == null) {
                System.out.println("Username and Password not found !");
                principalMenu();
            }
            else {
                System.out.println("You are welcome !");
                menuBib();
            }
        //}
    }

    public static void menuBib() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Crud Book");
            System.out.println("2. Crud Student");
            System.out.println("3. Crud Professor");
            System.out.println("4. View Borrow Book");
            System.out.println("5. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    menuCrudBook();
                    break;
                case 2:
                    menuCrudStudent();
                    break;
                case 3:
                    menuCrudProfessor();
                    break;
                case 4:
                    menuReadBorrowAndStory();
                    break;
                case 5:
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void professorAuthenticationLogin() {
        /*List<Students> students = Students.readStudentInFile();
        if (students.size() == 0) {
            System.out.println("There is not professor at the file!");
        } else {*/
            Professor.loadProfessors();
            System.out.println("WELCOME TO PROFESSOR LOGIN PAGE :");
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Enter your username : ");
            String username = scan.next();
            System.out.print("Enter your Password : ");
            String password = scan.next();
            Professor adminUsername = Professor.findProfessorByUsername(username);
            Professor adminPassword = Professor.findProfessorByPassword(password);
            if (adminUsername != null && adminPassword == null)
                System.out.println("Password not found !");
            else if (adminUsername == null && adminPassword != null)
                System.out.println("Username not found");
            else if (adminUsername == null && adminPassword == null)
                System.out.println("Username and Password not found !");
            else {
                System.out.println("You are welcome !");
                menuUser();
            }
        //}
    }

    public static void studentAuthenticationLogin() {
        /*List<Students> students = Students.readStudentInFile();
        if (students.size() == 0) {
            System.out.println("There is not students at the file!");
        } else {*/
            Students.loadStudents();
            System.out.println("WELCOME TO STUDENT LOGIN PAGE :");
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Enter your username : ");
            String username = scan.next();
            System.out.print("Enter your Password : ");
            String password = scan.next();
            Students adminUsername = Students.findStudentByUsername(username);
            Students adminPassword = Students.findStudentByPassword(password);
            if (adminUsername != null && adminPassword == null)
                System.out.println("Password not found !");
            else if (adminUsername == null && adminPassword != null)
                System.out.println("Username not found");
            else if (adminUsername == null && adminPassword == null)
                System.out.println("Username and Password not found !");
            else {
                System.out.println("You are welcome !");
                menuUser();
            }
        //}
    }

    public static void menuCrudBook() {
        /*List<Book> book = Book.readBookInFile();
        if (book.size() == 0) {
            System.out.println("There is not book at the file!");
        } else {
            Book.loadBooks();
        }*/
        Book.loadBooks();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all book");
            System.out.println("2. Add a new book");
            System.out.println("3. Update a book");
            System.out.println("4. Delete a book");
            System.out.println("5. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Book.readAllBooks();
                    menuBib();
                    break;
                case 2:
                    Book.createBook();
                    menuBib();
                    break;
                case 3:
                    Book.updateBook();
                    menuBib();
                    break;
                case 4:
                    Book.deleteBook();
                    menuBib();
                    break;
                case 5:
                    Book.saveBooks();
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void menuCrudBiblioman() {
        /*List<Biblioman> biblioman = Biblioman.readBibliomanInFile();
        if (biblioman.size() == 0) {
            System.out.println("There is not biblioman at the file!");
        } else {
            Book.loadBooks();
        }*/
        Book.loadBooks();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all biblioman");
            System.out.println("2. Add a new biblioman");
            System.out.println("3. Update a biblioman");
            System.out.println("4. Delete a biblioman");
            System.out.println("5. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Biblioman.readAllBiblioman();
                    break;
                case 2:
                    Biblioman.createBiblioman();
                    break;
                case 3:
                    Biblioman.updateBiblioman();
                    break;
                case 4:
                    Biblioman.deleteBiblioman();
                    break;
                case 5:
                    Biblioman.saveBibliomans();
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void menuCrudStudent() {
        /*List<Students> students = Students.readStudentInFile();
        if (students.size() == 0) {
            System.out.println("There is not students at the file!");
        } else {
            Students.loadStudents();
        }*/
        Students.loadStudents();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all student");
            System.out.println("2. Add a new student");
            System.out.println("3. Update a student");
            System.out.println("4. Delete a student");
            System.out.println("5. Go to Back");
            System.out.println("6. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Students.readAllStudent();
                    break;
                case 2:
                    Students.createStudent();
                    break;
                case 3:
                    Students.updateStudent();
                    break;
                case 4:
                    Students.deleteStudent();
                    break;
                case 5:
                    menuBib();
                    break;
                case 6:
                    Students.saveStudents();
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void menuCrudProfessor() {
        /*List<Professor> professors = Professor.readProfessorInFile();
        if (professors.size() == 0) {
            System.out.println("There is not professors at the file!");
        } else {
            Professor.loadProfessors();
        }*/
        Professor.loadProfessors();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all professor");
            System.out.println("2. Add a new professor");
            System.out.println("3. Update a professor");
            System.out.println("4. Delete a professor");
            System.out.println("5. Go to Back");
            System.out.println("6. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Professor.readAllProfessor();
                    menuBib();
                    break;
                case 2:
                    Professor.createProfessor();
                    menuBib();
                    break;
                case 3:
                    Professor.updateProfessor();
                    menuBib();
                    break;
                case 4:
                    Professor.deleteStudent();
                    menuBib();
                    break;
                case 5:
                    menuBib();
                    break;
                case 6:
                    Professor.saveProfessors();
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void menuReadBorrowAndStory() {
        /*List<Borrow> borrows = Borrow.readBorrowInFile();
        if (borrows.size() == 0) {
            System.out.println("There is not borrow book at the file!");
        } else {
            Borrow.loadBorrow();
        }
        List<Story> stories = Story.readStoryInFile();
        if (stories.size() == 0) {
            System.out.println("There is not story book at the file!");
        } else {
            Story.loadStory();
        }*/
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all borrow");
            System.out.println("2. Display borrow story");
            System.out.println("3. Display borrow specific story");
            System.out.println("5. Go to Back");
            System.out.println("6. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Borrow.readAllBorrow();
                    menuBib();
                    break;
                case 2:
                    Story.readAllBorrowStory();
                    menuBib();
                    break;
                case 3:
                    Story.readAllBorrowStorySpecific();
                    menuBib();
                    break;
                case 5:
                    menuBib();
                    break;
                case 6:
                    Students.saveStudents();
                    System.out.println("Logout ...");
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void menuBorrowBook() {
        /*List<Borrow> borrows = Borrow.readBorrowInFile();
        if (borrows.size() == 0) {
            System.out.println("There is not borrow book at the file!");
        } else {
            Borrow.loadBorrow();
        }
        List<Story> stories = Story.readStoryInFile();
        if (stories.size() == 0) {
            System.out.println("There is not story book at the file!");
        } else {
            Story.loadStory();
        }*/

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display my borrow");
            System.out.println("2. Display my borrow story");
            System.out.println("3. Borrow book");
            System.out.println("4. Return book");
            System.out.println("5. Go to back");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Borrow.readAllBorrow();
                    break;
                case 2:
                    Story.readAllBorrowStorySpecific();
                    break;
                case 3:
                    Borrow.createBorrow();
                    break;
                case 4:
                    Borrow.returnBorrow();
                    break;
                case 5:
                    menuUser();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    public static void menuUserSearchBook() {
        /*List<Book> book = Book.readBookInFile();
        if (book.size() == 0) {
            System.out.println("There is not book at the file!");
        } else {
            Book.loadBooks();
        }*/
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Display all book");
            System.out.println("2. Display book by category");
            System.out.println("3. Display book by athem");
            System.out.println("4. Go to back");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Book.readAllBooks();
                    break;
                case 2:
                    Book.readAllBooksByCategory();
                    break;
                case 3:
                    Book.readAllBooksByAthem();
                    break;
                case 4:
                    menuUser();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    public static void menuUser() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Book menu");
            System.out.println("2. Borrow menu");
            System.out.println("3. Logout");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    menuUserSearchBook();
                    break;
                case 2:
                    menuBorrowBook();
                    break;
                case 3:
                    principalMenu();
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}