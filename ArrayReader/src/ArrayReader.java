import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ArrayReader {
    ArrayList<String> readFile = new ArrayList<>();
    String fileName;
    static final String RED = "(255, 0, 0)";
    static final String GREEN = "(0, 255, 0)";
    static final String BLUE = "(0, 0, 255)";
    static final String WHITE = "(255, 255, 255)";
    static final String BLACK = "(0, 0, 0)";


    public ArrayReader(String file) throws FileNotFoundException {
        fileName = file;
        try {
            File inputFile = new File(file);
            Scanner scanner = new Scanner(inputFile);
            String input = scanner.nextLine();
            int count = 1;
            while(scanner.hasNextLine()){
                String[] individual = input.split(" ");
                for (int i = 0; i < individual.length; i++){
                    switch (individual[i]) {
                        case "R" -> {
                            readFile.add(RED);
                            System.out.println(count + ": " + "Input Red");
                            count++;
                        }
                        case "G" -> {
                            readFile.add(GREEN);
                            System.out.println(count + ": " + "Input Green");
                            count++;
                        }
                        case "B" -> {
                            readFile.add(BLUE);
                            System.out.println(count + ": " + "Input Blue");
                            count++;
                        }
                        case "W" -> {
                            readFile.add(WHITE);
                            System.out.println(count + ": " + "Input White");
                            count++;
                        }
                        case "0" -> {
                            readFile.add(BLACK);
                            System.out.println(count + ": " + "Input Black");
                            count++;
                        }
                    }
                }
                input = scanner.nextLine();

            }
            String[] individual = input.split(" ");
            for (int i = 0; i < individual.length; i++){
                switch (individual[i]) {
                    case "R" -> {
                        readFile.add(RED);
                        System.out.println(count + ": " + "Input Red");
                        count++;
                    }
                    case "G" -> {
                        readFile.add(GREEN);
                        System.out.println(count + ": " + "Input Green");
                        count++;
                    }
                    case "B" -> {
                        readFile.add(BLUE);
                        System.out.println(count + ": " + "Input Blue");
                        count++;
                    }
                    case "W" -> {
                        readFile.add(WHITE);
                        System.out.println(count + ": " + "Input White");
                        count++;
                    }
                    case "0" -> {
                        readFile.add(BLACK);
                        System.out.println(count + ": " + "Input Black");
                        count++;
                    }
                }
            }

        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }

    }


    public void Print(ArrayList<String> array){
        int count = 1;
        for (String string : array){
            System.out.println(count + ": " + string);
            count++;
        }
    }
    public void Save(ArrayList<String> array) throws IOException {
        try {
            File newFile = new File(this.fileName.substring(0, this.fileName.length() - 4) + "FINAL.txt");
            if (newFile.createNewFile()){
                System.out.println("File Created: " + newFile.getName());
            }
            else {
                System.out.println("File Already Exists");
            }
            FileWriter writer = new FileWriter(this.fileName.substring(0, this.fileName.length() - 4)+ "FINAL.txt");

            for (String string : array) {
                writer.write(string + " ");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An Error Occurred");
            e.printStackTrace();
        }
    }

}
