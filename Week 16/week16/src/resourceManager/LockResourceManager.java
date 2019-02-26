package resourceManager;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by u1661665(Joshua Pritchard) on 26/02/2019.
 *
 * Version: 26.02.2019
 */
public class LockResourceManager extends BasicResourceManager
{
    final Lock prioritylock;
    final Condition[] prioritiesConds = new Condition[11];


    /**
     * Set the resource and initialise the numbers of waiting processes, and the number of users, to zero.
     *
     * @param resource the resource managed by this manager
     * @param maxUses  the maximum number of uses permitted for this manager's resource.
     */
    public LockResourceManager(Resource resource, int maxUses)
    {
        super(resource, maxUses);
        prioritylock = new ReentrantLock();
        for(int x = 0; x < NO_OF_PRIORITIES; x++)
        {
            prioritiesConds[x] = prioritylock.newCondition();
        }
    }

    @Override
    public void requestResource(int priority) throws ResourceError
    {
        if(resourceIsExhausted())
        {
            throw new ResourceError("Resource is exhausted");
        }


        prioritylock.lock();
        increaseNumberWaiting(priority);

        int numberKnownUsersAbove;
        try
        {
            numberKnownUsersAbove = getKnownUsersAbove(priority);
            while (numberKnownUsersAbove > 0)
            {
                //increaseNumberWaiting(priority);
                prioritiesConds[priority].await();
                numberKnownUsersAbove = getKnownUsersAbove(priority);
                //decreaseNumberWaiting(priority);
            }

            useResource(getRandomTime());
            decreaseNumberWaiting(priority);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public int releaseResource() throws ResourceError
    {
        for(int x = 0; x < NO_OF_PRIORITIES; x++)
        {
            if(getNumberWaiting(x) > 0)
            {

                prioritiesConds[x].signal();
                prioritylock.unlock();
                return x;
            }
        }

        prioritylock.unlock();
        return NONE_WAITING;
    }

    private int getRandomTime()
    {
        Random randTime = new Random();
        return randTime.nextInt(1);
    }

    private int getKnownUsersAbove(int priority)
    {

        int total = 0;
        for(int x = 0; x < priority - 1; x++)
        {
            total += getNumberWaiting(x);
        }


        return total;
    }
}
