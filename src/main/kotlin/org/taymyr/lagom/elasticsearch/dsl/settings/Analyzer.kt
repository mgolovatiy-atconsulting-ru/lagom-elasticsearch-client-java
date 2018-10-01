package org.taymyr.lagom.elasticsearch.dsl.settings

import com.fasterxml.jackson.annotation.JsonInclude

data class Analyzer(
    val type: String,
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val tokenizer: String?,
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val filter: List<String>?
)
