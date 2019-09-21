/**
 * 
 */
package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

/**
 * Coin Pair Class holds Coin 1 and Coin 2 information.
 * @author Japan Patel
 *
 */
public class CoinPairImpl implements CoinPair {

	/**
	 * Coin 1
	 */
	private Coin coin1;
	
	/**
	 * Coin 2
	 */
	private Coin coin2;
	
	/**
	 * Coin 1 number
	 */
	private int coin1Number = 1;
	
	/**
	 * Coin 2 number
	 */
	private int coin2Number = 2;
	
	/**
	 * Coin Pair Implementation Constructor.
	 * Initializes Coin1 and Coin2
	 */
	public CoinPairImpl() {
		coin1 = new CoinImpl(coin1Number);
		coin2 = new CoinImpl(coin2Number);
	}

	@Override
	public Coin getCoin1() {
		 return coin1;
	}

	@Override
	public Coin getCoin2() {
		 return coin2;
	}
	
	@Override
   public String toString() {
		return coin1.toString() + ", " + coin2.toString();
	}

	@Override
	public boolean equals(CoinPair coinPair) {
		if(this.coin1.equals(coinPair.getCoin1()) && 
				this.coin2.equals(coinPair.getCoin2())) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CoinPair) {
			equals(((CoinPair)obj));
		}
		return false;
	}

   @Override
   public int hashCode() {
	   return coin1.hashCode() + coin2.hashCode();
   }

}
