package io.github.squid233.squidfunplugin.permission;

import org.spongepowered.api.command.CommandSource;

/**
 * @author squid233
 */
public class Permission {
    public static boolean isOp(CommandSource src) {
        for (Permissions permission : Permissions.values()) {
            if (!src.hasPermission(permission.get())) {
                return false;
            }
        }
        return true;
    }
}
