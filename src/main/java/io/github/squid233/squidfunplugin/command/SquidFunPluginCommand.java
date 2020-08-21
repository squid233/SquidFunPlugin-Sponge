package io.github.squid233.squidfunplugin.command;

import io.github.squid233.squidfunplugin.config.Config;
import io.github.squid233.squidfunplugin.config.ConfigurationNodes;
import io.github.squid233.squidfunplugin.lang.LangCode;
import io.github.squid233.squidfunplugin.lang.Language;
import io.github.squid233.squidfunplugin.metadata.Metadata;
import io.github.squid233.squidfunplugin.permission.Permission;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;

import static io.github.squid233.squidfunplugin.lang.LangCode.Locale.EN_US;
import static io.github.squid233.squidfunplugin.lang.LangCode.Locale.ZH_CN;

/**
 * @author squid233
 */
public class SquidFunPluginCommand implements CommandExecutor {
    public static final CommandSpec SQUID_FUN_PLUGIN_COMMAND = CommandSpec.builder()
            .description(Text.of("SquidFunPlugin misc"))
            .executor(new SquidFunPluginCommand())
            .child(ConfigChild.CONFIG_COMMAND, "config", "cfg")
            .child(ForceGcChild.FORCE_GC_COMMAND, "forcegc", "gc")
            .build();

    @Override
    public @NotNull CommandResult execute(@NotNull CommandSource src, @NotNull CommandContext args) {
        src.sendMessage(Text.of("SquidFunPlugin Version: " + Metadata.VERSION));
        return CommandResult.success();
    }

    public static class ConfigChild implements CommandExecutor {
        public static final CommandSpec CONFIG_COMMAND = CommandSpec.builder()
                .description(Text.of("Set config"))
                .child(LangChild.LANG_COMMAND, "lang")
                .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("lang"))))
                .executor(new ConfigChild())
                .build();

        @Override
        public @NotNull CommandResult execute(@NotNull CommandSource src, @NotNull CommandContext args) {
            return CommandResult.empty();
        }

        public static class LangChild implements CommandExecutor {
            public static final CommandSpec LANG_COMMAND = CommandSpec.builder()
                    .description(Text.of("Set lang"))
                    .arguments(GenericArguments.onlyOne(GenericArguments.string(Text.of("lang"))))
                    .executor(new LangChild())
                    .build();

            @Override
            public @NotNull CommandResult execute(@NotNull CommandSource src, @NotNull CommandContext args) {
                String l = args.<String>getOne("lang").orElse(LangCode.getCode(EN_US));
                if (l.equals(LangCode.getCode(EN_US)) || l.equals(LangCode.getCode(ZH_CN))) {
                    if (Permission.isOp(src)) {
                        Config.getInstance().setAndSaveThenReload(ConfigurationNodes.getInstance().langNode, l);
                        Language.setCurrentLangAndReload(l);
                        return CommandResult.success();
                    } else {
                        src.sendMessage(Text.builder(Language.get("msg.no_permission")).color(TextColors.DARK_RED).build());
                    }
                } else {
                    src.sendMessage(Text.builder("Now available languages: ").append(Text.of(Arrays.toString(LangCode.LANG_CODES))).build());
                }
                return CommandResult.empty();
            }
        }
    }

    public static class ForceGcChild implements CommandExecutor {
        public static CommandSpec FORCE_GC_COMMAND = CommandSpec.builder()
                .description(Text.of("Force execute System.gc()"))
                .executor(new ForceGcChild())
                .build();

        @Override
        public @NotNull CommandResult execute(@NotNull CommandSource src, @NotNull CommandContext args) {
            System.gc();
            src.sendMessage(Text.of("Current free memory: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 / 1024 + " GB"));
            return CommandResult.success();
        }
    }
}
