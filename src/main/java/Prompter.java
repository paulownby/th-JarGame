import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Prompter {
  private BufferedReader mIn = new BufferedReader(new InputStreamReader(System.in));

  public Jar setupGame() throws IOException {
    String typeOfItems = "";
    int maxFit = 0;
        
    System.out.print("\033[H\033[2J");
    System.out.println("\n\n");
    System.out.println("    Game Setup\n    ==========\n");
    while (typeOfItems.length() == 0) {
      System.out.printf("What type of item?  ");
      typeOfItems = mIn.readLine();
      if (typeOfItems.length() == 0 ) {
        System.out.println("\nYou have to put something in the jar.\n");
      }
    }
    while (maxFit < 1) {
      try {
        System.out.printf("What is the maximum amount of "
                          + typeOfItems + "?  ");
        maxFit = Integer.parseInt(mIn.readLine());
      } catch (IllegalArgumentException iae) {
      }
      if (maxFit < 1) {
        System.out.println("\nCome on. Give me a number I can work with.\n");
      }
      
    }
    System.out.print("\033[H\033[2J");
    Jar jar = new Jar(typeOfItems, maxFit);
    System.out.printf("The jar is filled with %s.\n", jar.getTypeOfItems());
    System.out.printf("The jar can hold up to %d %s.\n\n", 
                      jar.getMaxFit(),
                      jar.getTypeOfItems());
    return jar;
  }
  
  public void promptGuess(Jar jar, int guessStatus, String exception) throws IOException {
    // show too low prompt (-2)
    // show exception prompt (-1)
    // show first prompt (1)
    // show too high prompt (2)
    if (guessStatus == -2) {
      System.out.printf("Your guess is too low. Please try again:  ");
    } else if (guessStatus == -1) {
      System.out.printf("%s. Please try again:  ", exception);
    } else if (guessStatus == 1) {
      System.out.printf("How many %s are in the jar? Pick a number between 1 and %d:  ", 
                        jar.getTypeOfItems(),
                        jar.getMaxFit());
    } else if (guessStatus == 2) {
      System.out.printf("Your guess is too high. Please try again:  ");
    }
  }
  
  public int getGuess() throws IOException {
    try {
      return Integer.parseInt(mIn.readLine());
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException("Your guess is not an integer");
    }
  }

  public void showCongratulations(Jar jar, int tries, int guess) {
    if (tries == 1) {
      System.out.printf("\n\nWOW! There are %d %s in the jar. You got it in 1 attempt! Lucky guess.\n\n",
                        guess, jar.getTypeOfItems());
    } else {
      System.out.printf("\n\nCongratulations! There are %d %s in the jar. You got it in %d attempts.\n\n",
                        guess, jar.getTypeOfItems(), tries);
    }
  }
}    
