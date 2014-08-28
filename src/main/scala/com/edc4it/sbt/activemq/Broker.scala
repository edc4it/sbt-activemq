package com.edc4it.sbt.activemq

import com.edc4it.sbt.activemq.ContextClassloaderHelper.withRootClassloaderContext
import org.apache.activemq.broker.BrokerFactory

/**
 * todo
 */
class Broker(val uri: String, val startBroker : Boolean = true) {

  var broker = withRootClassloaderContext {
    BrokerFactory.createBroker(uri, startBroker)
  }


  def start() : Unit = {
    broker.start()
  }
  
  def stop(): Unit = {
    broker.stop()
  }
  
  def started : Boolean = broker.isStarted

}
