package com.clouway.testing.datastore

import com.google.cloud.NoCredentials
import com.google.cloud.datastore.DatastoreOptions

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
internal class CIEmulatorRunner : EmulatorRunner {
  private val projectName = "testing"

  override fun start(): DatastorePort {
    val host = System.getenv("DATASTORE_HOST")
    val port = System.getenv("DATASTORE_PORT")

    val datastore = DatastoreOptions.newBuilder()
                 .setProjectId(projectName)
                 .setHost("http://$host:$port")
                 .setCredentials(NoCredentials.getInstance())
                 .build()
                 .service

    return DatastorePort(datastore, host, port.toInt())
  }

  override fun stop() {
    // CI will shut it down after all tests are executed
  }

}