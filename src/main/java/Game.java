public class Game {

  
  public static void main(String[] args) {
    String mTypeOfItems;
    int mMaxFit;
    Jar jar;

    Prompter prompter = new Prompter();
    
    jar = prompter.setupGame();
    try {
      jar.fillJar();
    } catch (IllegalArgumentException iae) {
      System.out.printf("\n%s. Exiting game...\n\n");
      System.exit(0);
    }
    prompter.play(jar);
    


    
  }
}