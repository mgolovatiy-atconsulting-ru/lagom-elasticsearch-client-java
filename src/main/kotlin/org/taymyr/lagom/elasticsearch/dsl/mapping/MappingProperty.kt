package org.taymyr.lagom.elasticsearch.dsl.mapping

import com.fasterxml.jackson.annotation.JsonInclude

data class MappingProperty(
    val type: String,
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val analyzer: String? = null
) {

    constructor(type: MappingTypes, analyzer: String? = null) : this(type.title, analyzer)
}
