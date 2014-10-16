import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.appender.ArrayAppender
import org.junit.Before
import org.junit.Test

public class MainClassTest {

  def logAppender
  def configFile

  public MainClassTest() {
    def logger = LogManager.getLogger(MainClass.class.name)
    logAppender = new ArrayAppender('TestAppender',null,null)
    logAppender.start()
    logger.addAppender(logAppender)
  }

  @Before
  public void setup() {
    configFile = File.createTempFile('main_class_','.properties')
    logAppender.clearMessages()
  }

  @Test
  public void should_return_true() {
    MainClass.main([] as String[])
    assert logAppender.received('INFO - This is the main class.')
  }

}