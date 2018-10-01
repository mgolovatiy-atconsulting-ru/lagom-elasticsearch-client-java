package org.taymyr.lagom.elasticsearch

import akka.Done
import akka.NotUsed
import com.lightbend.lagom.javadsl.api.Descriptor
import com.lightbend.lagom.javadsl.api.Service
import com.lightbend.lagom.javadsl.api.Service.named
import com.lightbend.lagom.javadsl.api.Service.restCall
import com.lightbend.lagom.javadsl.api.ServiceCall
import com.lightbend.lagom.javadsl.api.deser.PathParamSerializers
import com.lightbend.lagom.javadsl.api.transport.Method.DELETE
import com.lightbend.lagom.javadsl.api.transport.Method.GET
import com.lightbend.lagom.javadsl.api.transport.Method.POST
import com.lightbend.lagom.javadsl.api.transport.Method.PUT
import org.taymyr.lagom.elasticsearch.dsl.search.QueryRoot
import org.taymyr.lagom.elasticsearch.serialize.JsonBytes
import org.taymyr.lagom.elasticsearch.serialize.JsonBytesSerializer
import kotlin.reflect.jvm.javaMethod

interface ElasticSearch : Service {

    fun deleteIndex(index: String): ServiceCall<NotUsed, Done>

    fun createIndexWithSettings(index: String): ServiceCall<JsonBytes, Done>

    fun createMapping(index: String, type: String): ServiceCall<JsonBytes, Done>

    fun updateIndex(index: String, type: String, id: String): ServiceCall<JsonBytes, Done>

    fun search(index: String, type: String): ServiceCall<QueryRoot, JsonBytes>

    fun deleteById(index: String, type: String, id: String): ServiceCall<NotUsed, Done>

    @JvmDefault
    override fun descriptor(): Descriptor = named("elastic-search")
        .withCalls(
            restCall<NotUsed, Done>(DELETE, "/:index", ElasticSearch::deleteIndex.javaMethod),
            restCall<JsonBytes, Done>(PUT, "/:index", ElasticSearch::createIndexWithSettings.javaMethod),
            restCall<JsonBytes, Done>(PUT, "/:index/_mapping/:type", ElasticSearch::createMapping.javaMethod),
            restCall<QueryRoot, JsonBytes>(GET, "/:index/:type/_search", ElasticSearch::search.javaMethod),
            restCall<JsonBytes, Done>(POST, "/:index/:type/:id/_update", ElasticSearch::updateIndex.javaMethod),
            restCall<NotUsed, Done>(DELETE, "/:index/:type/:id", ElasticSearch::deleteById.javaMethod)
        )
        .withPathParamSerializer(String::class.java, PathParamSerializers.STRING)
        .withMessageSerializer(JsonBytes::class.java, JsonBytesSerializer())
}
