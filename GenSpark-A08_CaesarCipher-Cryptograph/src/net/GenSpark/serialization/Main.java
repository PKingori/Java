/****************************************************************************************************************************
 *          Project: GenSpark- Java Bootcamp Training Program
 *          Project #: A03_CaesarCipher-Cryptograph
 *          Abstract: A simple Java program that demonstrates abilities to use Java serialization to  handle
 *                   I/O Files.
 *          Task: To write a program that accepts a user's statement and "marshalls" (serializes) it into an encrypted format
 *                The program should inversely, "unmarshall" (deserialize) file into a decrypted format
 *          Behaviour: The program uses the serialization and file formatter.
 *          Programmer: Patrick Kingori.
 *
 ***************************************************************************************************************************/
package net.GenSpark.serialization;
import net.GenSpark.serialization.Cipher.Cyphering;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){


           while (true){
               try {
                   Scanner scanner = new Scanner(System.in);
                   printHeader("\n********************** WELCOME TO THE CIPHER-DECIPHER CRYTOGRAPH ******************", "\nPlease press:\n 1 to ENCRYPT your statement\n 2 to DECRYPT your statement");
                   int choice = scanner.nextInt();
                   scanner.nextLine();

                   selectCryptoAction(scanner, choice);

               } catch (InputMismatchException | UnknownEntry e ){
                   e.printStackTrace();
               }
           }
        }

    private static void printHeader(String s, String s2) {
        System.out.println(s);
        System.out.println(s2);
    }

    private static void selectCryptoAction(Scanner scanner, int choice) throws UnknownEntry {
        if (choice == 1) {
            doCipher(scanner);

        } else if (choice == 2) {
            doDecipher(scanner);

        } else if (choice == 3) {
            System.exit(0);
        } else throw new UnknownEntry("Unknown entry");
    }

    private static void doDecipher(Scanner scanner) {
        System.out.print("Enter your message: ");
        String inputText = scanner.nextLine();
        System.out.print("Enter a key number (1-52): ");
        int key = scanner.nextInt();
        decipher decrypting = new decipher(key, inputText);

        printHeader("------------------------", String.format("Your translated text is:\n%s", decrypting.decode()));
        System.out.println("------------------------");


    }

    private static void doCipher(Scanner scanner) {
        System.out.print("Enter your statement: ");
        String inputText = scanner.nextLine();
        System.out.print("Enter a cryptographic key (1-52): ");
        int key = scanner.nextInt();
        var encrypting = new Cyphering(inputText, key);

        printHeader("\n------------------------", String.format("Your translated text is:\n%s", encrypting.encode()));
        System.out.println("------------------------");
    }

}


