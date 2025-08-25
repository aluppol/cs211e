package m01_streams;

public class Streams {
    public static void main(String[] args) {
        assert 0L == Streams.countChars("", 'a');
        System.out.println("countCharsTest_empty passed.");

        assert 0L == Streams.countChars("Hello World", 'z');
        System.out.println("countCharsTest_noMatches passed.");

        assert 5L == Streams.countChars("aaaaa", 'a');
        System.out.println("countCharsTest_allMatches passed.");
        assert 2L == Streams.countChars("aAaaA", 'A');
        System.out.println("countCharsTest_caseSensitive passed.");

        assert 1L == Streams.countChars("x", 'x');
        System.out.println("countCharsTest_singleMatch passed.");

        assert 0L == Streams.countChars("x", 'y');
        System.out.println("countCharsTest_singleNoMatch passed.");

        assert 3L == Streams.countChars("a b!a? a", 'a');
        System.out.println("countCharsTest_spacesPunctuation passed.");

        assert 2L == Streams.countChars("café élan", 'é');
        System.out.println("countCharsTest_unicodeBMP passed.");

        assert 5L == Streams.countChars("Hello, how are you? I am the last e in this line", 'e');
        System.out.println("countCharsTest_scattered passed.");

        String big = "a".repeat(10000) + "b".repeat(5000);
        assert 10000L == Streams.countChars(big, 'a');
        System.out.println("countCharsTest_largeInput passed.");

        assert 2L == Streams.countChars("abc123321", '3');
        System.out.println("countCharsTest_digitChar passed.");

        assert 2L == Streams.countChars("@@!!", '@');
        System.out.println("countCharsTest_symbolChar passed.");

        assert 2L == Streams.countChars("a\na\na", '\n');
        System.out.println("countCharsTest_newlineChar passed.");

        assert 2L == Streams.countChars("a\ta\ta", '\t');
        System.out.println("countCharsTest_tabChar passed.");

        assert 1L == Streams.countChars("abc\0def", '\0');
        System.out.println("countCharsTest_nullChar passed.");
    }

    public static Long countChars(String inputString, char charToCount) {
        if (inputString == null)  return 0L;

        return inputString
                .chars()
                .filter(c -> c == charToCount)
                .count();
    }
}
