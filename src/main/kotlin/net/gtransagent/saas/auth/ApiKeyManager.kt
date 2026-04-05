package net.gtransagent.saas.auth

import java.util.UUID

class ApiKeyManager {
    private val apiKeys = mutableMapOf<String, String>()

    fun generateApiKey(userId: String): String {
        val apiKey = UUID.randomUUID().toString()
        apiKeys[userId] = apiKey
        return apiKey
    }

    fun getApiKey(userId: String): String? {
        return apiKeys[userId]
    }

    fun revokeApiKey(userId: String) {
        apiKeys.remove(userId)
    }
}