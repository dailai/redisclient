package com.jn.redisclient.key;

import com.jn.langx.util.Preconditions;
import com.jn.langx.util.Strings;

public class RedisKeyBuilder {
    public static final String SEPARATOR_DEFAULT = ":";
    public static final String PREFIX_DEFAULT = "";

    private String prefix = PREFIX_DEFAULT;
    private String separation = SEPARATOR_DEFAULT;

    public RedisKeyBuilder(RedisKeyProperties properties) {
        prefix(properties.getPrefix());
        separation(properties.getSeparation());
    }

    public RedisKeyBuilder prefix(String prefix) {
        if (Strings.isNotBlank(prefix)) {
            this.prefix = prefix;
        }
        return this;
    }

    public RedisKeyBuilder separation(String separation) {
        if (Strings.isNotBlank(separation)) {
            this.separation = separation;
        }
        return this;
    }

    public String build(String key) {
        Preconditions.checkNotNull(key);
        return Strings.isEmpty(prefix) ? key : (prefix + separation + key);
    }
}
