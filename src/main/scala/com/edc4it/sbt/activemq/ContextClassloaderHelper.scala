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
