package model.enumeration;

/**
 * <pre> Provided enum type for Further Programming representing a side (i.e. a face) of a Coin</pre>
 * 
 * @author Caspar Ryan
 */
public enum CoinFace
{
   HEADS, TAILS;
	
	@Override
    public String toString() {
		return name().toLowerCase().substring(0, 1).toUpperCase() + name().toLowerCase().substring(1);
    }
}