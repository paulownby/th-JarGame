import java.util.Random;

public class Jar {
  private String mTypeOfItem;
  private int mMaxFit;
  private int mNumberOfItems;
  
  public Jar(String typeOfItem, int maxFit) {
    mTypeOfItem = typeOfItem;
    mMaxFit = maxFit;
    
  }
  public String getTypeOfItems() {
    return mTypeOfItem;
  }
  public int getMaxFit() {
    return mMaxFit;
  }
  public void fillJar() {
     // set random number
    if (mMaxFit < 1) {
      throw new IllegalArgumentException("A positive maximum fill is required");
    }
    Random random = new Random();
    mNumberOfItems = random.nextInt(mMaxFit) + 1;
  }
  public int makeGuess(int guess) {
      
    if (guess < 1 ) {
      throw new IllegalArgumentException("Your guess must be at least 1");
    } else if (guess > mMaxFit) {
      throw new IllegalArgumentException("Your guess must be less than " + mMaxFit);
    } 
    
    
    /* return the following:
        -1 guess is too small
        0 guess is correct
        1 guess is too big */
    if (guess == mNumberOfItems) {
      return 0;
    } else if (guess > mNumberOfItems) {
      return 1;
    } else {
      return -1;
    }
  }
}