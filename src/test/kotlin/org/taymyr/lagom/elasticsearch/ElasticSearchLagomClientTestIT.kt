package org.taymyr.lagom.elasticsearch

import akka.Done.done
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.whenReady
import org.taymyr.lagom.elasticsearch.dsl.search.QueryRoot
import java.util.concurrent.ExecutionException

class ElasticSearchLagomClientTestIT : StringSpec() {

    override fun listeners(): List<TestListener> = listOf(ElasticSearchLagomClientTestIT.Companion)

    init {
        "stage2 use elasticSearch" {
            shouldThrow<ExecutionException> {
                elasticSearch.search("qwe", "qwe").invoke(QueryRoot(null, null, null, null)).toCompletableFuture().get()
            }
        }
        "stage3 auto initialize settings" {
            // Will be load and apply settings from: elasticsearch/settings.json
            whenReady(elasticSearchAdministration.initializeSettings().toCompletableFuture()) {
                it shouldBe done()
            }
        }
    }

    companion object : AbstractEmbeddedElastic() {

        lateinit var elasticSearchAdministration: ElasticSearchAdministration
        lateinit var elasticRepositoryC1: ElasticRepositoryC1

        override fun beforeSpec(description: Description, spec: Spec) {
            super.beforeSpec(description, spec)
            elasticSearchAdministration = ElasticSearchAdministration(elasticSearch, "c1", "all")
            elasticRepositoryC1 = ElasticRepositoryC1(elasticSearch)
        }
    }
}
