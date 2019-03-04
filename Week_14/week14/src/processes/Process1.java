package processes;
/**
 * One version of the process --- assign y+1 to x.
 */
public class Process1 extends Process {
    public void run() {
        //read in the variable. 1 reference (read)
        int p = y;

        //calculate and store the new variable. 1 reference (write).
        x = p + 1;
    }
}
