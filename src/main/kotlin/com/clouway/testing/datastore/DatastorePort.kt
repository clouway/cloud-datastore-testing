package com.clouway.testing.datastore

import com.google.cloud.datastore.Datastore

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
data class DatastorePort(val datastore: Datastore, val host: String, val port: Int)