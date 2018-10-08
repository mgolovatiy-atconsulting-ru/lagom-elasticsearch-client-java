package org.taymyr.lagom.elasticsearch.dsl.search.query.joining

import org.taymyr.lagom.elasticsearch.dsl.search.query.QueryBody

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class NestedQueryBody(val path: String, val query: QueryBody) : JoiningQuery