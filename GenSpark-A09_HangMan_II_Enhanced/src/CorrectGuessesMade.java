import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CorrectGuessesMade {

    public CorrectGuessesMade(String hangmanCountry) {
        this.unknownCountry = hangmanCountry;
    }

    private String unknownCountry;

    private Set<String> correctGuesses = new HashSet<String>();

    public Stream<String> getCorrectGuesses() {

        return correctGuesses.stream();

    }

    public void anotherGuessedLetter(String cGuess){
        if (unknownCountry.contains(cGuess)) {

            correctGuesses.add(cGuess);

        };

    };

    public boolean wasLetterValid(String letter) {
        return correctGuesses.contains(letter);
    }




}