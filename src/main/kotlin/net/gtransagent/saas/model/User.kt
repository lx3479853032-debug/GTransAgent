package net.gtransagent.saas.model

import java.time.LocalDateTime
import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val username: String,
    val plan: SubscriptionPlan = SubscriptionPlan.FREE,
    val apiKey: String = UUID.randomUUID().toString(),
    val monthlyUsage: Int = 0,
    val monthlyLimit: Int = 100,
    val subscriptionStartDate: LocalDateTime = LocalDateTime.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isActive: Boolean = true
)

enum class SubscriptionPlan(val monthlyPrice: Double, val monthlyLimit: Int, val displayName: String) {
    FREE(0.0, 100, "免费版"),
    PROFESSIONAL(20.0, 10000, "专业版"),
    ENTERPRISE(100.0, Int.MAX_VALUE, "企业版")
}

data class UsageRecord(
    val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val callCount: Int = 1,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val translator: String = "",
    val sourceLanguage: String = "",
    val targetLanguage: String = ""
)