package resourceManager;

/**
 * Implements the requestResource() and releaseResource() methods using <tt>Object</tt>s to provide monitors for 
 * each priority level.
 * 
 * @author Hugh Osborne
 * @version February 2013
 */

public class RawResourceManager extends BasicResourceManager
{
    // The queues for each priority level (using the Objects' monitors)
    private Object[] queues;
    
    // The critical sections in the resource request management methods in this code are protected by lock() and 
    // unlock() methods, to provide a functionality similar to a Lock's lock() and unlock() methods.  The busy 
    // variable is used to indicate whether these critical sections are occupied.  lock() inspects this variable 
    // to detect whether processes attempting to lock the critical sections should sleep.
    private boolean busy;

    // A boolean is also required to indicate whether the resource is in use
    private boolean resourceInUse;
    
    /**
     * Initialise the queues, and note that the critical sections are currently free.
     * @param resource the resource managed by this manager
     * @param maxUses the maximum number of uses permitted for this manager's resource.
     * The actual number of uses permitted for the resource is set to a random value in the range (0,maxUses].
     */
    public RawResourceManager(Resource resource,int maxUses) {
        super(resource,maxUses);
        busy = false;
        queues = new Object[NO_OF_PRIORITIES];
        for (int index = 0; index < NO_OF_PRIORITIES; index++) {
            queues[index] = new Object();
        }
    }
    
    /**
     * Try to lock the critical sections. A process calling this method when the critical sections are
     * occupied will have to sleep until the critical sections become available again.
     * Atomicity of this method is guaranteed by the synchronized keyword.
     * @throw ResourceError if a sleeping process is interrupted.
     */
    public synchronized void lock() throws ResourceError {
        while (busy) { // if the critical sections are occupied
            try {
                wait(); // go to sleep
            } catch (InterruptedException ie) {
                throw new ResourceError("Process waiting on monitor lock was interrupted");
            }
        }
        busy = true; // mark the critical sections as busy
    }
    
    /**
     * Unlock the critical sections and wake a sleeping process waiting to enter the critical sections, if one exists.
     * Atomicity of this method is guaranteed by the synchronized keyword.
     */
    public synchronized void unlock() {
        busy = false; // mark the critical sections as free
        notify(); // wake a sleeping process, if one exists
    }
    
    /**
     * Request use of this manager's resource, with the specified priority.
     * If the resource is in use the requesting user will have to wait for the resource to be released.
     * @param priority the priority level at which the resource is being requested.
     * @throws ResourceError if the wait on one of the priorities' monitors, or the call of lock() throws an InterruptedException error.
     */
    public void requestResource(int priority) throws ResourceError {
        lock(); // lock the critical sections
        while (resourceInUse) { // if some other resource user is using the resource
            increaseNumberWaiting(priority);
            try {
                synchronized(queues[priority]) { // obtain the priority's monitor
                    unlock(); // free the critical sections
                    queues[priority].wait(); // and sleep
                    lock(); // lock the critical sections again
                }
            } catch (InterruptedException error) {
                throw new ResourceError(getResourceName() + " was interrupted while waiting in priority " + priority + " queue - " + error.getMessage());
            }
        }
        resourceInUse = true; // lock the resource (mark it as in use)
        unlock(); // free the critical sections
    }
    
    /**
     * Release this manager's resource.  If any users are waiting for the resource a waiting user with the
     * highest priority will be woken.
     * @return the priority of the resource user woken, or NONE_WAITING if no sleeping user was found
     * @throws ResourceError if the call of lock() throws an InterruptedException error.
     */
    public int releaseResource() throws ResourceError {
        lock(); // lock the critical sections
        int highestPriorityWaiting = NONE_WAITING; // start looking for a sleeping process
        for (int index = 0; index < NO_OF_PRIORITIES; index++) { // check priorities from low to high
            if (getNumberWaiting(index) > 0) { // if there are processes waiting at this priority
                highestPriorityWaiting = index; // then these are the highest priority sleeping processes found so far
            }
        }
        if (highestPriorityWaiting != NONE_WAITING) { // if a sleeping process was found
            decreaseNumberWaiting(highestPriorityWaiting); // note that it is going to be woken
            synchronized(queues[highestPriorityWaiting]) { // obtain the priority's monitor
                queues[highestPriorityWaiting].notify(); // and wake it
            }
        }
        resourceInUse = false; // unlock the resource (mark it as not in use)
        synchronized(this) {
            unlock(); // free the critical sections
        }
        return highestPriorityWaiting; // return the priority of the resource user woken, or NONE_WAITING
    }
}
