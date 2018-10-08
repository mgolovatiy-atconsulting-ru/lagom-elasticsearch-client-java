package org.taymyr.lagom.elasticsearch.dsl.search

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import org.taymyr.lagom.elasticsearch.dsl.search.aggregation.Aggregation
import org.taymyr.lagom.elasticsearch.dsl.search.query.QueryBody

data class QueryRoot(
    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    val query: QueryBody?,

    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    @get:JsonProperty("from")
    val pageNumber: Int?,

    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    @get:JsonProperty("size")
    val pageSize: Int?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    @get:JsonProperty("sort")
    val sort: List<SortField>?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    @get:JsonProperty("post_filter")
    val postFilter: QueryBody?,

    @get:JsonInclude(JsonInclude.Include.NON_EMPTY)
    val aggs: Map<String, Aggregation>?
) {

    class Builder {

        private var query: QueryBody? = null
        private var pageNumber: Int? = null
        private var pageSize: Int? = null
        private var sort: List<SortField>? = null
        private var postFilter: QueryBody? = null
        private var aggs: Map<String, Aggregation>? = null

        fun query(query: QueryBody?) = apply { this.query = query }
        fun pageNumber(pageNumber: Int?) = apply { this.pageNumber = pageNumber }
        fun pageSize(pageSize: Int?) = apply { this.pageSize = pageSize }

        fun sort(sort: List<SortField>?) = apply { this.sort = sort }
        fun sort(vararg sort: SortField) = sort(sort.asList())

        fun postFilter(postFilter: QueryBody?) = apply { this.postFilter = postFilter }

        fun aggs(aggs: Map<String, Aggregation>) = apply { this.aggs = aggs }
        fun aggs(vararg aggs: Pair<String, Aggregation>) = aggs(aggs.toMap())

        fun build() = QueryRoot(
            query,
            pageNumber,
            pageSize,
            sort,
            postFilter,
            aggs
        )
    }
}
