import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Logic {
    private final int ADD_PRODUCT = 0;
    private final int EXIT = 1;
    private final int IGNORE_PRODUCT = 0;
    private final int OVERWRITE_PRODUCT = 1;

    private Scanner sc = new Scanner(System.in);
    private Set<Product> products = new HashSet<>();


    void run() {
        int input = -1;
        do {
            printOptions();
            input = readOption();

            if (input == ADD_PRODUCT) {
                addProduct();
            }

        } while (input != EXIT);
        sc.close();
        printSet();
    }

    private int readOption() {
        System.out.println("Choose option:");
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }


    private void addProduct() {
        System.out.println("Insert product name");
        String productName = sc.nextLine();
        System.out.println("Insert product price");
        double price = sc.nextDouble();
        sc.nextLine();

        checkAvailability(productName, price);
    }

    private void checkAvailability(String productName, double price) {
        if (products.contains(new Product(productName, price))) {
            printSecondOptions();
            if (sc.nextInt() == 1) {
                products.remove(new Product(productName, price));
                products.add(new Product(productName, price));
                sc.nextLine();
            }
        } else {
            products.add(new Product(productName, price));
            System.out.println("Product added to database");
        }

    }

    private void printSecondOptions() {
        System.out.println("Product with the same name found, choose action:");
        System.out.println("> Ignore product - " + IGNORE_PRODUCT);
        System.out.println("> Overwrite product - " + OVERWRITE_PRODUCT);
    }

    private void printOptions() {
        System.out.println("Available options:");
        System.out.println("> Add new product - " + ADD_PRODUCT);
        System.out.println("> Exit the programme - " + EXIT);
    }

    private void printSet(){
        if (!products.isEmpty())
            System.out.println(products.toString());
        else
            System.out.println("No products to print");
    }

}
