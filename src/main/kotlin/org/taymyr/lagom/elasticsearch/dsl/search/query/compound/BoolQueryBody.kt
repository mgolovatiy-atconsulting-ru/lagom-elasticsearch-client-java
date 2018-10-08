package org.taymyr.lagom.elasticsearch.dsl.search.query.compound

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.taymyr.lagom.elasticsearch.dsl.search.query.QueryBody

data class BoolQueryBody @JsonCreator constructor(
    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val should: List<QueryBody>?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    @get:JsonProperty("must_not")
    val mustNot: List<QueryBody>?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val must: List<QueryBody>?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val filter: List<QueryBody>?
) {

    class Builder {

        private var should: List<QueryBody>? = null
        private var mustNot: List<QueryBody>? = null
        private var must: List<QueryBody>? = null
        private var filter: List<QueryBody>? = null

        fun should(should: List<QueryBody>?) = apply { this.should = should }
        fun should(vararg should: QueryBody) = should(should.asList())

        fun mustNot(mustNot: List<QueryBody>?) = apply { this.mustNot = mustNot }
        fun mustNot(vararg mustNot: QueryBody) = mustNot(mustNot.asList())

        fun must(must: List<QueryBody>?) = apply { this.must = must }
        fun must(vararg must: QueryBody) = must(must.asList())

        fun filter(filter: List<QueryBody>?) = apply { this.filter = filter }
        fun filter(vararg filter: QueryBody) = filter(filter.asList())

        fun build() = BoolQueryBody(should, mustNot, must, filter)
    }
}
