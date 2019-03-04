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
    private boolean inUse;


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
        inUse = false;
    }

    @Override
    public void requestResource(int priority) throws ResourceError
    {
        if(resourceIsExhausted())
        {
            throw new ResourceError("Resource is exhausted");
        }


        prioritylock.lock();

        try
        {
            while (inUse)
            {
                increaseNumberWaiting(priority);
                prioritiesConds[priority].await();
            }

            inUse = true;
            useResource(getRandomTime());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            prioritylock.unlock();
        }



    }

    @Override
    public int releaseResource() throws ResourceError
    {
        prioritylock.lock();
        int returnInt = NONE_WAITING;
        try
        {
            for (int x = 10; x >= 0; x--)
            {
                if (getNumberWaiting(x) > 0)
                {

                    prioritiesConds[x].signal();
                    decreaseNumberWaiting(x);
                    break;
                }
            }
        }
        finally
        {
            prioritylock.unlock();
            inUse = false;

        }

        return returnInt;

    }

    private int getRandomTime()
    {
        Random randTime = new Random();
        return randTime.nextInt(1);
    }
}
