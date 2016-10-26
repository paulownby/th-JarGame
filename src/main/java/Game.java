public class Game {

  public static void main(String[] args) throws java.io.IOException {
    String mTypeOfItems;
    int mMaxFit;
    Jar jar;
    int guessStatus = 1;
    int guess = -1;
    int tries = 0;
    String exception = "";
    Prompter prompter = new Prompter();
    
    jar = prompter.setupGame();
    jar.fillJar();

    while (guessStatus != 0) {
      prompter.promptGuess(jar, guessStatus, exception);
      try {
        guess = prompter.getGuess();
        try {      
          guessStatus = jar.makeGuess(guess);
          tries++;
        } catch (IllegalArgumentException iae){
          guessStatus = -1;
          exception = iae.getMessage();
        }
      } catch (IllegalArgumentException iae) {
        guessStatus = -1;
        exception = iae.getMessage();
      }
    }
    prompter.showCongratulations(jar, tries, guess);
  }
}