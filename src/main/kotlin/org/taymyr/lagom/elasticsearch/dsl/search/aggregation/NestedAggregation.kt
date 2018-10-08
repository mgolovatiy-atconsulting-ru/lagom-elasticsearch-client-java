package org.taymyr.lagom.elasticsearch.dsl.search.aggregation

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class NestedAggregation(val nested: NestedPath, val aggs: Map<String, Aggregation>) : Aggregation {

    data class NestedPath(val path: String)

    companion object {

        @JvmStatic fun of(path: String, aggs: Map<String, Aggregation>) = NestedAggregation(NestedPath(path), aggs)

        @JvmStatic fun of(path: String, vararg aggs: Pair<String, Aggregation>) = NestedAggregation(NestedPath(path), aggs.toMap())
    }
}