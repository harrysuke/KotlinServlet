package com.example.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JsonUtil {
    @PublishedApi
    internal val objectMapper = ObjectMapper() 
    private val mapper = ObjectMapper().registerKotlinModule()

    fun toJson(obj: Any): String = mapper.writeValueAsString(obj)

    inline fun <reified T> fromJson(json: String): T {
        return objectMapper.readValue(json, T::class.java)
    }
}