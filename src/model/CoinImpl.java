/**
 * 
 */
package model;

import model.enumeration.CoinFace;
import model.interfaces.Coin;

/**
 * Coin Implementation. 
 * Holds Coin Number and Coin Face
 * and is used to flip coin.
 * @author Japan Patel
 *
 */
public class CoinImpl implements Coin {

	/**
	 * The upward/visible face of the coin 
	 */
	private CoinFace coinFace;
	
	/**
	 * Coin Number
	 */
	private int coinNumber;
	
	/**
	 * Coin Implementation Constructor.
	 * Initializes coin number and randomly sets coin face
	 */
	public CoinImpl(int coinNumber) {
		this.coinNumber = coinNumber;
		this.coinFace = getRandomCoinFace();
	}

	@Override
	public int getNumber() {
		return coinNumber;
	}

	@Override
	public CoinFace getFace() {
		return this.coinFace;
	}

	@Override
	public void flip() {
		if(this.coinFace == CoinFace.HEADS) {
			this.coinFace = CoinFace.TAILS;
		}
		else {
			this.coinFace = CoinFace.HEADS;
		}
	}

	@Override
	public boolean equals(Coin coin) {
		return this.coinFace.equals(coin.getFace());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coin) {
			equals(((Coin) obj));
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return coinFace.hashCode();
	}
	
	@Override
	public String toString() {
		return "Coin " + this.coinNumber +": " + this.coinFace.toString();
	}
	
	/**
	 * Returns a Random coin face
	 * @return CoinFace The upward/visible face of the coin (Heads/Tails)
	 */
	private CoinFace getRandomCoinFace() {
		double random = Math.random();
		if(random < 0.5) {
			return CoinFace.HEADS;
		}
		return CoinFace.TAILS;
	}

}
