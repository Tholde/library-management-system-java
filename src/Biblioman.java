import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioman {
    //attributes
    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String username;
    private String password;
    private LocalDate birthday;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    public static final String bibliomanFileName = "biblioman.txt";
    public static final String delimiter = ";";
    public static List<Biblioman> bibliomanList = new ArrayList<>();

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
    //methods
    public String toString() {
        return "ID : " + id + "\nFullname : " + name + "\nPhone number : "
                + phone + "\nAddress : " + address + "\nBirthday : " + birthday +
                "\nCreated Date : " + createdDate.toString()
                + "\nUpdated Date : " + updatedDate.toString();
    }
    /*****for File txt******/
    public static List<Biblioman> readBibliomanInFile() {
        List<Biblioman> listBiblioman = new ArrayList<>();
        Biblioman biblioman = new Biblioman();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(bibliomanFileName))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] fields = ligne.split(delimiter);
                biblioman.setId(Integer.parseInt(fields[0]));
                biblioman.setName(fields[1]);
                biblioman.setPhone(fields[2]);
                biblioman.setAddress(fields[3]);
                biblioman.setEmail(fields[4]);
                biblioman.setUsername(fields[5]);
                biblioman.setPassword(fields[6]);
                biblioman.setBirthday(LocalDate.parse(fields[9]));
                biblioman.setCreatedDate(LocalDate.parse(fields[10]));
                biblioman.setUpdatedDate(LocalDate.parse(fields[11]));

                listBiblioman.add(biblioman);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listBiblioman; //retourner la list
    }

    public static void loadBiblioman() {
        Biblioman biblioman = new Biblioman();
        try (BufferedReader reader = new BufferedReader(new FileReader(bibliomanFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                biblioman.setId(Integer.parseInt(fields[0]));
                biblioman.setName(fields[1]);
                biblioman.setPhone(fields[2]);
                biblioman.setAddress(fields[3]);
                biblioman.setEmail(fields[4]);
                biblioman.setUsername(fields[5]);
                biblioman.setPassword(fields[6]);
                biblioman.setBirthday(LocalDate.parse(fields[7]));
                biblioman.setCreatedDate(LocalDate.parse(fields[8]));
                biblioman.setUpdatedDate(LocalDate.parse(fields[9]));

                bibliomanList.add(biblioman);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }
    public static void saveBibliomans() {
        File originalFile = new File(bibliomanFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(bibliomanFileName, true)) {
                for (Biblioman biblioman : bibliomanList) {
                    writer.append(biblioman.getId() + delimiter);
                    writer.append(biblioman.getName() + delimiter);
                    writer.append(biblioman.getPhone() + delimiter);
                    writer.append(biblioman.getAddress() + delimiter);
                    writer.append(biblioman.getEmail() + delimiter);
                    writer.append(biblioman.getUsername() + delimiter);
                    writer.append(biblioman.getPassword() + delimiter);
                    writer.append(biblioman.getBirthday() + delimiter);
                    writer.append(biblioman.getCreatedDate() + delimiter);
                    writer.append(biblioman.getUpdatedDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(bibliomanFileName, true)) {
                for (Biblioman biblioman : bibliomanList) {
                    writer.append(biblioman.getId() + delimiter);
                    writer.append(biblioman.getName()+ delimiter);
                    writer.append(biblioman.getPhone() + delimiter);
                    writer.append(biblioman.getAddress() + delimiter);
                    writer.append(biblioman.getEmail() + delimiter);
                    writer.append(biblioman.getUsername() + delimiter);
                    writer.append(biblioman.getPassword() + delimiter);
                    writer.append(biblioman.getBirthday() + delimiter);
                    writer.append(biblioman.getCreatedDate() + delimiter);
                    writer.append(biblioman.getUpdatedDate() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }

    /*******CRUD********/
    public static void createBiblioman() {
        Biblioman biblioman = new Biblioman();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter Biblioman ID : ");
        int id = scanner.nextInt();
        int j = findBibliomanById(id);
        if (j == 2) {
            biblioman.setId(id);
            System.out.print("Enter Biblioman Fullname : ");
            String fullname = scanner.next();
            biblioman.setName(fullname);
            System.out.print("Enter Biblioman Phone : ");
            String phone = scanner.next();
            biblioman.setPhone(phone);
            System.out.print("Enter Biblioman Address : ");
            String address = scanner.next();
            biblioman.setAddress(address);
            System.out.print("Enter Biblioman Birthday and follow the format (year-mounth-day) : ");
            String date = scanner.next();
            LocalDate daty = LocalDate.parse(date);
            biblioman.setBirthday(daty);
            System.out.print("Enter Biblioman Email : ");
            String email = scanner.next();
            biblioman.setEmail(email);
            System.out.print("Enter Biblioman Username : ");
            String username = scanner.next();
            biblioman.setUsername(username);
            System.out.print("Enter Biblioman Password : ");
            String password = scanner.next();
            System.out.print("Confirm this Password : ");
            String ConfirmPassword = scanner.next();
            if (password.equals(ConfirmPassword)) {
                try {
                    String passcrypter = encryptedPassword(password);
                    biblioman.setPassword(passcrypter);
                    biblioman.setCreatedDate(LocalDate.now());
                    biblioman.setUpdatedDate(LocalDate.now());
                    System.out.println("Biblioman " + id + " is register successfully !");
                    bibliomanList.add(biblioman);
                    saveBibliomans();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("Password not found !");
            }
        } else {
            System.out.print("This was already exist in list !");
        }
    }
    public static void readAllBiblioman() {
        if (bibliomanList.size() == 0) {
            System.out.println("It have not a biblioman in file txt!");
        } else {
            System.out.println("----------------------------------------------");
            for (Biblioman biblioman : bibliomanList) {
                System.out.println(biblioman.toString());
            }
            System.out.println("----------------------------------------------");
        }
    }
    public static void updateBiblioman() {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter any ID of a biblioman to update: ");
        int id = scan.nextInt();
        int number = findBibliomanById(id);
        if (number == 2) {
            System.out.println("Biblioman not found!");
        } else {
            System.out.print("Enter Student Fullname : ");
            String name = scan.next();
            bibliomanList.get(number).setName(name);
            System.out.print("Enter Student Phone : ");
            String phone = scan.next();
            bibliomanList.get(number).setPhone(phone);
            System.out.print("Enter Student Address : ");
            String address = scan.next();
            bibliomanList.get(number).setAddress(address);
            System.out.print("Enter Student Birthday and follow the format (year-mounth-day) : ");
            String date = scan.next();
            bibliomanList.get(number).setBirthday(LocalDate.parse(date));
            bibliomanList.get(number).setUpdatedDate(LocalDate.now());

            System.out.println("Biblioman " + id + " updated successfully");
            saveBibliomans();
        }
    }
    public static void deleteBiblioman() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            try {
                if (bibliomanList.size() == 0) {
                    System.out.println("It have not a Biblioman in file txt!");
                } else {
                    System.out.println("Delete any Biblioman : ");
                    System.out.print("Enter id for a biblioman do you delete : ");
                    int id = scan.nextInt();
                    int j = findBibliomanById(id);
                    if (j == 2) {
                        System.out.println("This biblioman do not exist !");
                        break;
                    } else {
                        bibliomanList.remove(j);
                        System.out.println("\nA biblioman " + bibliomanList.get(j).getId() + " deleted");
                        saveBibliomans();
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*******SEARCH DATA********/
    private static int findBibliomanById(int id) {
        for (int i = 0; i < bibliomanList.size(); i++) {
            if (bibliomanList.get(i).getId() == id)
                return i;
        }
        return 2;
    }
    public static Biblioman findBibliomanByUsername(String searchKey) {
        for (Biblioman biblioman : bibliomanList) {
            if (biblioman.getUsername().toLowerCase().equals(searchKey.toLowerCase())) {
                return biblioman;
            }
        }
        return null;
    }
    public static Biblioman findBibliomanByPassword(String searchKey){
        try {
            String password = Biblioman.encryptedPassword(searchKey);
            for (Biblioman biblioman : bibliomanList) {
                if (biblioman.getPassword().equals(password)) {
                    return biblioman;
                }
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    /***************ENCRYPTED PASSWORD**********************/
    public static String encryptedPassword(String password) throws NoSuchAlgorithmException {
        //the SHA-256 hashing algorithm to encrypt the password entered by the user
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte[] byteData = md.digest();

        StringBuffer sb = new StringBuffer();
        //this object use for stored and displayed the result
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
