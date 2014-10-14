import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

class MainClass {

  private static final Logger logger = LogManager.getLogger(MainClass.class.getName());

  public static void main(String[] args) {
    logger.info("This is the main class.")
  }

}