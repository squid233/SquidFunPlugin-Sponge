package io.github.squid233.squidfunplugin.lang;

import org.intellij.lang.annotations.MagicConstant;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static io.github.squid233.squidfunplugin.lang.LangCode.Locale.*;

/**
 * @author squid233
 */
public class LangCode {
    private final String code;
    private final int rawId;

    public static final String[] LANG_CODES = {EN_US.code, ZH_CN.code};

    @Contract(pure = true)
    private LangCode(String code, int rawId) {
        this.code = code;
        this.rawId = rawId;
    }

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull LangCode of(String code, int rawId) {
        return new LangCode(code, rawId);
    }

    public static String getByRawId(int rawId) {
        return LANG_CODES[rawId];
    }

    @Contract(pure = true)
    public static int getRawId(@MagicConstant(flagsFromClass = LangCode.Locale.class) LangCode langCode) {
        return langCode.rawId;
    }

    @Contract(pure = true)
    public static String getCode(@MagicConstant(flagsFromClass = LangCode.Locale.class) LangCode langCode) {
        return LANG_CODES[getRawId(langCode)];
    }

    public static class Locale {
        public static final LangCode EN_US = of("en_us", 0);
        public static final LangCode ZH_CN = of("zh_cn", 1);
    }
}
