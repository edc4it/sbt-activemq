package com.edc4it.sbt.activemq

/**
 * todo
 */
object ContextClassloaderHelper {

  def withRootClassloaderContext[T](body: => T): T = {
    val c = Thread.currentThread().getContextClassLoader
    Thread.currentThread().setContextClassLoader(this.getClass.getClassLoader)
    try {
      body
    } finally {
      Thread.currentThread().setContextClassLoader(c)
    }
  }
}
