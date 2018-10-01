package org.taymyr.lagom.elasticsearch

import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic
import pl.allegro.tech.embeddedelasticsearch.PopularProperties
import java.net.URI
import java.util.concurrent.TimeUnit

open class AbstractEmbeddedElastic : TestListener {

    private lateinit var clientFactory: LagomClientFactory
    private lateinit var embeddedElastic: EmbeddedElastic
    lateinit var elasticSearch: ElasticSearch

    override fun beforeSpec(description: Description, spec: Spec) {
        clientFactory = LagomClientFactory.create(
            "elastic-search",
            LagomClientFactory::class.java.classLoader
        )
        elasticSearch = clientFactory.createClient(ElasticSearch::class.java, URI("http://localhost:9250"))
        embeddedElastic = EmbeddedElastic.builder()
            .withElasticVersion("6.4.1")
            .withSetting(PopularProperties.CLUSTER_NAME, "my_cluster")
            .withSetting(PopularProperties.HTTP_PORT, "9250")
            .withEsJavaOpts("-Xms128m -Xmx512m")
            .withStartTimeout(5, TimeUnit.MINUTES)
            .build()
            .start()
    }

    override fun afterSpec(description: Description, spec: Spec) {
        clientFactory.close()
        embeddedElastic.stop()
    }
}
