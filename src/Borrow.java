import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrow {
    //attribute
    private int idBook;
    private int idStudent;
    private LocalDate retDate;
    private LocalDate borrowDate;
    private int idProfessor;
    public static final String borrowFileName = "borrow.txt"; //stocker-na ny information -ny indrana
    public static final String delimiter = ";";
    public static final String borrowedFileName = "borrowed.txt";//stocker-na izay boky nindramina
    public static List<Borrow> borrowList = new ArrayList<>();

    public static List<Book> bookListBorrowed = new ArrayList<>();

    //getters and setters

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public LocalDate getRetDate() {
        return retDate;
    }

    public void setRetDate(LocalDate retDate) {
        this.retDate = retDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }


    /***************DEFAULT VALUE*****************/
    public String toString() {
        loadBorrowed();
        return "Book borrowed \n" + Book.getBookAndShow(idBook)
                + "\nBorrow Date : " + borrowDate + "\nNormal Return Date : "+ retDate
                + "\nPerson : \n" + Professor.getProfessorAndShow(idProfessor) + "\n" + Students.getStudentAndShow(idStudent);
    }

    /*****for File txt******/
    public static List<Borrow> readBorrowInFile() {
        List<Borrow> listBorrow = new ArrayList<>();
        Borrow borrow = new Borrow();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(borrowFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                borrow.setIdBook(Integer.parseInt(fields[0]));
                borrow.setIdStudent(Integer.parseInt(fields[1]));
                borrow.setIdProfessor(Integer.parseInt(fields[2]));
                borrow.setRetDate(LocalDate.parse(fields[3]));
                borrow.setBorrowDate(LocalDate.parse(fields[4]));

                listBorrow.add(borrow);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listBorrow; //retourner la list
    }

    public static void loadBorrow() {
        Borrow borrow = new Borrow();
        try (BufferedReader reader = new BufferedReader(new FileReader(borrowFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                 borrow.setIdBook(Integer.parseInt(fields[0]));
                 borrow.setIdStudent(Integer.parseInt(fields[1]));
                 borrow.setIdProfessor(Integer.parseInt(fields[2]));
                 borrow.setRetDate(LocalDate.parse(fields[3]));
                 borrow.setBorrowDate(LocalDate.parse(fields[4]));

                borrowList.add(borrow);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveBorrow() {
        File originalFile = new File(borrowFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(borrowFileName, true)) {
                for (Borrow borrow : borrowList) {
                    writer.append(borrow.getIdBook() + delimiter);
                    writer.append(borrow.getIdStudent() + delimiter);
                    writer.append(borrow.getIdProfessor() + delimiter);
                    writer.append(borrow.getRetDate() + delimiter);
                    writer.append(borrow.getBorrowDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(borrowFileName, true)) {
                for (Borrow borrow : borrowList) {
                    writer.append(borrow.getIdBook() + delimiter);
                    writer.append(borrow.getIdStudent() + delimiter);
                    writer.append(borrow.getIdProfessor() + delimiter);
                    writer.append(borrow.getRetDate() + delimiter);
                    writer.append(borrow.getBorrowDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }

    public static List<Book> readBorrowedInFile() {
        List<Book> listBorrow = new ArrayList<>();
        Book book = new Book();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(borrowedFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                book.setId(Integer.parseInt(fields[0]));
                book.setIsbn(fields[1]);
                book.setTitle(fields[2]);
                book.setAuthor(fields[3]);
                book.setCategory(fields[4]);
                book.setAthem(fields[5]);
                book.setAddDate(LocalDateTime.parse(fields[6]));
                book.setUpdateDate(LocalDateTime.parse(fields[7]));

                listBorrow.add(book);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listBorrow; //retourner la list
    }

    public static void loadBorrowed() {
        Book book = new Book();
        try (BufferedReader reader = new BufferedReader(new FileReader(borrowedFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                book.setId(Integer.parseInt(fields[0]));
                book.setIsbn(fields[1]);
                book.setTitle(fields[2]);
                book.setAuthor(fields[3]);
                book.setCategory(fields[4]);
                book.setAthem(fields[5]);
                book.setAddDate(LocalDateTime.parse(fields[6]));
                book.setUpdateDate(LocalDateTime.parse(fields[7]));

                bookListBorrowed.add(book);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveBorrowed() {
        File originalFile = new File(borrowedFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(borrowedFileName, true)) {
                for (Book book : bookListBorrowed) {
                    writer.append(book.getId() + delimiter);
                    writer.append(book.getIsbn() + delimiter);
                    writer.append(book.getTitle() + delimiter);
                    writer.append(book.getAuthor() + delimiter);
                    writer.append(book.getCategory() + delimiter);
                    writer.append(book.getAthem() + delimiter);
                    writer.append(book.getAddDate() + delimiter);
                    writer.append(book.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(borrowedFileName, true)) {
                for (Book book : bookListBorrowed) {
                    writer.append(book.getId() + delimiter);
                    writer.append(book.getIsbn() + delimiter);
                    writer.append(book.getTitle() + delimiter);
                    writer.append(book.getAuthor() + delimiter);
                    writer.append(book.getCategory() + delimiter);
                    writer.append(book.getAthem() + delimiter);
                    writer.append(book.getAddDate() + delimiter);
                    writer.append(book.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }

    /***********CRUD***************/
    public static void createBorrow() {
        Borrow borrow = new Borrow();
        while (true) {
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            try {
                System.out.println("Borrow Book :");
                System.out.print("Enter your id : ");
                int idPers = scan.nextInt();
                int resStud = Students.findStudentById(idPers);
                int resProf = Professor.findProfessorById(idPers);
                if (resProf != 2 || resStud != 2) {
                    /*System.out.println("How many book do you borrow ? (1-3)");
                    int num = scan.nextInt();
                    if (num > 0 && num < 4 && num <= Book.bookList.size()) {
                        for (int i = 1; i < num; i++) {*/
                            System.out.print("Enter isbn or title book :");
                            String search = scan.next();
                            Book book = Book.findBookByString(search);
                            if (book == null) {
                                System.out.println("This book not exist in file !");
                            } else {
                                int numero = book.getId();
                                bookListBorrowed.add(book);
                                System.out.println("Register id for this book is : " + numero);
                                borrow.setIdBook(numero);
                                LocalDate borrowDt = LocalDate.now();
                                borrow.setBorrowDate(borrowDt);
                                if (resProf != 2 && resStud == 2) {
                                    System.out.print("Enter date end of semester follow a format yyyy-mm-dd: ");
                                    String dat = scan.next();
                                    borrow.setRetDate(LocalDate.parse(dat));
                                    borrow.setIdProfessor(Professor.professorList.get(resProf).getId());
                                    borrow.setIdStudent(0);
                                    borrowList.add(borrow);
                                    bookListBorrowed.add(book);
                                    Book.bookList.remove(book);
                                    saveBorrow();
                                    Book.saveBooks();
                                    saveBorrowed();
                                    System.out.println("Borrow Successfully!");
                                } else if (resProf == 2 && resStud != 2) {
                                    BorrowDate brwDt = new BorrowDate(borrowDt, 3);
                                    borrow.setRetDate(brwDt.getReturnDate());
                                    borrow.setIdStudent(Students.studentList.get(resStud).getId());
                                    borrow.setIdProfessor(0);
                                    borrowList.add(borrow);
                                    bookListBorrowed.add(book);
                                    Book.bookList.remove(book);
                                    saveBorrow();
                                    Book.saveBooks();
                                    saveBorrowed();
                                    System.out.println("Borrow Successfully!");
                                } else {
                                    System.out.println("Id do not exist !");
                                }
                            }
                        /*}
                        break;
                    } else
                        System.out.println("Borrow not found !");*/

                    break;
                } else
                    System.out.println("Id not found !");
                break;
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void readAllBorrow() {
        List<Borrow> list = readBorrowInFile();
        if (list.size() == 0) {
            System.out.println("No Borrow at the list !");
        } else {
            System.out.println("Borrow Book : ");
            System.out.println("----------------------------------------");
            for (Borrow brw : list) {
                System.out.println(brw.toString());
                System.out.println("-----------------------------------------");
            }
            System.out.println("\n" + list.size() + " book borrowed");
            System.out.println("----------------------------------------------------");
        }
    }

    public static void readSpecific(int id) {
        List<Borrow> borrowSpecific = listSpecific(id);
        System.out.println("Specific of your borrow List : ");
        System.out.println("---------------------------------");
        for (Borrow brw : borrowSpecific) {
            System.out.println(brw.toString());
            System.out.println("----------------------------------");
        }
    }

    public static void returnBorrow() {
        Story story = new Story();
        if (borrowList.size() == 0) {
            System.out.println("No Borrow at the list !");
        } else {
            while (true) {
                Scanner scan = new Scanner(System.in).useDelimiter("\n");
                try {
                    System.out.println("Return Borrow Book :");
                    System.out.print("Enter your id : ");
                    int idPers = scan.nextInt();
                    int person = findBorrowPersonById(idPers);
                    if (person != 2) {
                        readSpecific(person);
                        System.out.print("Enter isbn or title book :");
                        String search = scan.next();
                        Book book = Book.findBookByString(search);
                        Borrow brw = findBorrowByIdBook(book.getId());
                        if (brw == null) {
                            System.out.println("This book not exist in file !");
                        } else {
                            Book.bookList.add(book);
                            story.setIdBorrow(brw.getIdBook());
                            //LocalDate date = LocalDate.now();
                            System.out.print("Enter return date follow this format yyyy-mm-dd : ");
                            String dateret = scan.next();
                            LocalDate date = LocalDate.parse(dateret);
                            LocalDate retDate = brw.getRetDate();
                            story.setReturnDate(date);
                            story.setIdPerson(person);
                            long resultat = ChronoUnit.DAYS.between(retDate, date);
                            if (resultat > 0) {
                                System.out.println("You are late " + resultat + " day(s)");
                                int pay = (int) (resultat * 500);
                                story.setPay(pay+" Ar");
                                System.out.println("You pay "+pay+" Ar for your late");
                                story.setStatus("late " + resultat + " day(s)");
                                Story.storyList.add(story);
                                borrowList.remove(brw);
                                saveBorrow();
                                Story.saveStory();
                                Book.saveBooks();
                                System.out.println("Borrow Successfully !");
                            } else {
                                System.out.println("Congratulation !");
                                story.setStatus("normal");
                                Story.storyList.add(story);
                                borrowList.remove(brw);
                                saveBorrow();
                                Story.saveStory();
                                Book.saveBooks();
                                System.out.println("Borrow Successfully !");
                            }
                        }
                        break;
                    } else {
                        System.out.println("You do not exist at list!");
                        break;
                    }
                } catch (Exception ex) {
                    System.out.println(" ");
                    System.out.println("=>>>Erreur d'entrée: " + ex.getMessage());
                    System.out.println(" ");
                }
            }
        }
    }

    /*********************SEARCH DATE***********************/
    public static int findBorrowPersonById(int id) {
        List<Borrow> list = readBorrowInFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdProfessor() == id)
                return i;
            if (list.get(i).getIdStudent() == id)
                return i;
        }
        return 2;
    }

    public static Borrow findBorrowByIdBook(int idBook) {
        List<Borrow> list = readBorrowInFile();
        for (Borrow brw : list) {
            if (brw.getIdBook() == idBook) {
                return brw;
            }
        }
        return null;
    }

    public static List<Borrow> listSpecific(int id) {
        List<Borrow> specificList = new ArrayList<>();
        List<Borrow> list = readBorrowInFile();
        for (Borrow brw : list) {
            if (brw.getIdStudent() == id) {
                specificList.add(brw);
            }
            if (brw.getIdProfessor() == id) {
                specificList.add(brw);
            }
        }
        return specificList;
    }

    /*public static Book findBorrowByString(String searchkey) {
        for (Book book : Book.bookList) {
            if (book.getTitle().toLowerCase().equals(searchkey.toLowerCase()) || book.getIsbn().toLowerCase().equals(searchkey.toLowerCase())) {
                return book;
            }
        }
        return null;
    }  */
}