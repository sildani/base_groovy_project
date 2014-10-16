package org.apache.logging.log4j.core.appender

import groovy.mock.interceptor.MockFor
import org.apache.logging.log4j.core.impl.Log4jLogEvent
import org.junit.Test

public class ArrayAppenderTest {

  @Test
  public void should_store_messages_in_local_array() {
    def appender = new ArrayAppender('name',null,null)
    def mockEvent = new MockFor(Log4jLogEvent)
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Hello!'] }
    mockEvent.use {
      def event = new Log4jLogEvent()
      appender.append(event)
      assert appender.messages[0] == 'INFO - Hello!'
    }
  }

  @Test
  public void should_store_messages_in_order() {
    def appender = new ArrayAppender('name',null,null)
    def mockEvent = new MockFor(Log4jLogEvent)
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'First hello!'] }
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Second hello!'] }
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Third hello!'] }
    mockEvent.use {
      appender.append(new Log4jLogEvent())
      appender.append(new Log4jLogEvent())
      appender.append(new Log4jLogEvent())
      assert appender.messages.size() == 3
      assert appender.messages[0] == 'INFO - First hello!'
      assert appender.messages[1] == 'INFO - Second hello!'
      assert appender.messages[2] == 'INFO - Third hello!'
    }
  }

  @Test
  public void should_report_if_message_has_been_logged() {
    def appender = new ArrayAppender('name',null,null)
    def mockEvent = new MockFor(Log4jLogEvent)
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Hello!'] }
    mockEvent.use {
      def event = new Log4jLogEvent()
      appender.append(event)
      assert appender.received('INFO - Hello!')
      assert !appender.received('La La La La')
    }
  }

  @Test
  public void should_clear_messages() {
    def appender = new ArrayAppender('name',null,null)
    def mockEvent = new MockFor(Log4jLogEvent)
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'First hello!'] }
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Second hello!'] }
    mockEvent.demand.getLevel { 'INFO' }
    mockEvent.demand.getMessage { [formattedMessage: 'Third hello!'] }
    mockEvent.use {
      appender.append(new Log4jLogEvent())
      appender.append(new Log4jLogEvent())
      appender.append(new Log4jLogEvent())
      assert appender.messages.size() == 3
      appender.clearMessages()
      assert appender.messages.size() == 0
    }
  }

}