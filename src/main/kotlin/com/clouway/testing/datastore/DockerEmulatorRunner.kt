package com.clouway.testing.datastore

import com.google.cloud.NoCredentials
import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
internal class DockerEmulatorRunner : EmulatorRunner {

  private val projectName = "testing"
  private val emulatorPort = 8888

  private lateinit var datastoreContainer: GoogleCloudContainer

  override fun start(): DatastorePort {
    datastoreContainer =
            GoogleCloudContainer()
                    .withExposedPorts(emulatorPort)
                    .withCommand("/bin/sh", "-c",
            """
              gcloud beta emulators datastore start --project $projectName \
                --host-port=0.0.0.0:$emulatorPort \
                --consistency=1 \
                --no-store-on-disk
            """).waitingFor(LogMessageWaitStrategy().withRegEx("(?s).*running.*$"))

    datastoreContainer.start()
    
    val datastoreService: Datastore by lazy {
      val containerHost = "${datastoreContainer.host}:${datastoreContainer.getMappedPort(emulatorPort)}"

      DatastoreOptions.newBuilder()
              .setProjectId(projectName)
              .setHost(containerHost)
              .setCredentials(NoCredentials.getInstance())
              .build()
              .service
    }

    return DatastorePort(datastoreService, datastoreContainer.containerIpAddress, datastoreContainer.getMappedPort(emulatorPort))
  }


  override fun stop() {
    datastoreContainer.stop()
  }





}