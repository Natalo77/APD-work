package stringSearcher;

/**
 * Created by u1661665(Joshua Pritchard) on 12/11/2018.
 */
public class StringIndex
{
    private String string;
    private String subString;
    private int index;


    public StringIndex(String string, String subString, int index)
    {
        this.string = string;
        this.subString = subString;
        this.index = index;
    }

    public String getString()
    {
        return this.string;
    }

    public String getSubString()
    {
        return this.subString;
    }

    public int getIndex()
    {
        return this.index;
    }
}
