package danny.work20220613;
import java.util.LinkedHashMap;

/**
 * A class for caching key-value pairs. If the key-value pair is not found in the cache, the supplied finder will
 * attempt to return the appropriate value for the key parameter, and the returned key-value pair will be added to the
 * cache if the value is not null.
 */

public final class Cache<K, V> {
    private final LinkedHashMap<K, V> cache = new LinkedHashMap<>();
    private final Fetcher<K, V> fetcher;
    private final int cacheSize;

    public Cache(Fetcher<K, V> fetcher) {
        this(fetcher, 100);
    }

    public Cache(Fetcher<K, V> fetcher, int cacheSize) {
        this.fetcher = fetcher;
        this.cacheSize = cacheSize;
    }

    public V lookup(K key) {
        V value = cache.get(key);
        if (value == null) {
            value = fetcher.apply(key);
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
