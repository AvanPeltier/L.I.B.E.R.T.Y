import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Printing Inputs: ");
        Scanner scanner = new Scanner(System.in);
        ArrayReader arrayReader = new ArrayReader("C:\\Users\\Avan Peltier\\Documents\\GitHub\\L.I.B.E.R.T.Y\\LightFiles\\" + scanner.nextLine());
        arrayReader.Print(arrayReader.readFile);
        arrayReader.Save(arrayReader.readFile);
        System.out.println("Read complete");
    }
}
