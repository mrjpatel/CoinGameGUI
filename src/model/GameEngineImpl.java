/**
 * 
 */
package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * Game EngineImpl is used to manage players, bets, spinners
 * @author Japan Patel
 *
 */
public class GameEngineImpl implements GameEngine {
	
	/**
	 * HashMap of all players
	 */
	private Map<String, Player> playersMap = null;
	
	/**
	 * Set of all game engine callbacks
	 */
	private Set<GameEngineCallback> gameEngineCallbacks;
	
	/**
	 * Constructor
	 */
	public GameEngineImpl() {
		playersMap = new HashMap<String, Player>();
		gameEngineCallbacks = new HashSet<GameEngineCallback>();
	}

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		
		checkDelayValidity(initialDelay1, finalDelay1, delayIncrement1);
		CoinPair coinPair = new CoinPairImpl();
		int currentDelay = initialDelay1;
		
		while(currentDelay < finalDelay1) {
			flipCoins(coinPair);
			sleep(delayIncrement1);
			currentDelay += delayIncrement1;
			for(GameEngineCallback callback: gameEngineCallbacks) {
				callback.playerCoinUpdate(player, coinPair.getCoin1(), this);
				callback.playerCoinUpdate(player, coinPair.getCoin2(), this);
			}
		}
		
		for(GameEngineCallback callback: gameEngineCallbacks) {		
			callback.playerResult(player, coinPair, this);
		}
		player.setResult(coinPair);
	}
	
	
	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		checkDelayValidity(initialDelay1, finalDelay1, delayIncrement1);
		CoinPair coinPair = new CoinPairImpl();
		int currentDelay = initialDelay1;
		
		while(currentDelay < finalDelay1) {
			flipCoins(coinPair);
			sleep(delayIncrement1);
			currentDelay += delayIncrement1;
			for(GameEngineCallback callback: gameEngineCallbacks) {
				callback.spinnerCoinUpdate(coinPair.getCoin1(), this);
				callback.spinnerCoinUpdate(coinPair.getCoin2(), this);
			}
		}
		
		applyBetResults(coinPair);
		for(GameEngineCallback callback: gameEngineCallbacks) {		
			callback.spinnerResult(coinPair, this);
		}
	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		for(Player player : playersMap.values()) {
			player.getBetType().applyWinLoss(player, spinnerResult);
		}
	}

	@Override
	public void addPlayer(Player player) {
		if(player != null) {
			playersMap.put(player.getPlayerId(), player);	
		}
	}

	@Override
	public Player getPlayer(String id) {
		return playersMap.get(id);
	}

	@Override
	public boolean removePlayer(Player player) {
		if(playersMap.containsKey(player.getPlayerId())) {
			playersMap.remove(player.getPlayerId());
			return true;
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		gameEngineCallbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(gameEngineCallbacks.contains(gameEngineCallback)) {
			gameEngineCallbacks.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(playersMap.values());
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if(player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		}
		return false;
	}
	
	/**
	 * Sleeps the Thread for given time.
	 * @param millis milliseconds (ms)
	 */
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Flips Coin 1 and Coin 2
	 * @param coinPair Coin Pair instance
	 */
	private void flipCoins(CoinPair coinPair) {
		coinPair.getCoin1().flip();
		coinPair.getCoin2().flip();
	}
	
	/**
	 *  IllegalArgumentException thrown when: <UL>
    * <LI> if the delay params are < 0
    * <LI> the finalDelay < initialDelay
    * <LI> the delayIncrement > (finalDelay - initialDelay)
    * </UL>
	 * @param initialDelay Initial Delay
	 * @param finalDelay Final Delay
	 * @param delayIncrement Delay Increment
	 */
	private void checkDelayValidity(int initialDelay, int finalDelay, int delayIncrement) {
		if(initialDelay < 0 || finalDelay < 0 || delayIncrement <= 0 || finalDelay < initialDelay || 
				delayIncrement > (finalDelay - initialDelay)) {
			throw new IllegalArgumentException("Invalid delays passed for spinners");
		}
	}

}
