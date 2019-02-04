package ferrocarrilesDeAmericaDelSur.railways;

import ferrocarrilesDeAmericaDelSur.errors.RailwaySystemError;
import ferrocarrilesDeAmericaDelSur.errors.SetUpError;
import ferrocarrilesDeAmericaDelSur.tools.Clock;
import ferrocarrilesDeAmericaDelSur.tools.Delay;

/**
 * An implementation of a railway.  The runTrain method, should, in collaboration with Peru's runTrain(), guarantee
 * safe joint operation of the railways.
 */
public class Bolivia extends Railway {
	/**
     * Change the parameters of the Delay constructor in the call of the superconstructor to
	 * change the behaviour of this railway.
	 * @throws SetUpError if there is an error in setting up the delay.
	 */
	public Bolivia() throws SetUpError {
		super("Bolivia",new Delay(0.1,0.3));
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

			//if it's no-one's turn, it is Bolivia's turn.
			if(!getSharedBasket().hasStone(this) && !getSharedBasket().hasStone(otherRail))
			{
				getSharedBasket().putStone(this);
			}

			//Put a 'permission-request stone' in his own basket.
			this.getBasket().putStone(this);

			//if the other basket has a stone in (other rail is requesting permission)
			while(otherRail.getBasket().hasStone(otherRail))
			{
				//if it's not bolivia's turn as designated by the shared basket.
				if(getSharedBasket().hasStone(otherRail))
				{
					//Remove your 'permission-request stone' before having a siesta.
					this.getBasket().takeStone(this);

					//take a siesta.
					siesta();

					//put your 'permission-request stone' back in your own basket.
					this.getBasket().putStone(this);
				}
			}

			//cross the pass
			crossPass();


			//It is now the other rail's turn
			getSharedBasket().takeStone(this);
			getSharedBasket().putStone(otherRail);

			//remove the 'permission-request stone' from your own basket.
			this.getBasket().takeStone(this);

		}
	}
}