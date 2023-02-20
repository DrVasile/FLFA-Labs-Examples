package utils;

import java.util.regex.Pattern;

public class StringUtils
{
    private static final Pattern UPPER_LETTERS = Pattern.compile("^(?=.*[A-Z]).+$");
    public static boolean isAllLower(final String string)
    {
        return !UPPER_LETTERS.matcher(string).matches();
    }
}
