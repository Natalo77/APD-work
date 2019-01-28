package counter;

/**
 * A demonstration of the use and behaviour of Counters and ThreadSets.
 *
 * @author Hugh Osborne
 * @implNote Edited: Joshua Pritchard (U1661665)
 * @version January 2019
 */
public class Main {
    /**
     * Demonstrate the behaviour of counters and ThreadSets.  A thread set is populated with two counters, and
     * the thread set's runSet method is used to run the counters concurrently.
     *
     * @param args not used
     * @throws CounterException should not occur
     * @throws InterruptedException should not occur
     */
    public static void main(String[] args) throws CounterException, InterruptedException {
		/*
		 * Create two counters (in a thread set), and then run them with tracing on, so that their
		 * behaviour is visible.
		 */
        ThreadSet<Counter> counters = new ThreadHashSet<>();  // will contain the counters
        counters.add(new Counter("up",0,10)); // counter "up" counts from 5 to 10
        counters.add(new Counter("down",10,0)); // counter "down" counts from  to 0
        Counter.traceOn(); // switch tracing on
        Counter.setDelay(0.001); // set a delay from 0.0 to 0.1 seconds
        counters.runSet(); // run the counters (concurrently)
    }

    /**
     * Important here: delay is not 0.1 for everything, it is between 0.0 and 0.1, chosen randomly.
     *  . The behaviour observed is that eventually one counter overpowers the other in a concurrency 'tug of war'.
     *  . Obviously this means that the other counter is then free to count to its goal value.
     *  . Both counters are put in equal starting positions with the same difference between their starting values and their goal values.
     *  . The above means that it should be an even distribution of which counter reaches its goal value first.
     *  . This is because the random time generation has no bias towards either counter, which results in a 0.5/0.5 chance for each counter to perform its operation.
     *  .
     *  . [insert excel here]
     *  .
     *  . The test data shows the results of 20 iterations of the standard main method.
     *  . There is a small possibility, given the equal starting positions, that the system could run in an endless loop of counters.
     *  . The above is demonstrated by the longest trace stack being 581, the closest the 20 iterations got to infinity.
     *  . The inverse, that a counter could perform all its steps before the other gets a chance to do anything is also true.
     *  . This can be mathematically proven:
     *  . The lowest millisecond bound is 0.0 * 1000 = 0
     *  . The highest millisecond bound is 0.1 * 1000 = 100
     *  . The random number generator simply chooses a number between these two millisecond values.
     *  . If counter A got given 100ms.
     *  . Then counter B could theoretically be given 5 steps of 1ms.
     *  . These 5ms < 100ms which means that counter B would complete all of its steps before A completes its first step.
     *  . This is comparable in probability to the infinity loop situation however, and is not achieved in the 20 iterations.
     *  . However, a close number was achieved by iteration 16, which only took 33 trace steps.
     *  .
     *  . One point of interest within this test data is in Iteration 10.
     *  . Within this iteration, the down counter reaches 0 however the up counter then steps before the down has a chance to finish its operation.
     *  . This demonstrates a consequence of concurrent programming with shared variables.
     *  . The demonstration being evident in the code that the up counter has stepped in the period between the step() and the start of the !isFinished() while loop.
     *  . While the counter was stepped to 0, it was stepped back to 1 before the isFinished() method queried it.
     *  . The consequence demonstrated is the lack of predictability in the code.
     *  . In a larger system, this could cause a fatal error.
     *  . This exact consequence could be solved with the use of critical sections, but simply serves as a warning in this piece of code.
     *  .
     *  . Another piece of information that may be considered relevant is the ratio of iterations which finished their up counter first - 14:6.
     *  . At first glance this may suggest that the up counter has a bias on it to finish first.
     *  . This is false, and is simply a consequence of the low number of iterations performed on the test.
     *  . Given a higher number of iterations, the even distribution would be observed.
     *  .
     *  . [insert second excel here]
     *  .
     *  . This test data does the same thing with the delay changed to 0.001
     *  . This means that the upper bound is now 0.001 * 1000 = 1ms.
     *  . the lower bound is unchanged, meaning the counter can either choose from 0 or 1 for the delay time.
     *  . This decreases the variance in the values for the delay and means that the probability a counter will be faster than the other is roughly 0.5
     *  .
     *  . This means that this test data is more accurate at showing the theories discussed with the previous test data.
     *  . The first one to look at will be the 'A versus B' theory where one counter steps all the way through before the other counter is able to step once.
     *  . This occurs three separate times in the 20 iterations, with all of them having the 'up' counter finish before 'down' has stepped once.
     *  . The issue might be raised, given this result, that it's impossible for counter A to step through all the way when the options are as small as 0 and 1ms.
     *  . This can be explained in terms of processor tick speed:
     *  . Counter A has had every step be given a delay time of 0ms, whereas the first step of B has been given a step time of 1ms.
     *  . Processes in each thread of a CPU are executed as fast as the tick speed of the hardware.
     *  . The delay value is here is irrelevant to that and simply emulates a latency based architecture.
     *  . So the conclusion drawn from this must be that the tick speed of the processor is high enough that a single thread can execute all 5 instructions in less than 1ms.
     *  . (This makes a lot of sense given the clock speed of modern CPUs being well in the GHz).
     *  .
     *  . The infinity theory is also very well demonstrated here - the largest number of iterations was a staggering 1153.
     *  . The even distribution is somewhat better shown here as well, with 12:8 (up:down).
     *  .
     *  . I tried an iteration of testing where I allowed the maximum bound to be 1000ms.
     *  . The first iteration of this took 30 minutes and showed exactly the same behaviour, so I won't be continuing this test run.
     */
}
