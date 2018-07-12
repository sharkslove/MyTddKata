import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class WordWrapperTest {
    @Test
    public void WrapNullReturnsEmptyString() throws Exception {
        assertThat(WordWrapper.wrap(null, 10), is(""));
    }

    @Test
    public void WrapEmptyStringReturnEmptyString() throws Exception{
        assertThat(WordWrapper.wrap("", 10), is(""));
    }

    @Test
    public void OneShoutWorkDoesNotWrap() throws Exception{
        assertThat(WordWrapper.wrap("word", 10), is("word"));
    }

    @Test(expected = WordWrapper.InvalidArgument.class)
    public void LengthLessThanOneShouldThrowInvalidArgument() throws Exception {
        WordWrapper.wrap("xxx", 0);
    }
    @Test
    public void TwoWordsLongerThanLimitShouldWrap() throws Exception{
        assertThat(WordWrapper.wrap("words words", 6), is("words\nwords"));
        assertThat(WordWrapper.wrap("words here", 6), is("words\nhere"));
        assertThat(WordWrapper.wrap("words here words", 6), is("words\nhere\nwords"));
    }

    @Test
    public void WordLongerThanLengthBreaksAtLength() throws Exception{
        assertThat(WordWrapper.wrap("word1word1", 5), is("word1\nword1"));
        assertThat(WordWrapper.wrap("longerword", 6), is("longer\nword"));
        assertThat(WordWrapper.wrap("verylongword", 4), is("very\nlong\nword"));
    }

    @Test
    public void ThreeWordsJustOverTheLimitShouldBreakAtSecond() throws Exception{
        assertThat(WordWrapper.wrap("word word word", 11), is("word"));
    }

}