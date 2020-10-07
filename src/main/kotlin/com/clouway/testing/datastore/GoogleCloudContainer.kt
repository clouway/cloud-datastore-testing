package com.clouway.testing.datastore

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class GoogleCloudContainer : GenericContainer<GoogleCloudContainer>(DockerImageName.parse("google/cloud-sdk:latest"))