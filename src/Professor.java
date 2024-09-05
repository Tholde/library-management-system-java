import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Professor {

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
    public static final String professorFileName = "professor.txt";
    public static final String delimiter = ";";
    public static List<Professor> professorList = new ArrayList<>();

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
    /***************DEFAULT VALUE*******************/
    public String toString() {
        return "ID : " + id + "\nFullname" + fullname +
                "\nTelephone : " + phone + "\nAddress : " + address +
                "\nBirthday : " + birthday + "\nDepartement : " + department +
                "\nAdd Date : " + addDate.toString()
                + "\nUpdate Date : " + updateDate.toString();
    }
    public static String getProfessorAndShow(int id){
        int num = findProfessorById(id);
        if (num != 2 )
            return "ID : " + professorList.get(num).id + "\nFullname : " + professorList.get(num).fullname
                    + "\nDepartment : " + professorList.get(num).department;
        return null;
    }

    /*****for File txt******/
    public static List<Professor> readProfessorInFile() {
        List<Professor> listProfessor = new ArrayList<>();
        Professor professor = new Professor();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(professorFileName))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] fields = ligne.split(delimiter);
                professor.setId(Integer.parseInt(fields[0]));
                professor.setFullname(fields[1]);
                professor.setPhone(fields[2]);
                professor.setAddress(fields[3]);
                professor.setDepartment(fields[4]);
                professor.setBirthday(LocalDate.parse(fields[5]));
                professor.setEmail(fields[6]);
                professor.setUsername(fields[7]);
                professor.setPassword(fields[8]);
                professor.setAddDate(LocalDateTime.parse(fields[9]));
                professor.setUpdateDate(LocalDateTime.parse(fields[10]));

                listProfessor.add(professor);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listProfessor; //retourner la list
    }

    public static void loadProfessors() {
        Professor professor = new Professor();
        try (BufferedReader reader = new BufferedReader(new FileReader(professorFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                professor.setId(Integer.parseInt(fields[0]));
                professor.setFullname(fields[1]);
                professor.setPhone(fields[2]);
                professor.setAddress(fields[3]);
                professor.setDepartment(fields[4]);
                professor.setBirthday(LocalDate.parse(fields[5]));
                professor.setEmail(fields[6]);
                professor.setUsername(fields[7]);
                professor.setPassword(fields[8]);
                professor.setAddDate(LocalDateTime.parse(fields[9]));
                professor.setUpdateDate(LocalDateTime.parse(fields[10]));

                professorList.add(professor);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }
    public static void saveProfessors() {
        File originalFile = new File(professorFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(professorFileName, true)) {
                for (Professor professor : professorList) {
                    writer.append(professor.getId() + delimiter);
                    writer.append(professor.getFullname() + delimiter);
                    writer.append(professor.getPhone() + delimiter);
                    writer.append(professor.getAddress() + delimiter);
                    writer.append(professor.getDepartment() + delimiter);
                    writer.append(professor.getBirthday() + delimiter);
                    writer.append(professor.getEmail() + delimiter);
                    writer.append(professor.getUsername() + delimiter);
                    writer.append(professor.getPassword() + delimiter);
                    writer.append(professor.getAddDate() + delimiter);
                    writer.append(professor.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(professorFileName, true)) {
                for (Professor professor : professorList) {
                    writer.append(professor.getId() + delimiter);
                    writer.append(professor.getFullname() + delimiter);
                    writer.append(professor.getPhone() + delimiter);
                    writer.append(professor.getAddress() + delimiter);
                    writer.append(professor.getDepartment() + delimiter);
                    writer.append(professor.getBirthday() + delimiter);
                    writer.append(professor.getEmail() + delimiter);
                    writer.append(professor.getUsername() + delimiter);
                    writer.append(professor.getPassword() + delimiter);
                    writer.append(professor.getAddDate() + delimiter);
                    writer.append(professor.getUpdateDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }

    /*******CRUD********/
    public static void createProfessor() {
        Professor professor = new Professor();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter Professor ID : ");
        int id = scanner.nextInt();
        int j = findProfessorById(id);
        if (j == 2) {
            professor.setId(id);
            System.out.print("Enter professor Fullname : ");
            String fullname = scanner.next();
            professor.setFullname(fullname);
            System.out.print("Enter professor Phone : ");
            String phone = scanner.next();
            professor.setPhone(phone);
            System.out.print("Enter professor Address : ");
            String address = scanner.next();
            professor.setAddress(address);
            System.out.print("Enter professor Birthday and follow the format (year-mounth-day) : ");
            String date = scanner.next();
            LocalDate daty = LocalDate.parse(date);
            professor.setBirthday(daty);
            System.out.print("Enter professor Email : ");
            String email = scanner.next();
            professor.setEmail(email);
            System.out.print("Enter professor Username : ");
            String username = scanner.next();
            professor.setUsername(username);
            System.out.print("Enter professor Password : ");
            String password = scanner.next();
            /*System.out.print("Confirm this Password : ");
            String ConfirmPassword = scanner.next();
            if (password == ConfirmPassword) {*/
                try {
                    professor.setPassword(Biblioman.encryptedPassword(password));
                    professor.setAddDate(LocalDateTime.now());
                    professor.setUpdateDate(LocalDateTime.now());
                    System.out.print("Enter Professor Department Teaching " +
                            "\n1.Theology\n2.Busness\n3.Informatic" +
                            "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                            "\n8.Visitor\n9.Other\nvotre choix :");
                    int choix = scanner.nextInt();
                    switch (choix) {
                        case 1:
                            professor.setDepartment("Theology");
                            professorList.add(professor);
                            break;
                        case 2:
                            professor.setDepartment("Busness");
                            professorList.add(professor);
                            break;
                        case 3:
                            professor.setDepartment("Informatic");
                            professorList.add(professor);
                            break;
                        case 4:
                            professor.setDepartment("Nurssing");
                            professorList.add(professor);
                            break;
                        case 5:
                            professor.setDepartment("Education");
                            professorList.add(professor);
                            break;
                        case 6:
                            professor.setDepartment("Communication");
                            professorList.add(professor);
                            break;
                        case 7:
                            professor.setDepartment("EnglishLanguage");
                            professorList.add(professor);
                            break;
                        case 8:
                            professor.setDepartment("Visiteur");
                            professorList.add(professor);
                            break;
                        case 9:
                            System.out.print("Enter your funtion : ");
                            String dep = scanner.next();
                            professor.setDepartment(dep);
                            professorList.add(professor);
                    }
                    System.out.println("Professor " + id + " is register successfully !");
                    saveProfessors();
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
    public static void readAllProfessor() {
        List<Professor> list = readProfessorInFile();
        if (list.size() == 0) {
            System.out.println("It have not a professor in file txt!");
        } else {
            System.out.println("----------------------------------------------");
            for (Professor professor : list) {
                System.out.println(professor.toString());
            }
            System.out.println("----------------------------------------------");
        }
    }
    public static void updateProfessor() {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter any ID of a professor to update: ");
        int id = scan.nextInt();
        int number = findProfessorById(id);
        if (number == 2) {
            System.out.println("Professor not found!");
        } else {
            System.out.print("Enter Student Fullname : ");
            String fullname = scan.next();
            professorList.get(number).setFullname(fullname);
            System.out.print("Enter Student Phone : ");
            String phone = scan.next();
            professorList.get(number).setPhone(phone);
            System.out.print("Enter Student Address : ");
            String address = scan.next();
            professorList.get(number).setAddress(address);
            System.out.print("Enter Student Birthday and follow the format (year-mounth-day) : ");
            String date = scan.next();
            professorList.get(number).setBirthday(LocalDate.parse(date));
            professorList.get(number).setUpdateDate(LocalDateTime.now());
            System.out.print("Enter new professor department \n1.Theology\n2.Busness\n3.Informatic" +
                    "\n4.Nurssing\n5.Education\n6.Communication\n7.English Language" +
                    "\n8.Visitor\n9.Other\nvotre choix :");
            int choix = scan.nextInt();
            String category;
            switch (choix) {
                case 1:
                    category = "Theology";
                    professorList.get(number).setDepartment(category);
                    break;
                case 2:
                    category = "Busness";
                    professorList.get(number).setDepartment(category);
                    break;
                case 3:
                    category = "Informatic";
                    professorList.get(number).setDepartment(category);
                    break;
                case 4:
                    category = "Nurssing";
                    professorList.get(number).setDepartment(category);
                    break;
                case 5:
                    category = "Education";
                    professorList.get(number).setDepartment(category);
                    break;
                case 6:
                    category = "Communication";
                    professorList.get(number).setDepartment(category);
                    break;
                case 7:
                    category = "EnglishLanguage";
                    professorList.get(number).setDepartment(category);
                    break;
                case 8:
                    category = "Visitor";
                    professorList.get(number).setDepartment(category);
                    break;
                case 9:
                    System.out.print("Enter your funtion : ");
                    String dep = scan.next();
                    professorList.get(number).setDepartment(dep);
            }
            System.out.println("Professor " + id + " updated successfully");
            saveProfessors();
        }
    }
    public static void deleteStudent() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                if (professorList.size() == 0) {
                    System.out.println("It have not a Professor in file txt!");
                } else {
                    System.out.println("Delete any Professor : ");
                    System.out.print("Enter id for a professor do you delete : ");
                    int id = scan.nextInt();
                    int j = findProfessorById(id);
                    if (j == 2) {
                        System.out.println("This professor do not exist !");
                        break;
                    } else {
                        professorList.remove(j);
                        System.out.println("\nA professor " + professorList.get(j).getId() + " deleted");
                        saveProfessors();
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*******SEARCH DATA********/
    public static int findProfessorById(int id) {
        List<Professor> list = readProfessorInFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id)
                return i;
        }
        return 2;
    }
    public static Professor findProfessorByUsername(String searchKey) {
        for (Professor professor : professorList) {
            if (professor.getUsername().toLowerCase().equals(searchKey.toLowerCase())) {
                return professor;
            }
        }
        return null;
    }
    public static Professor findProfessorByPassword(String searchKey){
        try {
            String password = Biblioman.encryptedPassword(searchKey);
            for (Professor professor : professorList) {
                if (professor.getPassword().equals(password)) {
                    return professor;
                }
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
