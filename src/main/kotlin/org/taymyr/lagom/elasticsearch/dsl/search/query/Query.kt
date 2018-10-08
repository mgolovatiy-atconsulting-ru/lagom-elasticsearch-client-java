package org.taymyr.lagom.elasticsearch.dsl.search.query

/**
 * Base data class for all queries Elastic Search.
 *
 * @author Ilya Korshunov {@literal <ikorshunov@fil-it.ru>}.
 */
data class Query(val query: QueryBody)