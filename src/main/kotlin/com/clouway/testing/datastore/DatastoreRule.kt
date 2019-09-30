package com.clouway.testing.datastore

import com.google.cloud.datastore.Datastore
import org.junit.rules.ExternalResource

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
open class DatastoreRule : ExternalResource() {

  private lateinit var datastorePort: DatastorePort
  private lateinit var runner: EmulatorRunner

  override fun before() {
    val ci = System.getenv("CI")
    if (ci == "true") {
      runner = CIEmulatorRunner()
    } else {
      runner = DockerEmulatorRunner()
    }

    datastorePort = runner.start()
  }

  override fun after() {
    runner.stop()
  }

  val datastore: Datastore
    get() = datastorePort.datastore

  val port: DatastorePort
    get() = datastorePort
}