package com.jn.redisclient.core.key;

import com.jn.langx.util.Preconditions;
import com.jn.langx.util.Strings;
import com.jn.langx.util.collection.Pipeline;
import com.jn.langx.util.function.Function;

import java.util.Collection;
import java.util.List;

public class RedisKeyWrapper {
    public static final String SEPARATOR_DEFAULT = ":";
    public static final String PREFIX_DEFAULT = "";

    private String prefix = PREFIX_DEFAULT;
    private String separation = SEPARATOR_DEFAULT;

    public RedisKeyWrapper() {
    }

    public RedisKeyWrapper(RedisKeyProperties properties) {
        prefix(properties.getPrefix());
        separation(properties.getSeparation());
    }

    public RedisKeyWrapper prefix(String prefix) {
        if (Strings.isNotBlank(prefix)) {
            this.prefix = prefix;
        }
        return this;
    }

    public RedisKeyWrapper separation(String separation) {
        if (Strings.isNotBlank(separation)) {
            this.separation = separation;
        }
        return this;
    }

    public String wrap(String key) {
        Preconditions.checkNotNull(key);
        return Strings.isEmpty(prefix) ? key : (prefix + separation + key);
    }

    public String unwrap(String key) {
        String prefix = this.prefix + separation;
        if (key.startsWith(prefix) && key.length() > prefix.length()) {
            return key.substring(prefix.length());
        }
        return key;
    }

    public static String wrap(RedisKeyWrapper wrapper, String key) {
        return wrapper.wrap(key);
    }

    public static String unwrap(RedisKeyWrapper wrapper, String key) {
        return wrapper.unwrap(key);
    }

    public static <C extends Collection<String>> C unwrap(final RedisKeyWrapper wrapper, C keys) {
        List<String> a = Pipeline.of(keys).map(new Function<String, String>() {
            @Override
            public String apply(String key) {
                return wrapper.wrap(key);
            }
        }).asList();
        keys.clear();
        keys.addAll(a);
        return keys;
    }

    public static <C extends Collection<String>> C wrap(final RedisKeyWrapper wrapper, C keys) {
        List<String> a = Pipeline.of(keys).map(new Function<String, String>() {
            @Override
            public String apply(String key) {
                return wrapper.wrap(key);
            }
        }).asList();
        keys.clear();
        keys.addAll(a);
        return keys;
    }

    public static String[] wrap(final RedisKeyWrapper wrapper, String... keys) {
        return Pipeline.of(keys).map(new Function<String, String>() {
            @Override
            public String apply(String key) {
                return wrapper.wrap(key);
            }
        }).toArray(String[].class);
    }
}
