package RandomMethods;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by u1661665 (Joshua Pritchard) on 24/10/2018.
 */

/**
 * Defines a static method for returning a random array of Strings.
 */
public class RandomStringArray
{
    /**
     * Generates a randomly sized and populated arrayList of Strings as per the parameters specified.
     *
     * @param arrayLowSize the lowest size the arrayList can be.
     * @param arrayHighSize the highest size the arrayList can be.
     * @param stringLowLen the lowest size a string within the arraylist can be.
     * @param stringHighLen the highest size a string within the arrayList can be.
     * @return the arrayList generated and populated with random strings.
     */
    public static ArrayList<String> getRandomStringArray(int arrayLowSize, int arrayHighSize, int stringLowLen, int stringHighLen)
    {
        Random rand = new Random();

        //values used for random value generation.
        int randArrayBound = arrayHighSize - arrayLowSize;
        int randStringBound = stringHighLen - stringLowLen;
        int arraySize = rand.nextInt(randArrayBound) + arrayLowSize;

        //The array of strings to be created.
        ArrayList<String> testData = new ArrayList<>(arraySize);

        //Add random strings into 'testData' 'arraySize' times.
        for(int x = 0; x < arraySize; x++)
        {
            //Create a new random string generator, with a random length of string.
            RandomMethods.RandomString randString = new RandomMethods.RandomString
                    (rand.nextInt(randStringBound) + stringLowLen, ThreadLocalRandom.current());

            //Generate a random string and add it to the array.
            testData.add(randString.nextString());
        }

        return testData;
    }
}

