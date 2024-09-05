import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Story {
    private int idBorrow;
    private String status;

    private LocalDate returnDate;
    private int idPerson;
    private String pay;

    public int getIdBorrow() {
        return idBorrow;
    }

    public void setIdBorrow(int idBorrow) {
        this.idBorrow = idBorrow;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public static final String storyFileName = "story.txt";
    public static final String delimiter = ";";
    public static List<Story> storyList = new ArrayList<>();
    /*************default value****************/
    public String toString(){
        Borrow borrow = Borrow.findBorrowByIdBook(idBorrow);
        return borrow.toString()+"\nReturn Date : "+returnDate+"\nStatus : "+status+"\nPay : "+pay;
    }

    /*****for File txt******/
    public static List<Story> readStoryInFile() {
        List<Story> listStory = new ArrayList<>();
        Story story = new Story();
        //mamorona list histocker-na ilay livres d io list io no ampidirina anaty fichier
        try (Scanner scanner = new Scanner(new File(storyFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(delimiter);
                story.setIdBorrow(Integer.parseInt(fields[0]));
                story.setStatus(fields[1]);
                story.setReturnDate(LocalDate.parse(fields[2]));
                story.setIdPerson(Integer.parseInt(fields[3]));
                story.setPay(fields[4]);

                listStory.add(story);//ampidirina anaty liste izy
            }
        } catch (FileNotFoundException e) {
            // Handle exception
        }
        return listStory; //retourner la list
    }

    public static void loadStory() {
        Story story = new Story();
        try (BufferedReader reader = new BufferedReader(new FileReader(storyFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(delimiter);
                story.setIdBorrow(Integer.parseInt(fields[0]));
                story.setStatus(fields[1]);
                story.setReturnDate(LocalDate.parse(fields[2]));
                story.setIdPerson(Integer.parseInt(fields[3]));
                story.setPay(fields[4]);

                storyList.add(story);
            }
        } catch (IOException e) {
            /*System.out.println("Error reading file!");
            e.printStackTrace();*/
        }
    }

    public static void saveStory() {
        File originalFile = new File(storyFileName);
        if (originalFile.delete()) {
            try (FileWriter writer = new FileWriter(storyFileName, true)) {
                for (Story story : storyList) {
                    writer.append(story.getIdBorrow() + delimiter);
                    writer.append(story.getStatus() + delimiter);
                    writer.append(story.getReturnDate() + delimiter);
                    writer.append(story.getIdPerson() + delimiter);
                    writer.append(story.getPay() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
        if (!originalFile.exists()) {
            try (FileWriter writer = new FileWriter(storyFileName, true)) {
                for (Story story : storyList) {
                    writer.append(story.getIdBorrow() + delimiter);
                    writer.append(story.getStatus() + "\n");
                    writer.append(story.getReturnDate() + delimiter);
                    writer.append(story.getIdPerson() + delimiter);
                    writer.append(story.getPay() + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error writing to file!");
                e.printStackTrace();
            }
        }
    }
    /****************FOR READ STORY*********************/
    public static void readAllBorrowStory() {
        List<Story> list = readStoryInFile();
        if (list.size() == 0) {
            System.out.println("No Borrow Story at the list !");
        } else {
            System.out.println("Borrow Book Story : ");
            System.out.println("****************************************************************************");
            for (Story story : list) {
                System.out.println(story.toString());
                System.out.println("***********************************");
            }
            System.out.println("\n" + list.size() + " book borrowed story");
            System.out.println("****************************************************************************");
        }
    }
    public static void readAllBorrowStorySpecific() {
        List<Story> list = readStoryInFile();
        Scanner scan = new Scanner(System.in);
        if (list.size() == 0) {
            System.out.println("No Borrow Story at the list !");
        } else {
            System.out.println("Enter your id : ");
            int id = scan.nextInt();
            List<Story> specificList = findStoryById(id);
            System.out.println("Borrow Book Specific Story : ");
            System.out.println("****************************************************************************");
            for (Story story : specificList) {
                System.out.println(story.toString());
                System.out.println("***********************************");
            }
            System.out.println("\n" + specificList.size() + " book borrowed story");
            System.out.println("****************************************************************************");
        }
    }
    /******************Search data******************/
    public static int findBorrowPersonById(int id) {
        List<Story> list = readStoryInFile();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdPerson() == id)
                return i;
        }
        return 2;
    }
    public static List<Story> findStoryById(int id){
       List<Story> storySpecific = new ArrayList<>();
        List<Story> list = readStoryInFile();
        for (Story story : list) {
            if (story.getIdPerson() == id) {
                storySpecific.add(story);
            }
        }
        return storySpecific;
    }
}
