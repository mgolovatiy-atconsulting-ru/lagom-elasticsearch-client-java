package org.taymyr.lagom.elasticsearch.dsl.search.query.term

data class TermsQuery(val terms: Terms) : TermLevelQuery {

    companion object {

        @JvmStatic fun terms(key: String, value: List<String>): TermsQuery {
            return TermsQuery(Terms(key, value))
        }
    }
}