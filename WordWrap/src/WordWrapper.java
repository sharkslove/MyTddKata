import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class WordWrapper {
    private static int length;
    public WordWrapper(int length){
        this.length = length;
    }

    public static String wrap(String s, int length) throws InvalidArgument {
        return new WordWrapper(length).wrap(s);
    }

    public String wrap(String s) throws InvalidArgument {
        if (length <1)
            throw  new InvalidArgument();
        if (s == null)
            return "";
        if (s.length() <= length)
            return s;
        else{
            int space = s.indexOf(" ");
            if (space > 0){
                return s.substring(0, space) + "\n" + wrap(s.substring(space +1), length);
            }
            return s.substring(0, length) + "\n" + wrap(s.substring(length), length);
        }
    }



    public  static class InvalidArgument extends Exception {
    }

    @Test
    public void WrapNullReturnsEmptyString() throws Exception {
        assertThat(wrap(null, 10), is(""));
    }

    @Test
    public void WrapEmptyStringReturnEmptyString() throws Exception{
        assertThat(wrap("", 10), is(""));
    }

    @Test
    public void OneShoutWorkDoesNotWrap() throws Exception{
        assertThat(wrap("word", 10), is("word"));
    }

    @Test(expected = WordWrapper.InvalidArgument.class)
    public void LengthLessThanOneShouldThrowInvalidArgument() throws Exception {
        wrap("xxx", 0);
    }
    @Test
    public void TwoWordsLongerThanLimitShouldWrap() throws Exception{
        assertThat(wrap("words words", 6), is("words\nwords"));
        assertThat(wrap("words here", 6), is("words\nhere"));
        assertThat(wrap("words here words", 6), is("words\nhere\nwords"));
    }

    @Test
    public void WordLongerThanLengthBreaksAtLength() throws Exception{
        assertThat(wrap("word1word1", 5), is("word1\nword1"));
        assertThat(wrap("longerword", 6), is("longer\nword"));
        assertThat(wrap("verylongword", 4), is("very\nlong\nword"));

    }



}
