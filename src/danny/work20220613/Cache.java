package danny.work20220613;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * A class for caching key-value pairs. If the key-value pair is not found in the cache, the supplied finder will
 * attempt to return the appropriate value for the key parameter, and the returned key-value pair will be added to the
 * cache if the value is not null.
 */

public final class Cache<K, V> {
    private final LinkedHashMap<K, V> cache = new LinkedHashMap<>();
    private final Function<K, V> fn;
    private final int cacheSize;

    public Cache(Function<K, V> fetcher) {
        this(fetcher, 100);
    }

    public Cache(Function<K, V> fn, int cacheSize) {
        this.fn = fn;
        this.cacheSize = cacheSize;
    }

    public V lookup(K key) {
        V value = cache.get(key);
        if (value == null) {
            value = fn.apply(key);
            if (value != null) {
                if (cache.size() == cacheSize) {
                    cache.remove(cache.keySet().iterator().next());
                }
                cache.put(key, value);
            }
        }
        return value;
    }
}
