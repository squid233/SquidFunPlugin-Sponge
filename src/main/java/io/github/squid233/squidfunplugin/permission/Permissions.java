package io.github.squid233.squidfunplugin.permission;

import org.jetbrains.annotations.Contract;

/**
 * @author squid233
 */
public enum Permissions {

    /**
     * Plugins' permission
     */
    MINECRAFT("minecraft"),
    LUCKPERMS("luckperms"),
    ;

    private final String permission;

    @Contract(pure = true)
    Permissions(String permission) {
        this.permission = permission;
    }

    @Override
    @Contract(pure = true)
    public String toString() {
        return get();
    }

    @Contract(pure = true)
    public String get() {
        return permission;
    }
}
