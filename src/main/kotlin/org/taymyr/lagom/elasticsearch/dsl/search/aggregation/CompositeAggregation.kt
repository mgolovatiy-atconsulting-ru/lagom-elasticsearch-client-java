package org.taymyr.lagom.elasticsearch.dsl.search.aggregation

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class CompositeAggregation(val composite: Composite) : Aggregation {

    data class Source(val terms: TermsAggregation)

    data class Composite(val sources: Map<String, Source>) {

        companion object {

            @JvmStatic fun of(vararg srcs: Pair<String, Source>) = Composite(srcs.toMap())
        }
    }
}