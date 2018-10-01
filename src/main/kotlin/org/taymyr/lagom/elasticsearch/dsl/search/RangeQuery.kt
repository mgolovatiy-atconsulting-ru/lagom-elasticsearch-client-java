package org.taymyr.lagom.elasticsearch.dsl.search

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.OptionalInt

data class RangeQuery(
    val range: Range
) : Query {

    interface Range

    companion object {

        @JvmStatic fun of(range: Range) = RangeQuery(range)
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    data class LteGte @JsonCreator constructor(
        val lte: OptionalInt,
        val gte: OptionalInt
    )
}
