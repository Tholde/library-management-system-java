import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    public static final String adminFileName = "admin.txt";
    public static final String delimiter = ";";
    public static List<Admin> adminList = new ArrayList<>();

    //getters and setters

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
    /*****for File txt******/
    public static List<Admin> readAdminInFile() {
        List<Admin> listAdmin = new ArrayList<>();
        Admin admin = new Admin();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(adminFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                admin.setUsername(fields[0]);
                admin.setPassword(fields[1]);

                listAdmin.add(admin);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listAdmin; //retourner la list
    }

    public static void loadAdmin() {
        Admin admin = new Admin();
        try (BufferedReader reader = new BufferedReader(new FileReader(adminFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                admin.setUsername(fields[0]);
                admin.setPassword(fields[1]);

                adminList.add(admin);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveAdmin() {
        File originalFile = new File(adminFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(adminFileName, true)) {
                for (Admin admin : adminList) {
                    writer.append(admin.getUsername() + delimiter);
                    writer.append(admin.getPassword() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(adminFileName, true)) {
                for (Admin admin : adminList) {
                    writer.append(admin.getUsername() + delimiter);
                    writer.append(admin.getPassword() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }
    /************METHODS*****************/
    public static void updateAdmin(){
        System.out.println("Enter your username : ");
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String user = scanner.next();
        Admin admin = findAdminByUsername(user);
        if (admin != null){

        }
    }
    public static Admin findAdminByUsername(String searchKey) {
        for (Admin admin : adminList) {
            if (admin.getUsername().toLowerCase().equals(searchKey.toLowerCase())) {
                return admin;
            }
        }
        return null;
    }
    public static Admin findAdminByPassword(String searchKey){
        try {
            String password = Biblioman.encryptedPassword(searchKey);
            for (Admin admin : adminList) {
                if (admin.getPassword().equals(password)) {
                    return admin;
                }
            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
