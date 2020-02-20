package com.jn.redisclient.core.key;

public class RedisKeyProperties {
    private String prefix = RedisKeyBuilder.PREFIX_DEFAULT;
    private String separation = RedisKeyBuilder.SEPARATOR_DEFAULT;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSeparation() {
        return separation;
    }

    public void setSeparation(String separation) {
        this.separation = separation;
    }
}
