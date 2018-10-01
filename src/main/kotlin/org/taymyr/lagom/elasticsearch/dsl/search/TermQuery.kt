package org.taymyr.lagom.elasticsearch.dsl.search

data class TermQuery(
    val term: Term
) : Query {

    interface Term

    companion object {

        @JvmStatic fun of(term: Term) = TermQuery(term)

        @JvmStatic fun of(vararg term: Term): List<TermQuery> = term.map { of(it) }
    }
}
