package org.taymyr.lagom.elasticsearch.dsl.search.query.term

data class TermQuery(val term: Term) : TermLevelQuery {

    companion object {

        @JvmStatic fun term(key: String, value: String): TermQuery {
            return TermQuery(Term(key, value))
        }

        @JvmStatic fun term(kv: Pair<String, String>): TermQuery {
            return TermQuery(Term(kv.first, kv.second))
        }
    }
}
