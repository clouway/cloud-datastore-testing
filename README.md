## Cloud Datastore Testing Package 
A testing package of cloud-datastore.

### Adding as dependency

In Gradle:
```groovy

repositories {
  mavenCentral()
}

dependencies {
     compile 'com.clouway.testing:cloud-datastore-testing:0.0.1'
}
```

In Maven:

```xml

 <dependency>
    <groupId>com.clouway.testing</groupId>
    <artifactId>cloud-datastore-testing</artifactId>
    <version>0.0.1</version>
 </dependency>

```

### Usage
 * Unit Testing with JUnit 4.x
```kotlin
  @Rule
  @JvmField
  val context = JUnitRuleMockery()

  companion object {
    @ClassRule
    @JvmField
    val datastoreRule = DatastoreRule()
  }

  @Rule
  @JvmField
  var cleaner = DatastoreCleaner { datastoreRule.port }

  // ....
   
  @Test
  fun happyPath() {
    val datastore = datastoreRuel.datastore
    // do datastore persistent logic
  }
   
```

 * Jenkins CI Usage   
 As cloud-datastore-testing library is using docker for running of the emulator it is required the following environment
 variables to be passed from the CI:
  
 ```bash
   export DATASTORE_HOST=xxxx
   export DATASTORE_PORT=5000
 ```  
 
 Here is a complete Jenkins example:
 ```groovy  
 docker.image('google/cloud-sdk:latest').withRun('--entrypoint gcloud', 'beta emulators datastore start --no-legacy --project testing --host-port=0.0.0.0:8888 --consistency=1 --no-store-on-disk') { c ->
    docker.image('yourimage').inside("-e CI=true -e DATASTORE_HOST=datastore -e DATASTORE_PORT=8888 --link ${c.id}:datastore") {
        // perform your test execution here  
    }
 }
```
 
 

### Contributing
If you would like to contribute code to pcache client you can do so through GitHub by forking the repository and sending
a pull request. When submitting code, please make every effort to follow existing conventions and style in order to
keep the code as readable as possible. Please also make sure your code compiles by running gradle clean build.
