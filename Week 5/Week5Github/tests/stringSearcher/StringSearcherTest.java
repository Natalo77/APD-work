package stringSearcher;

import arrayGenerator.CharacterArrayGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static java.lang.System.nanoTime;
import static org.junit.jupiter.api.Assertions.*;

abstract class StringSearcherTest {

    abstract StringSearcher getSearcher(String string);

    int NUMBER_OF_STRINGS = 1000;
    int SIZE_OF_STRINGS = 1000;
    int MAX_SIZE_OF_SUBSTRING = 20;

    Random rand = new Random();
    CharacterArrayGenerator stringGenerator = new CharacterArrayGenerator();
    SequentialStringSearcher searcher;

    StringIndex[] data = new StringIndex[1000];

    Character[] stringGen()
    {
        return stringGenerator.getArray(SIZE_OF_STRINGS);
    }

    private int test(String substring,String superstring) throws NotFound {
        return getSearcher(substring).occursIn(superstring);
    }

    @Test
    void testFred() throws NotFound {
        assertEquals(2,test("fred","Alfred the Great"));
    }

    @Test
    void testCap() throws NotFound {
        assertEquals(6,test("cap","The incapable captain capsized the boat"));
    }

    @Test
    void testCab() {
        assertThrows(NotFound.class,()->test("absent","The can sent a message to base"));
    }

    /**
     * Creates a random string using the CharacterArrayGenerator,
     * finds a substring within it,
     * then challenges the Searcher to find the substring.
     *
     * @throws NotFound if the calculated substring is not found within the generated string (impossible)
     */
    @Test
    void testRandomStrings() throws NotFound
    {
        //SetUp
        {
            for (int x = 0; x < NUMBER_OF_STRINGS; x++)
            {
                Character[] chars = stringGen();
                StringBuilder string = new StringBuilder("");

                for (Character c : chars)
                {
                    string.append(c);
                }

                int length = rand.nextInt(MAX_SIZE_OF_SUBSTRING - 1) + 1;
                int index = rand.nextInt(SIZE_OF_STRINGS - MAX_SIZE_OF_SUBSTRING - 1 - length);

                String substring = string.substring(index, index + length);

                data[x] = new StringIndex(string.toString(), substring, new SequentialStringSearcher(substring).occursIn(string.toString()));
            }
        }

        //Tests
        long startTime = nanoTime();
        for(StringIndex a: data)
        {
            try
            {
                assertEquals(a.getIndex(), test(a.getSubString(), a.getString()));
            } catch (NotFound e)
            {
                fail("Failed to find " + a.getSubString() + " in " + a.getString());
            }
        }
        System.out.println("Time taken: " + (nanoTime() - startTime));
    }
}