package processes;
/**
 * One version of the process --- assign x+1 to y.
 */
public class Process2 extends Process {
    public void run()
    {
        //read in the variable. 1 reference (read)
        int p = x;

        //calculate and store the new variable. 1 reference (write).
        y = p + 1;
    }
  }
