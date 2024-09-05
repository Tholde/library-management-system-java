import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students {

    //attributes
    private int id;
    private String fullname;
    private String phone;
    private String address;
    private String department;
    private LocalDate birthday;
    private String email;
    private String username;
    private String password;
    private LocalDateTime addDate;
    private LocalDateTime updateDate;
    public static final String studentFileName = "student.txt";
    public static final String delimiter = ";";
    public static List<Students> studentList = new ArrayList<>();

    //getters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    /****************DEFAULT VALUE******************/
    public String toString() {
        return "ID : " + id + "\nFullname" + fullname +
                "\nTelephone : " + phone +"\nEmail : " + email + "\nAddress : " + address +
                "\nBirthday : " + birthday + "\nDepartement : " + department +
                "\nAdd Date : " + addDate.toString()
                + "\nUpdate Date : " + updateDate.toString();
    }
    public static String getStudentAndShow(int id){
        int num = findStudentById(id);
        if (num != 2 )
            return "ID : " + studentList.get(num).id + "\nFullname : " + studentList.get(num).fullname
                    +"\nDepartment : " + studentList.get(num).department;
        return null;
    }

    /*****for File txt******/
    public static List<Students> readStudentInFile() {
        List<Students> listStudents = new ArrayList<>();
        Students student = new Students();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(studentFileName))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] fields = ligne.split(delimiter);
                student.setId(Integer.parseInt(fields[0]));
                student.setFullname(fields[1]);
                student.setPhone(fields[2]);
                student.setAddress(fields[3]);
                student.setDepartment(fields[4]);
                student.setBirthday(LocalDate.parse(fields[5]));
                student.setEmail(fields[6]);
                student.setUsername(fields[7]);
                student.setPassword(fields[8]);
                student.setAddDate(LocalDateTime.parse(fields[9]));
                student.setUpdateDate(LocalDateTime.parse(fields[10]));

                listStudents.add(student);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listStudents; //retourner la list
    }

    public static void loadStudents() {
        Students student = new Students();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                student.setId(Integer.parseInt(fields[0]));
                student.setFullname(fields[1]);
                student.setPhone(fields[2]);
                student.setAddress(fields[3]);
                student.setDepartment(fields[4]);
                student.setBirthday(LocalDate.parse(fields[5]));
                student.setEmail(fields[6]);
                student.setUsername(fields[7]);
                student.setPassword(fields[8]);
                student.setAddDate(LocalDateTime.parse(fields[9]));
                student.setUpdateDate(LocalDateTime.parse(fields[10]));

                studentList.add(student);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveStudents() {
        File originalFile = new File(studentFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(studentFileName, true)) {
                for (Students student : studentList) {
                    writer.append(student.getId() + delimiter);
                    writer.append(student.getFullname() + delimiter);
                    writer.append(student.getPhone() + delimiter);
                    writer.append(student.getAddress() + delimiter);
                    writer.append(student.getDepartment() + delimiter);
                    writer.append(student.getBirthday() + delimiter);
                    writer.append(student.getEmail() + delimiter);
                    writer.append(student.getUsername() + delimiter);
                    writer.append(student.getPassword() + delimiter);
                    writer.append(student.getAddDate() + delimiter);
                    writer.append(student.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(studentFileName, true)) {
                for (Students student : studentList) {
                    writer.append(student.getId() + delimiter);
                    writer.append(student.getFullname() + delimiter);
                    writer.append(student.getPhone() + delimiter);
                    writer.append(student.getAddress() + delimiter);
                    writer.append(student.getDepartment() + delimiter);
                    writer.append(student.getBirthday() + delimiter);
                    writer.append(student.getEmail() + delimiter);
                    writer.append(student.getUsername() + delimiter);
                    writer.append(student.getPassword() + delimiter);
                    writer.append(student.getAddDate() + delimiter);
                    writer.append(student.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }

    /*******CRUD********/
    public static void createStudent() {
        Students student = new Students();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter Student ID : ");
        int id = scanner.nextInt();
        int j = findStudentById(id);
        if (j == 2) {
            student.setId(id);
            System.out.print("Enter Student Fullname : ");
            String fullname = scanner.next();
            student.setFullname(fullname);
            System.out.print("Enter Student Phone : ");
            String phone = scanner.next();
            student.setPhone(phone);
            System.out.print("Enter Student Address : ");
            String address = scanner.next();
            student.setAddress(address);
            System.out.print("Enter Student Birthday and follow the format (year-mounth-day) : ");
            String date = scanner.next();
            student.setBirthday(LocalDate.parse(date));
            System.out.print("Enter Student Email : ");
            String email = scanner.next();
            student.setEmail(email);
            System.out.print("Enter Student Username : ");
            String username = scanner.next();
            student.setUsername(username);
            System.out.print("Enter Student Password : ");
            String password = scanner.next();
            /*System.out.print("Confirm this Password : ");
            String ConfirmPassword = scanner.next();
            if (password == ConfirmPassword) {*/
                try {
                    student.setPassword(Biblioman.encryptedPassword(password));
                    student.setAddDate(LocalDateTime.now());
                    student.setUpdateDate(LocalDateTime.now());
                    System.out.print("Enter Student Department \n1.Theology\n2.Busness\n3.Informatic" +
                            "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                            "\nvotre choix :");
                    int choix = scanner.nextInt();
                    switch (choix) {
                        case 1:
                            student.setDepartment("Theology");
                            studentList.add(student);
                            break;
                        case 2:
                            student.setDepartment("Busness");
                            studentList.add(student);
                            break;
                        case 3:
                            student.setDepartment("Informatic");
                            studentList.add(student);
                            break;
                        case 4:
                            student.setDepartment("Nurssing");
                            studentList.add(student);
                            break;
                        case 5:
                            student.setDepartment("Education");
                            studentList.add(student);
                            break;
                        case 6:
                            student.setDepartment("Communication");
                            studentList.add(student);
                            break;
                        case 7:
                            student.setDepartment("EnglishLanguage");
                            studentList.add(student);
                            break;
                    }
                    System.out.println("A Student " + id + " is register successfully !");
                    saveStudents();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            /*}else {
                System.out.println("Password not found !");
            }*/
        } else {
            System.out.print("This was already exist in list !");
        }
    }
    public static void readAllStudent() {
        List<Students> list = readStudentInFile();
        if (list.size() == 0) {
            System.out.println("It have not a Student in file txt!");
        } else {
            System.out.println("----------------------------------------------");
            for (Students student : list) {
                System.out.println(student.toString());
            }
            System.out.println("----------------------------------------------");
        }
    }
    public static void updateStudent() {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter any ID of the student to update: ");
        int id = scan.nextInt();
        int number = findStudentById(id);
        if (number == 2) {
            System.out.println("Student not found!");
        } else {
            System.out.print("Enter Student Fullname : ");
            String fullname = scan.next();
            studentList.get(number).setFullname(fullname);
            System.out.print("Enter Student Phone : ");
            String phone = scan.next();
            studentList.get(number).setPhone(phone);
            System.out.print("Enter Student Address : ");
            String address = scan.next();
            studentList.get(number).setAddress(address);
            System.out.print("Enter Student Birthday and follow the format (year-mounth-day) : ");
            String date = scan.next();
            LocalDate daty = LocalDate.parse(date);
            studentList.get(number).setBirthday(daty);
            studentList.get(number).setUpdateDate(LocalDateTime.now());
            System.out.print("Enter new Student Department \n1.Theology\n2.Busness\n3.Informatic" +
                    "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                    "\nvotre choix :");
            int choix = scan.nextInt();
            String category;
            switch (choix) {
                case 1:
                    category = "Theology";
                    studentList.get(number).setDepartment(category);
                    break;
                case 2:
                    category = "Busness";
                    studentList.get(number).setDepartment(category);
                    break;
                case 3:
                    category = "Informatic";
                    studentList.get(number).setDepartment(category);
                    break;
                case 4:
                    category = "Nurssing";
                    studentList.get(number).setDepartment(category);
                    break;
                case 5:
                    category = "Education";
                    studentList.get(number).setDepartment(category);
                    break;
                case 6:
                    category = "Communication";
                    studentList.get(number).setDepartment(category);
                    break;
                case 7:
                    category = "EnglishLanguage";
                    studentList.get(number).setDepartment(category);
                    break;
            }
            System.out.println("Student " + id + " updated successfully");
            saveStudents();
        }
    }
    public static void deleteStudent() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                if (studentList.size() == 0) {
                    System.out.println("It have not a student in file txt!");
                } else {
                    System.out.println("Delete any Book : ");
                    System.out.print("Enter id for a student do you delete : ");
                    int id = scan.nextInt();
                    int j = findStudentById(id);
                    if (j == 2) {
                        System.out.println("This student do not exist !");
                        break;
                    } else {
                        studentList.remove(j);
                        System.out.println("\nA student " + studentList.get(j).getId() + " deleted");
                        saveStudents();
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*******SEARCH DATA********/
    public static int findStudentById(int id) {
        List<Students> list = readStudentInFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id)
                return i;
        }
        return 2;
    }
    public static Students findStudentByUsername(String searchKey) {
        for (Students students : studentList) {
            if (students.getUsername().toLowerCase().equals(searchKey.toLowerCase())) {
                return students;
            }
        }
        return null;
    }
    public static Students findStudentByPassword(String searchKey){
        try {
            String password = Biblioman.encryptedPassword(searchKey);
            for (Students students : studentList) {
                if (students.getPassword().equals(password)) {
                    return students;
                }
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
