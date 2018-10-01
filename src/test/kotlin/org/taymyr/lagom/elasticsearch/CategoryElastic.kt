package org.taymyr.lagom.elasticsearch

data class CategoryElastic(
    val id: Long,
    val name: List<String>,
    val title: Map<String, String>,
    val technicalName: String,
    val attachAllowed: Boolean,
    val parentId: Long
) : Indexable {

    override fun getId() = id.toString()
}
