# SBT ActimeMQ

Simple auto plugin to run activemq from sbt

## Features

These are the features of this small plugin:

- Register multiple broker URIs
- Start / Stop / Restart brokers
- List registered URI


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
log4j:WARN No appenders could be found for logger (org.apache.activemq.broker.jmx.ManagementContext).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
[success] Total time: 1 s, ...

> amqStatus

default STARTED broker:tcp://localhost:61616
sample  STOPPED broker:tcp://localhost:17171

[success] Total time: 0 s,
```

(Fixing the log4j is next on the agenda :)

- Stop a broker

```bash
amqStop default
[success] Total time: 0 s,

```

### Notes

Some important notes (some will need to be 

- The broker starts a random port for the JMX use `netstat` to see which one
- The data directory is set to `activemq/data/<name>`

### License

SBT ActiveMQ  is Apache 2.0 licensed.


