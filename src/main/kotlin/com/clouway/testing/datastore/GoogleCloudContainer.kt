package com.clouway.testing.datastore

import org.testcontainers.containers.GenericContainer

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class GoogleCloudContainer : GenericContainer<GoogleCloudContainer>("google/cloud-sdk:latest")