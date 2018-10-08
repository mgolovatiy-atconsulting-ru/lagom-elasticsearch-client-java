package org.taymyr.lagom.elasticsearch.dsl.search

/**
 * @author Evgeny Stankevich {@literal <estankevich@fil-it.ru>}.
 */
abstract class Order(val expression: String, val value: String) {

    data class AscOrder(val expr: String) : Order(expr, "asc")

    data class DescOrder(val expr: String) : Order(expr, "desc")

    companion object {

        @JvmStatic fun asc(expr: String) = AscOrder(expr)

        @JvmStatic fun desc(expr: String) = DescOrder(expr)
    }
}