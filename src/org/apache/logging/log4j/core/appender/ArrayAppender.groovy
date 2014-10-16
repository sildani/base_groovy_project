package org.apache.logging.log4j.core.appender

import java.io.Serializable
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent

class ArrayAppender extends AbstractAppender {

  def messages = []

  def clearMessages() {
    messages = []
  }

  def received(message) {
    messages?.findAll { receivedMessage -> receivedMessage == message }.size() > 0
  }

  // what follows is the implementation of the abstract functions of
  // AbstractAppender - that's why it looks more lke Java than Groovy

  String name
  Filter filter
  Layout layout
  boolean ignoreException
  boolean initialized

  public ArrayAppender(String name, Filter filter, Layout<Serializable> layout) {
    super(name,filter,layout)
    this.name = name
    this.filter = filter
    this.layout = layout
    this.ignoreException = false
  }

  public ArrayAppender(String name, Filter filter, Layout<Serializable> layout, boolean ignoreException) {
    super(name,filter,layout,ignoreException)
    this.name = name
    this.filter = filter
    this.layout = layout
    this.ignoreException = ignoreException
  }

  public void append(LogEvent event) {
    messages << "${event.level} - ${event.message.formattedMessage}"
  }

  public String getName() {
    this.name
  }

  public Filter getFilter() {
    this.filter
  }

  public Layout getLayout() {
    this.layout
  }

}