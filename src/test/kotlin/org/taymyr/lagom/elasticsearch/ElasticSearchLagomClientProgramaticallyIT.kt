package org.taymyr.lagom.elasticsearch

import akka.Done.done
import io.kotlintest.Description
import io.kotlintest.Spec
import io.kotlintest.extensions.TestListener
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.whenReady
import org.taymyr.lagom.elasticsearch.dsl.mapping.ElasticSearchMapping
import org.taymyr.lagom.elasticsearch.dsl.mapping.MappingProperties
import org.taymyr.lagom.elasticsearch.dsl.mapping.MappingProperty
import org.taymyr.lagom.elasticsearch.dsl.mapping.MappingTypes
import org.taymyr.lagom.elasticsearch.dsl.search.QueryRoot
import org.taymyr.lagom.elasticsearch.dsl.settings.Analyzer
import org.taymyr.lagom.elasticsearch.dsl.settings.ElasticSearchIndexSettings
import java.util.concurrent.ExecutionException

class ElasticSearchLagomClientProgramaticallyIT : StringSpec() {

    override fun listeners(): List<TestListener> = listOf(ElasticSearchLagomClientProgramaticallyIT.Companion)

    init {
        "stage2 use elasticSearch" {
            val internalError = shouldThrow<ExecutionException> {
                elasticSearch.search("qwe", "qwe").invoke(QueryRoot(null, null, null, null))
                    .toCompletableFuture().get()
            }
        }

        "stage3 set settings for index 'c1' programatically" {
            val settingsAdministration = ElasticSearchIndexSettings(
                1,
                ElasticSearchIndexSettings.Analysis(
                    filter = mapOf(
                        "autocomplete_filter" to AutocompleteFilter(
                            "edge_ngram",
                            1,
                            20
                        )
                    ),
                    analyzer = mapOf(
                        "autocomplete" to Analyzer(
                            "custom",
                            "standard",
                            listOf(
                                "lowercase",
                                "autocomplete_filter"
                            )
                        )
                    )
                )
            )
            val result = elasticSearchAdministration.createIndexWithSettings(settingsAdministration).toCompletableFuture().get()
            result shouldBe done()
        }

        "stage4 create mapping" {
            val mapping = ElasticSearchMapping(
                mapOf(
                    "id" to MappingProperties.LONG,
                    "name" to MappingProperty(MappingTypes.TEXT, "autocomplete"),
                    "title" to MappingProperties.OBJECT,
                    "techinicalName" to MappingProperties.TEXT,
                    "attachAllowed" to MappingProperties.BOOLEAN,
                    "parentId" to MappingProperties.LONG
                )
            ).withTypeName("all")
            whenReady(elasticSearchAdministration.createMapping(mapping).toCompletableFuture()) {
                it shouldBe done()
            }
        }

        "stage5 deleteIndex programatically" {
            whenReady(elasticSearchAdministration.deleteIndex().toCompletableFuture()) {
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
