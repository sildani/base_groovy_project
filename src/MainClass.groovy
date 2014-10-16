import org.apache.logging.log4j.LogManager;

class MainClass {

  private static final def logger = LogManager.getLogger(MainClass.class.name);

  public static void main(String[] args) {
    logger.info("This is the main class.")
  }

}