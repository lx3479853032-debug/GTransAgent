package net.gtransagent.saas.billing

import net.gtransagent.saas.model.SubscriptionPlan
import net.gtransagent.saas.model.User

class BillingService {
    private val userUsage = mutableMapOf<String, Int>()

    fun recordUsage(userId: String, callCount: Int = 1): Boolean {
        val currentUsage = userUsage[userId] ?: 0
        userUsage[userId] = currentUsage + callCount
        return true
    }

    fun checkUsageLimit(user: User): Boolean {
        val currentUsage = userUsage[user.id] ?: 0
        return currentUsage < user.monthlyLimit
    }

    fun getMonthlyUsage(userId: String): Int {
        return userUsage[userId] ?: 0
    }

    fun getRemainingUsage(user: User): Int {
        val currentUsage = userUsage[user.id] ?: 0
        return (user.monthlyLimit - currentUsage).coerceAtLeast(0)
    }

    fun resetMonthlyUsage(userId: String) {
        userUsage[userId] = 0
    }

    fun calculateOverageFee(user: User, overageCount: Int): Double {
        return when (user.plan) {
            SubscriptionPlan.FREE -> 0.01 * overageCount
            SubscriptionPlan.PROFESSIONAL -> 0.005 * overageCount
            SubscriptionPlan.ENTERPRISE -> 0.0
        }
    }
}