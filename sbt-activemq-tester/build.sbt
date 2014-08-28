val root = (project in file(".")).enablePlugins(SbtActiveMQ)


amqBrokers += ("sample" -> "xbean:file:src/main/etc/activemq-broker.xml")

