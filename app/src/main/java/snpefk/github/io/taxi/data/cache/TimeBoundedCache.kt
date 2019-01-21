package snpefk.github.io.taxi.data.cache

import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

/**
 * Кэш с ограничением жизни объекта по времени. После истечения timeBound
 * объект "удаляется" из кэша.
 *
 * @param timeBound срок жизни объекта в кэше
 */
class TimeBoundedCache<K, V>(private val timeBound: Duration) {

    private val cache: ConcurrentHashMap<K, TimeBoundedValue<V>> = ConcurrentHashMap()

    constructor(timeBoundedCache: TimeBoundedCache<K, V>) : this(timeBoundedCache.timeBound) {
        cache.putAll(timeBoundedCache.cache)
    }

    operator fun get(key: K): V? {
        return cache[key]?.takeUnless { it.isObsolete() }?.value
    }

    operator fun set(key: K, value: V) {
        cache[key] = TimeBoundedValue(value, timeBound)
    }

    operator fun contains(key: K): Boolean = key in cache

    private class TimeBoundedValue<V>(val value: V, duration: Duration) {
        private val deadline = LocalDateTime.now() + duration

        fun isObsolete(): Boolean = LocalDateTime.now() > deadline
    }
}