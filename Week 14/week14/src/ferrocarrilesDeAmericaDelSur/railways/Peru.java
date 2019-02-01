package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Bolivia's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Peru extends Railway {
	/**
	 * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Peru() throws SetUpError {
		super("Peru",new Delay(0.1,0.3));
	}

    /**
     * Run the train on the railway.
	 * This method currently does not provide any synchronisation to avoid two trains being in the pass at the same time.
     */
    public void runTrain() throws RailwaySystemError {
    	Clock clock = getRailwaySystem().getClock();
    	while (!clock.timeOut())
		{
		    //move to start of pass.
    		choochoo();

    		//Find out what the other railway is
    		Railway otherRail = getRailwaySystem().getNextRailway(this);

            //Put a stone in his own basket.
            this.getBasket().putStone(this);

    		//if the other basket has a stone in
            while(otherRail.getBasket().hasStone(otherRail))
            {
                //Remove the stone before having a siesta.
                this.getBasket().takeStone(this);

                //take a siesta.
                siesta();

                //take the stone out immediately after.
                this.getBasket().putStone(this);
            }

            //cross the pass
            crossPass();

            //remove the stone from your own basket.
            this.getBasket().takeStone(this);

    	}
    }
}