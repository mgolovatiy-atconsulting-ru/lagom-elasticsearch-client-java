package org.taymyr.lagom.elasticsearch.dsl.search.query.compound

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
data class BoolQuery(val bool: BoolQueryBody) : CompoundQuery
