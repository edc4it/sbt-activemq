package com.edc4it.sbt.activemq

import sbt.Keys._
import sbt._
import sbt.complete.Parsers._


object SbtActiveMQ extends AutoPlugin {

  object autoImport {

    val amqBrokerURI = settingKey[String]("brokerURI")
    val br = settingKey[Broker]("brokerURI")
    val amqStart = inputKey[Unit]("start the broker")

    val amqStop = inputKey[Unit]("stop the broker")
    val amqList = inputKey[Unit]("List registered brokers")
  }

  import com.edc4it.sbt.activemq.SbtActiveMQ.autoImport._

  var registeredBrokers: List[Broker] = List()
  
  var defaultBroker: Option[Broker] = None


  private val argsParser = (Space ~> StringBasic).*


  override def projectSettings: Seq[Setting[_]] = Seq(
    startBrokerSettings,
    stopBrokerSettings,
    listBrokerSettings,
    amqBrokerURI := "tcp://localhost:61616"
  )

  def listBrokerSettings: Setting[_] = amqList := {
    val log = streams.value.log

    def printBrokerStatus(b: Broker,idx: String="") {
      println(s"idx: $idx\nuri: ${b.uri}\nstarted: ${b.started}")
    }
    defaultBroker.foreach(b=>printBrokerStatus(b))
    
    registeredBrokers.zipWithIndex.foreach{
      case(b,idx) => printBrokerStatus(b,idx.toString);
    }
      
      
  }

  def startBrokerSettings: Setting[_] = amqStart := {
    val log = streams.value.log
    val args = argsParser.parsed

    args match {
      case uri :: Nil => registeredBrokers +:= new Broker(uri)
      case Nil =>
        val uri = amqBrokerURI.value
        defaultBroker match {
          case Some(broker) if broker.uri == uri =>
            broker.start()
          case Some(prevBroker) if prevBroker.uri != uri =>
            if (prevBroker.started) {
              log.error("Broker with different uri is still running")
            } else
              defaultBroker = Some(new Broker(uri, true))
          case None => defaultBroker = Some(new Broker(uri, true))
        }
      case _ => log.info("Syntax amqStart <brokerURI>")
      

    }
  }

  val digit = """\d+""".r

  def stopBrokerSettings: Setting[_] = amqStop := {
    val log = streams.value.log
    val args = argsParser.parsed
    args match {
      case head :: Nil => registeredBrokers.lift(head.toInt) match {
        case Some(b) => log.info("Stopping Broker")
          b.stop()
        case None => log.error(s"No broker registered for $args")
      }
      case Nil => defaultBroker.map(b => b.stop())
      case _ => log.info("Syntax amqStop <idx>")
    }


  }

}





