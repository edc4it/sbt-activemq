package com.edc4it.sbt.activemq

import java.nio.file.FileSystems

import com.edc4it.sbt.activemq.ContextClassloaderHelper.withRootClassloaderContext
import org.apache.activemq.broker.{BrokerFactory, BrokerService}

/**
 * todo
 */
class Broker(val uri: String, val name : String) {

  private var _broker: Option[BrokerService] = None


  def start(): Unit = {
    if (started)
      sys.error("Broker is still running stop it first")

    val brokerService = withRootClassloaderContext(BrokerFactory.createBroker(uri, false))
    _broker = Some(brokerService)
    val dataDirectory = FileSystems.getDefault.getPath(".activemq-data", name).toFile
    brokerService.setDataDirectoryFile(dataDirectory)
    brokerService.getManagementContext.setConnectorPort(0)
    brokerService .start()
  }

  def stop(): Unit = {
    if (!started)
      sys.error("Broker is not running")
    _broker.foreach(b => b.stop()) // stop a previous broker
    _broker = None
  }

  def started = _broker.exists(b => b.isStarted)

}
