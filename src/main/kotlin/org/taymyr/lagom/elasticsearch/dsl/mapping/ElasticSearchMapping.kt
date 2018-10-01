package org.taymyr.lagom.elasticsearch.dsl.mapping

data class ElasticSearchMapping(
    val properties: Map<String, MappingProperty>
) {

    fun withTypeName(typeName: String) = mapOf(typeName to this)
}
