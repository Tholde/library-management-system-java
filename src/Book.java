import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book {
    //attributes
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private String athem;
    private LocalDateTime addDate;
    private LocalDateTime updateDate;
    public static final String bookFileName = "book.txt";
    public static final String delimiter = ";";
    public static List<Book> bookList = new ArrayList<>();

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAthem() {
        return athem;
    }

    public void setAthem(String athem) {
        this.athem = athem;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    //methods
    /*******************DEFAULT VALUE**************************/
    public String toString() {
        return "ID : " + id + "\nISBN : " + isbn + "\nTitle : "
                + title + "\nAuthor : " + author + "\nCategory : "
                + category + "\nAthem : " + athem +
                "\nAdd Date : " + addDate.toString()
                + "\nUpdate Date : " + updateDate.toString();
    }
    public static String getBookAndShow(int id){
        int num = findBookBorrowedById(id);
        if (num != 2 )
            return "ID : " + Borrow.bookListBorrowed.get(num).id + "\nISBN : " + Borrow.bookListBorrowed.get(num).isbn + "\nTitle : "
                + Borrow.bookListBorrowed.get(num).title + "\nAuthor : " + Borrow.bookListBorrowed.get(num).author + "\nCategory : "
                + Borrow.bookListBorrowed.get(num).category + "\nAthem : " + Borrow.bookListBorrowed.get(num).athem;
        return null;
    }
    public String getDataBook(){
        return id+delimiter+isbn+delimiter+title+delimiter+author+delimiter+category+delimiter+athem;
    }

    /*****for File txt******/
    public static List<Book> readBookInFile() {
        List<Book> listBook = new ArrayList<>();
        Book book = new Book();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(bookFileName))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] fields = ligne.split(delimiter);
                book.setId(Integer.parseInt(fields[0]));
                book.setIsbn(fields[1]);
                book.setTitle(fields[2]);
                book.setAuthor(fields[3]);
                book.setCategory(fields[4]);
                book.setAthem(fields[5]);
                book.setAddDate(LocalDateTime.parse(fields[6]));
                book.setUpdateDate(LocalDateTime.parse(fields[7]));

                listBook.add(book);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listBook; //retourner la list
    }

    public static void loadBooks() {
        Book book = new Book();
        try (BufferedReader reader = new BufferedReader(new FileReader(bookFileName))) {
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

                bookList.add(book);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveBooks() {
        File originalFile = new File(bookFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(bookFileName, true)) {
                for (Book book : bookList) {
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
            try (FileWriter writer = new FileWriter(bookFileName, true)) {
                for (Book book : bookList) {
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

    /*******CRUD********/
    public static void createBook() {
        Book book = new Book();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter register number : ");
        int id = scanner.nextInt();
        int j = findBookById(id);
        if (j == 2) {
            book.setId(id);
            System.out.print("Enter book ISBN: ");
            String isbn = scanner.next();
            book.setIsbn(isbn);
            System.out.print("Enter book title: ");
            String title = scanner.next();
            book.setTitle(title);
            System.out.print("Enter book author: ");
            String author = scanner.next();
            book.setAuthor(author);
            System.out.print("Enter book athem: ");
            String athem = scanner.next();
            book.setAthem(athem);
            book.setAddDate(LocalDateTime.now());
            book.setUpdateDate(LocalDateTime.now());
            System.out.print("Enter book category \n1.Theology\n2.Busness\n3.Informatic" +
                    "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                    "\n8.Ellen White\n9.Bible\n10.Encyclopedy\nvotre choix :");
            int choix = scanner.nextInt();
            String category;
            switch (choix) {
                case 1:
                    book.setCategory("Theology");
                    bookList.add(book);
                    break;
                case 2:
                    book.setCategory("Busness");
                    bookList.add(book);
                    break;
                case 3:
                    book.setCategory("Informatic");
                    bookList.add(book);
                    break;
                case 4:
                    book.setCategory("Nurssing");
                    bookList.add(book);
                    break;
                case 5:
                    book.setCategory("Education");
                    bookList.add(book);
                    break;
                case 6:
                    book.setCategory("Communication");
                    bookList.add(book);
                    break;
                case 7:
                    book.setCategory("EnglishLanguage");
                    bookList.add(book);
                    break;
                case 8:
                    book.setCategory("EllenWhite");
                    bookList.add(book);
                    break;
                case 9:
                    book.setCategory("Bible");
                    bookList.add(book);
                    break;
                case 10:
                    book.setCategory("Encyclopedy");
                    bookList.add(book);
                    break;
            }
            System.out.println("A book " + isbn + " is register successfully !");
            saveBooks();
        } else {
            System.out.print("This was exist in list !");
        }
    }

    public static void readAllBooks() {
        List<Book> list = readBookInFile();
        if (list.size() == 0) {
            System.out.println("It have not a book in file txt!");
        } else {
            System.out.println("----------------------------------------------");
            for (Book book : list) {
                System.out.println(book.toString());
            }
            System.out.println("----------------------------------------------");
        }
    }
    public static void readAllBooksByCategory() {
        List<Book> list = readBookInFile();
        if (list.size() == 0) {
            System.out.println("It have not a book in file txt!");
        } else {
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            System.out.print("There is book category exist in our library \n" +
                    "1.Theology\n2.Busness\n3.Informatic" +
                    "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                    "\n8.Ellen White\n9.Bible\n10.Encyclopedy\nEnter your choice by the category name : ");
            int cate = scan.nextInt();
            List<Book> listCate = new ArrayList<>();
            switch (cate){
                case 1:
                    listCate = findBookByStringCategory("Theology");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 2:
                    listCate = findBookByStringCategory("Busness");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 3:
                    listCate = findBookByStringCategory("Informatic");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 4:
                    listCate = findBookByStringCategory("Nurssing");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 5:
                    listCate = findBookByStringCategory("Education");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 6:
                    listCate = findBookByStringCategory("Communication");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 7:
                    listCate = findBookByStringCategory("EnglishLanguage");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 8:
                    listCate = findBookByStringCategory("EllenWhite");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 9:
                    listCate = findBookByStringCategory("Bible");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;
                case 10:
                    listCate = findBookByStringCategory("Encyclopedy");
                    if (listCate.size() == 0)
                        System.out.println("It has not a book in list this category");
                    else {
                        System.out.println("----------------------------------------------");
                        for (Book book : listCate) {
                            System.out.println(book.toString());
                        }
                        System.out.println("----------------------------------------------");
                    }
                    break;

            }
        }
    }
    public static void readAllBooksByAthem() {
        List<Book> list = readBookInFile();
        if (list.size() == 0) {
            System.out.println("It have not a book in file txt!");
        } else {
            Scanner scan = new Scanner(System.in).useDelimiter("\n");
            System.out.print("Enter the athem to you search : ");
            String cate = scan.next();
            List<Book> listCate = findBookByStringAthem(cate);
            if (listCate.size() == 0)
                System.out.println("It has not a book in list this category");
            else {
                System.out.println("----------------------------------------------");
                for (Book book : list) {
                    System.out.println(book.toString());
                }
                System.out.println("----------------------------------------------");
            }
        }
    }
    public static void updateBook() {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        /*System.out.print("Enter any ID of the book to update: ");
        int id = scan.nextInt();
        int number = findBookById(id);*/
        System.out.print("Enter any ISBN of the book to update : ");
        String isbn = scan.next();
        int number = findBookByIsbn(isbn);
        if (number == 2) {
            System.out.println("Book not found!");
        } else {
            System.out.print("Enter new book ISBN: ");
            String newIsbn = scan.next();
            bookList.get(number).setIsbn(newIsbn);
            System.out.print("Enter new book title: ");
            String title = scan.next();
            bookList.get(number).setTitle(title);
            System.out.print("Enter new book author: ");
            String author = scan.next();
            bookList.get(number).setAuthor(author);
            bookList.get(number).setUpdateDate(LocalDateTime.now());
            System.out.print("Enter new book category \n1.Theology\n2.Busness\n3.Informatic" +
                    "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                    "\n8.Ellen White\n9.Bible\n10.Encyclopedy\nvotre choix :");
            int choix = scan.nextInt();
            String category;
            switch (choix) {
                case 1:
                    category = "Theology";
                    bookList.get(number).setCategory(category);
                    break;
                case 2:
                    category = "Busness";
                    bookList.get(number).setCategory(category);
                    break;
                case 3:
                    category = "Informatic";
                    bookList.get(number).setCategory(category);
                    break;
                case 4:
                    category = "Nurssing";
                    bookList.get(number).setCategory(category);
                    break;
                case 5:
                    category = "Education";
                    bookList.get(number).setCategory(category);
                    break;
                case 6:
                    category = "Communication";
                    bookList.get(number).setCategory(category);
                    break;
                case 7:
                    category = "EnglishLanguage";
                    bookList.get(number).setCategory(category);
                    break;
                case 8:
                    category = "EllenWhite";
                    bookList.get(number).setCategory(category);
                    break;
                case 9:
                    category = "Bible";
                    bookList.get(number).setCategory(category);
                    break;
                case 10:
                    category = "Encyclopedy";
                    bookList.get(number).setCategory(category);
                    break;
            }
            System.out.println("Book " + isbn + " updated successfully");
            saveBooks();
        }
    }

    public static void deleteBook() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                if (bookList.size() == 0) {
                    System.out.println("It have not a book in file txt!");
                } else {
                    System.out.println("Delete any Book : ");
                    System.out.print("Enter id for a book do you delete : ");
                    int id = scan.nextInt();
                    int j = findBookById(id);
                    if (j == 2) {
                        System.out.println("This book do not exist !");
                        break;
                    } else {
                        bookList.remove(j);
                        System.out.println("\nA book " + bookList.get(j).getIsbn() + " deleted");
                        saveBooks();
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*******SEARCH DATA********/
    //search book by id and return id
    public static int findBookById(int id) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).id == id)
                return i;
        }
        return 2;
    }
    public static int findBookBorrowedById(int id){
        for (int i = 0; i < Borrow.bookListBorrowed.size(); i++) {
            if (Borrow.bookListBorrowed.get(i).id == id)
                return i;
        }
        return 2;
    }
    //search book by string and return id
    public static int findBookByIsbn(String isbn) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getIsbn().toLowerCase().equals(isbn.toLowerCase()))
                return i;
        }
        return 2;
    }
    //search book by id and return book
    public static Book findBookByIdReturnBook(int id) {
        if (bookList.size() == 0) {
            System.out.println("No book exist is file ");
        } else {
            int j = findBookById(id);
            if (j == 2) {
                System.out.println("This book not exist");
            } else {
                return bookList.get(j);
            }
        }
        return null;
    }
    //search book by isbn or title and return book
    public static Book findBookByString(String searchkey) {
        List<Book> list = readBookInFile();
        for (Book book : list) {
            if (book.getTitle().toLowerCase().equals(searchkey.toLowerCase()) || book.getIsbn().toLowerCase().equals(searchkey.toLowerCase())) {
                return book;
            }
        }
        return null;
    }
    //search book by category and return list of the book have this category
    public static List<Book> findBookByStringCategory(String searchkey) {
        List<Book> listByCategory = new ArrayList<>();
        List<Book> list = readBookInFile();
        for (Book book : list) {
            if (book.getCategory().toLowerCase().equals(searchkey.toLowerCase())) {
                listByCategory.add(book);
            }
        }
        return listByCategory;
    }
    //search book by athem and return list of the book have this athem
    public static List<Book> findBookByStringAthem(String searchkey) {
        List<Book> listByCategory = new ArrayList<>();
        List<Book> list = readBookInFile();
        for (Book book : list) {
            if (book.getAthem().toLowerCase().equals(searchkey.toLowerCase())) {
                listByCategory.add(book);
            }
        }
        return listByCategory;
    }
}
