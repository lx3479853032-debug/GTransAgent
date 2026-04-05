package net.gtransagent.saas.model;

import java.time.LocalDateTime;
import java.util.UUID;

data class User(val id: String = UUID.randomUUID().toString(), val email: String, val username: String, val region: Region = Region.CN, val currency: Currency = Currency.CNY, val plan: SubscriptionPlan = SubscriptionPlan.FREE, val apiKey: String = UUID.randomUUID().toString(), val monthlyUsage: Int = 0, val monthlyLimit: Int = 100, val subscriptionStartDate: LocalDateTime = LocalDateTime.now(), val createdAt: LocalDateTime = LocalDateTime.now(), val isActive: Boolean = true);

enum class Region(val displayName: String, val defaultCurrency: Currency) {
    CN("中国", Currency.CNY),
    US("美国", Currency.USD),
    EU("欧洲", Currency.EUR),
    JP("日本", Currency.JPY),
    SG("新加坡", Currency.SGD),
    OTHER("其他", Currency.USD)
};

enum class Currency(val symbol: String, val code: String) {
    CNY("¥", "CNY"),
    USD("$", "USD"),
    EUR("€", "EUR"),
    JPY("¥", "JPY"),
    SGD("S$", "SGD")
};

enum class SubscriptionPlan(val priceMap: Map<Currency, Double>, val monthlyLimit: Int, val displayName: String) {
    FREE(mapOf(Currency.CNY to 0.0, Currency.USD to 0.0, Currency.EUR to 0.0, Currency.JPY to 0.0, Currency.SGD to 0.0), 100, "Free"),
    PROFESSIONAL(mapOf(Currency.CNY to 20.0, Currency.USD to 3.0, Currency.EUR to 2.8, Currency.JPY to 330.0, Currency.SGD to 4.0), 10000, "Professional"),
    ENTERPRISE(mapOf(Currency.CNY to 100.0, Currency.USD to 15.0, Currency.EUR to 14.0, Currency.JPY to 1650.0, Currency.SGD to 20.0), Int.MAX_VALUE, "Enterprise");
    fun getPrice(currency: Currency): Double {
        return priceMap[currency] ?: priceMap[Currency.USD] ?: 0.0
    }
};

data class UsageRecord(val id: String = UUID.randomUUID().toString(), val userId: String, val callCount: Int = 1, val timestamp: LocalDateTime = LocalDateTime.now(), val translator: String = "", val sourceLanguage: String = "", val targetLanguage: String = "")