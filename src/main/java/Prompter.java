import java.io.Console;
  
public class Prompter {
  private Console mConsole = System.console();

  public Jar setupGame() {
    String typeOfItems = "";
    int maxFit = 0;
    
    System.out.print("\033[H\033[2J");
    System.out.println("\n\n");
    System.out.println("    Game Setup\n    ==========\n");
    while (typeOfItems.length() == 0) {  
      typeOfItems = mConsole.readLine("What type of item?  ");
      if (typeOfItems.length() == 0 ) {
        System.out.println("\nYou have to put something in the jar.\n");
      }
    }
    while (maxFit < 1) {
      try {
        maxFit = Integer.parseInt(mConsole.readLine("What is the maximum amount of " 
                                                    + typeOfItems 
                                                    + "?  "));
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
  
  public void play(Jar jar) {
    int guessStatus = -1;
    int guess = -1;
    int tries = 0;
    System.out.printf("How many %s are in the jar? Pick a number between 1 and %d:  ", 
                      jar.getTypeOfItems(),
                      jar.getMaxFit());
    while (guessStatus != 0) {
      try {
        guess = Integer.parseInt(mConsole.readLine());
        try {      
          guessStatus = jar.makeGuess(guess);
          tries++;
          if (guessStatus > 0) {
            System.out.printf("Your guess is too high. Please try again:  ");
          } else if (guessStatus < 0) {
            System.out.printf("Your guess is too low. Please try again:  ");
          }
        } catch (IllegalArgumentException iae){
        System.out.printf("%s. Please try again:  ", iae.getMessage());
      }
      } catch (IllegalArgumentException iae) {
        System.out.printf("Your guess is not an integer. Please try again:  ");
      }
    }
    if (tries == 1) {
      System.out.printf("\n\nWOW! There are %d %s in the jar. You got it in 1 attempt! Lucky guess.\n\n",
                        guess, jar.getTypeOfItems());
    } else {
      System.out.printf("\n\nCongratulations! There are %d %s in the jar. You got it in %d attempts.\n\n",
                        guess, jar.getTypeOfItems(), tries);
    }
  }
 
}