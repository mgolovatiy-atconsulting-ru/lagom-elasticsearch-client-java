package org.taymyr.lagom.elasticsearch.dsl.search.query.term

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class Terms(
    @get: JsonIgnore
    val key: String,
    @get: JsonIgnore
    val value: List<String>
) : HashMap<String, Any>(mapOf(key to value))