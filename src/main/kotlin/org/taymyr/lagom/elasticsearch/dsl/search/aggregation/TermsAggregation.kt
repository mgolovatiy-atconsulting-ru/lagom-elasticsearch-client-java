package org.taymyr.lagom.elasticsearch.dsl.search.aggregation

import com.fasterxml.jackson.annotation.JsonInclude
import org.taymyr.lagom.elasticsearch.dsl.search.Order

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class TermsAggregation(
    val terms: FieldSpec,

    @get:JsonInclude(JsonInclude.Include.NON_NULL)
    val aggs: Map<String, Aggregation>? = null
) : Aggregation {

    data class FieldSpec(val field: String, val size: Int? = null, val order: Order? = null)

    companion object {

        @JvmStatic fun of(terms: FieldSpec, aggs: Map<String, Aggregation>) = TermsAggregation(terms, aggs)

        @JvmStatic fun of(terms: FieldSpec, vararg aggs: Pair<String, Aggregation>) = TermsAggregation(terms, aggs.toMap())

        @JvmStatic fun of(terms: FieldSpec) = TermsAggregation(terms, null)
    }
}