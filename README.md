# SBT ActiveMQ

Simple auto plugin to run activemq from sbt

## Features

These are the features of this small plugin:

- Register multiple broker URIs
- Start / Stop / Restart brokers
- List registered URI

## Snapshots

A snapshot version is available at 
[https://oss.sonatype.org/content/repositories/snapshots/](https://oss.sonatype.org/content/repositories/snapshots/)


## Configuration

The plugin is written as a 

Add this to your `plugins.sbt' 

```scala
addSbtPlugin("com.edc4it" % "sbt-activemq" % "1.0.0-SNAPSHOT")
```

And this to your `build.sbt' (or similar in your build definition)
```scala
val root = (project in file(".")).enablePlugins(SbtActiveMQ)
 
amqBrokers += ("sample" -> "broker:tcp://localhost:17171")
```

You can add multiple Broker URIs. 

## Using

Once you start the sbt console you can the following

- List the registered Broker URIs:

```bash
> amqStatus

default STOPPED broker:tcp://localhost:61616
sample  STOPPED broker:tcp://localhost:17171

[success] Total time: 0 s, ...
```

- Start a broker (using completion for the names)

```bash
> amqStart default
Apache ActiveMQ 5.10.0 (brokerOne, ID:hp-8570w-43824-1409310328896-0:1) is starting
Apache ActiveMQ 5.10.0 (brokerOne, ID:hp-8570w-43824-1409310328896-0:1) started

[success] Total time: 1 s, ...

> amqStatus

default STARTED broker:tcp://localhost:61616
sample  STOPPED broker:tcp://localhost:17171

[success] Total time: 0 s,
```

- Stop a broker

```bash
> amqStop default
Apache ActiveMQ 5.10.0 (sample, ID:hp-8570w-43824-1409310328896-0:1) is shutting down
Apache ActiveMQ 5.10.0 (sample, ID:hp-8570w-43824-1409310328896-0:1) uptime 1 minute
Apache ActiveMQ 5.10.0 (sample, ID:hp-8570w-43824-1409310328896-0:1) is shutdown 
[success] Total time: 0 s,

```

### Notes

Some important notes (some will need to be 

- The broker starts a random port for the JMX use `netstat` to see which one
- The data directory is set to `activemq/data/<name>`
- The log is written to `activemq/log/brokers.log` (the logs from the BrokerService are presented in the sbt console)

### License

SBT ActiveMQ  is Apache 2.0 licensed.


