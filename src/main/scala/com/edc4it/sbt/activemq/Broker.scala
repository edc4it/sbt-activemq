
/*
 *
 *  * Copyright 2011-2013 EDC4IT Ltd.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  
 */

package com.edc4it.sbt.activemq

import java.nio.file.FileSystems

import com.edc4it.sbt.activemq.ContextClassloaderHelper.withRootClassloaderContext
import org.apache.activemq.broker.{BrokerFactory, BrokerService}


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
