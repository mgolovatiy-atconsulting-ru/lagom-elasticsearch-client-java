package org.taymyr.lagom.elasticsearch.dsl

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.taymyr.lagom.elasticsearch.Indexable

data class UpdateIndexItem<T : Indexable> @JsonCreator constructor(
    val doc: T,
    @get:JsonProperty("doc_as_upsert") val docAsUpsert: Boolean = true
)
