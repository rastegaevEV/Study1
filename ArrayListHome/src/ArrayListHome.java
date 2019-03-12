import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome\\file.txt"))) {

            ArrayList<Integer> numbers = new ArrayList<Integer>();

            while (scanner.hasNext()) {
                numbers.add(scanner.nextInt());
            }
            System.out.println(numbers);
        }
    }
}
