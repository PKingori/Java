package net.GenSpark.serialization;
import java.io.FileWriter;
import java.io.IOException;


public class Cipher {
    public static class Cyphering {
        private int cryptyKey;
        private String testIn;

        public Cyphering(String inputText, int key) {
            cryptyKey = key;
            testIn = inputText;
        }
        public String stringInput() {
            return testIn;
        }
        public int getKey()
        {
            return cryptyKey;
        }
        public boolean isValidInputText()
        {
            char[] chars = testIn.toCharArray();

            return true;
        }

        public boolean isNotEmpty()
        {
            boolean b = !testIn.trim().isEmpty();
            return b;
        }
        public boolean isValidKey()
        {
            boolean isCryptic = cryptyKey > 1 && cryptyKey < 54;
            return isCryptic;

        }

        public String encode()
        {
            String ciphered = "";
            String encodedArray[] = testIn.split("");
            String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,";

            for (int i = 0; i<encodedArray.length;i++)
            {
                if (testIn.charAt(i) == ' ')
                {
                    ciphered += " ";
                }
                else
                {
                    int positionChar = alphabet.indexOf(testIn.charAt(i));
                    int key = (cryptyKey + positionChar) % 54;
                    ciphered += alphabet.charAt(key);
                }
            }

            try {

                FileWriter cipherText = new FileWriter("text.txt");
                cipherText.write(ciphered);
                cipherText.close();
                System.out.println("\nSuccessfully wrote to the file.\n");

            } catch (IOException e) {
                System.out.println("An error occurred while writing the file");

            }

            return ciphered;
        }
    }




}
