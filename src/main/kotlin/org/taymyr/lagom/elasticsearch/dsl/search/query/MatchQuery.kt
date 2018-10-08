package org.taymyr.lagom.elasticsearch.dsl.search.query

data class MatchQuery(
    val match: Match
) : QueryBody {

    interface Match

    companion object {

        @JvmStatic fun of(match: Match) = MatchQuery(match)
    }
}
