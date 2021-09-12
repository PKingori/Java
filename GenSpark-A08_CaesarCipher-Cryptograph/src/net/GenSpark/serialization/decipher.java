package net.GenSpark.serialization;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class decipher {

    String data;

    public String decodedValue;
    private int mKey;
    private String mInputText;

    public decipher(int key, String inputText) {
        mKey = key;
        mInputText = inputText;

        try {

            FileWriter encodedText = new FileWriter("text.txt");
            encodedText.write(mInputText);
            encodedText.close();
            System.out.println("Successfully wrote to the file.\n");

        } catch (IOException e) {
            System.out.println("An error occurred.");

        }

        try {
            File myText = new File("text.txt");
            Scanner myReader = new Scanner(myText);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println("Reading the file to decrypt...\n" + "\"" + data + "\"" + " was read from the file.");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred. File not found.");
            e.printStackTrace();
        }


    }


    public String getInputText() {
        return data;
    }
    public int getKey() {
        return mKey;
    }
    public boolean isValidInputText()


    {
        char[] chars = data.toCharArray();

        // old code
        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }


        return true;
    }


    public boolean isValidKey()
    {
        return mKey >1 && mKey < 54;
    }

    public String decode()
    {

        try {
            File myText = new File("text.txt");
            Scanner myReader = new Scanner(myText);

            String data = myReader.nextLine();

            System.out.println("\"" + data + "\"" + " is being decrypted...\n");

            decodedValue = "";
            String encodedArray[] = data.split("");
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,";


            // old code
            for (int i = 0; i<encodedArray.length;i++)
            {
                if (data.charAt(i) == ' ')
                {
                    decodedValue += " ";
                }
                else
                {
                    int charPosition = alphabet.indexOf(data.charAt(i));
                    int keyVal = (charPosition - mKey) % 54;

                    if (keyVal < 0)
                    {
                        keyVal = alphabet.length() + keyVal;
                    }

                    char replaceValue = alphabet.charAt(keyVal);
                    decodedValue += replaceValue;
                }
            }

            return decodedValue;


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }
}