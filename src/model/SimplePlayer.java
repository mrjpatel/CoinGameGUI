/**
 * 
 */
package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

/**
 * Simple Player Implementation.
 * Holds Player information.
 * 
 * @author Japan Patel
 */
public class SimplePlayer implements Player {

	/**
	 * Player's Name
	 */
	private String playerName;
	
	/**
	 * Player's Id
	 */
	private String playerId;
	
	/**
	 * Player's Points
	 */
	private int playerPoints;
	
	/**
	 * Player's Bet Type
	 */
	private BetType betType;
	
	/**
	 * Player's bet amount
	 */
	private int bet;

	/**
	 * Player's Coin Pair (Coin1 and Coin2)
	 */
	private CoinPair coinPair;
	
	/**
	 * Constructor, Initializes Simple Player
	 * @param playerId Player ID
	 * @param playerName Player Name
	 * @param initialPoints Initial Points 
	 */
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		if (playerId == null || playerName == null) {
			throw new IllegalArgumentException("Invalid playerID or Player name passed when creating players");
		}
		
		this.playerId = playerId;
		this.playerName = playerName;
		this.playerPoints = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return this.playerPoints;
	}

	@Override
	public void setPoints(int points) {
		this.playerPoints = points;
	}

	@Override
	public String getPlayerId() {
		return this.playerId;
	}

	@Override
	public boolean setBet(int bet) {
		if(bet > 0 && this.playerPoints >= bet) {
			this.bet = bet;
			return true;
		}
		resetBet();
		return false;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	@Override
	public BetType getBetType() {
		return this.betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
		this.setBetType(BetType.NO_BET);
	}

	@Override
	public CoinPair getResult() {
		return this.coinPair;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		this.coinPair = coinPair;
	}
	
	@Override
	public String toString() {
		return "Player: id=" + this.playerId + ", name=" + this.playerName + ", bet=" + this.bet +
				", betType= " + this.betType + ", points=" + this.playerPoints + ", RESULT .. " +
				coinPair.toString();
	}

}
