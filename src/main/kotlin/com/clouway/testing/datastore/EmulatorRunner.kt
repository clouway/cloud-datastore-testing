package com.clouway.testing.datastore

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
internal interface EmulatorRunner {

  fun start(): DatastorePort

  fun stop()

}