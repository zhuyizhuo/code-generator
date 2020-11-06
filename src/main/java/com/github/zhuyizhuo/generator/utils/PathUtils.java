package com.github.zhuyizhuo.generator.utils;

/**
 * 路径工具类
 *
 * @author zhuo
 * @since 1.5.0
 */
public class PathUtils {

    private final char match;

    public PathUtils(char match) {
        this.match = match;
    }

    private boolean matches(char c) {
        return c == match;
    }

    /**
     * Returns a string copy of the input character sequence, with each group of consecutive matching
     * BMP characters replaced by a single replacement character. For example:
     *
     * <pre>{@code
     * CharMatcher.anyOf("eko").collapseFrom("bookkeeper", '-')
     * }</pre>
     *
     * ... returns {@code "b-p-r"}.
     *
     * @param sequence the character sequence to replace matching groups of characters in
     * @param replacement the character to append to the result string in place of each group of
     *     matching characters in {@code sequence}
     * @return the new string
     */
    public String collapseFrom(CharSequence sequence, char replacement) {
        // This implementation avoids unnecessary allocation.
        int len = sequence.length();
        for (int i = 0; i < len; i++) {
            char c = sequence.charAt(i);
            if (matches(c)) {
                if (c == replacement && (i == len - 1 || !matches(sequence.charAt(i + 1)))) {
                    // a no-op replacement
                    i++;
                } else {
                    StringBuilder builder = new StringBuilder(len).append(sequence, 0, i).append(replacement);
                    return finishCollapseFrom(sequence, i + 1, len, replacement, builder, true);
                }
            }
        }
        // no replacement needed
        return sequence.toString();
    }

    private String finishCollapseFrom(
            CharSequence sequence,
            int start,
            int end,
            char replacement,
            StringBuilder builder,
            boolean inMatchingGroup) {
        for (int i = start; i < end; i++) {
            char c = sequence.charAt(i);
            if (matches(c)) {
                if (!inMatchingGroup) {
                    builder.append(replacement);
                    inMatchingGroup = true;
                }
            } else {
                builder.append(c);
                inMatchingGroup = false;
            }
        }
        return builder.toString();
    }

}
