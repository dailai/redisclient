package com.jn.redisclient;

import com.jn.langx.util.Preconditions;
import com.jn.langx.util.Strings;

public class RedisKeyBuilder {
    String prefix = "";
    String separation = ":";

    public RedisKeyBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public RedisKeyBuilder separation(String separation) {
        this.separation = separation;
        return this;
    }

    public String build(String key) {
        Preconditions.checkNotNull(key);
        return Strings.isEmpty(prefix) ? key : (prefix + separation + key);
    }
}
