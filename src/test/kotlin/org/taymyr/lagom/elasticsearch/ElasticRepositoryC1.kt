package org.taymyr.lagom.elasticsearch

class ElasticRepositoryC1(
    elasticSearch: ElasticSearch
) : AbstractElasticRepository<CategoryElastic, CategorySearchResult>(elasticSearch) {

    override fun getIndexName() = "c1"

    override fun getTypeName() = "all"
}
