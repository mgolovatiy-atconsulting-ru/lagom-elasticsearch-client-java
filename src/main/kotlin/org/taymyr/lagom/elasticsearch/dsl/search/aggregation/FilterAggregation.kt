package org.taymyr.lagom.elasticsearch.dsl.search.aggregation

import org.taymyr.lagom.elasticsearch.dsl.search.query.Query

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class FilterAggregation(val filter: Query, val aggs: Map<String, Aggregation>) : Aggregation {

    companion object {

        @JvmStatic fun of(filter: Query, aggs: Map<String, Aggregation>) = FilterAggregation(filter, aggs)

        @JvmStatic fun of(filter: Query, vararg aggs: Pair<String, Aggregation>) = FilterAggregation(filter, aggs.toMap())
    }
}