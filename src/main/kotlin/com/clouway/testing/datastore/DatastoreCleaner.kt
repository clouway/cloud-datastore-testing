package com.clouway.testing.datastore

import com.google.common.io.CharStreams
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author Miroslav Genov (miroslav.genov@clouway.com)
 */
class DatastoreCleaner(val datastorePort: () -> DatastorePort) : TestRule {
  override fun apply(base: Statement, description: Description): Statement {
    return object: Statement() {
      override fun evaluate() {
        val endpoint = datastorePort()
        sendPostRequest(endpoint, "/reset")
        base.evaluate()
      }
    }
  }

  @Throws(IOException::class)
    protected fun sendPostRequest(endpoint: DatastorePort, request: String): String {
      val url = URL("http", endpoint.host, endpoint.port, request)
      val con = url.openConnection() as HttpURLConnection
      con.requestMethod = "POST"
      con.doOutput = true
      val out = con.outputStream
      out.write("".toByteArray())
      out.flush()

      val `in` = con.inputStream
      val response = CharStreams.toString(InputStreamReader(con.inputStream))
      `in`.close()
      return response
    }

}