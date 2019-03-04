package Comparison;

import arrayGenerator.generator.IntegerArrayGenerator;
import arrayGenerator.scope.IntegerScope;
import linkedList.list.ListAccessError;
import linkedList.list.SingleLinkList;

import java.util.Random;

/**
 * Created by u1661665(Joshua Pritchard) on 19/11/2018.
 * Version : 20/11/2018
 */
public class SingleLinkListComparison
{
    private static final int NUM_TESTS = 100;
    private static final int SIZE_OF_ARRAY = 100;

    private static void timeComparison() throws ListAccessError
    {
        //Create a new random instance to obtain random indices.
        Random rand = new Random();

        //Set up an iterator to gradually increase the size of the array.
        for(int arraySize = SIZE_OF_ARRAY; arraySize <= 1000000; arraySize *= 10 )
        {
            //Create a new integer array based on the specified size of array.
            Integer[] ints = new IntegerArrayGenerator(new IntegerScope()).getArray(arraySize);

            //Use this array to populate a SingleLinkList.
            SingleLinkList<Integer> list = new SingleLinkList<>();
            for(int x = 0; x < arraySize; x++)
            {
                list.add(x, ints[x]);
            }

            System.out.println("Testing array of size: " + arraySize);

            //Repeat this however many times specified by num_tests.
            for (int x = 0; x < NUM_TESTS; x++)
            {
                //Obtain a random index within the array/list.
                int i = rand.nextInt(arraySize);

                //Time how long it takes to access the array and print this value.
                double before = System.nanoTime();
                int arrayInt = ints[i];
                System.out.println("System took " + ((System.nanoTime()) - before) + " ns. to access the array");

                //Time how long it takes to access the list and print this value.
                before = System.nanoTime();
                int listInt = list.get(i);
                System.out.println("System took " + ((System.nanoTime()) - before) + " ns. to access the list.");
            }

            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            SingleLinkListComparison.timeComparison();
        }
        catch (ListAccessError listAccessError)
        {
            listAccessError.printStackTrace();
        }
    }
}
