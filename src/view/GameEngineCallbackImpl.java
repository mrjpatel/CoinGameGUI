package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Implementation of GameEngineCallback 
 * showing Java logging behaviour
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	
	private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

	public GameEngineCallbackImpl() {
		// NOTE need to also set the console to FINE in
		// %JRE_HOME%\lib\logging.properties
		logger.setLevel(Level.FINE);
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		logger.log(Level.FINE, player.getPlayerName() + " coin " + coin.getNumber() + 
				" flipped to " + coin.getFace().toString());
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		logger.log(Level.INFO, player.getPlayerName() + ", final result=" + coinPair.toString());
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		logger.log(Level.FINE, "Spinner coin " + coin.getNumber() + " flipped to " + coin.getFace());
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		logger.log(Level.INFO, "Spinner, final result=" + coinPair.toString());
		
		StringBuilder playerResults = new StringBuilder();
		for(Player player : engine.getAllPlayers()) {
			playerResults.append("\n" + player.toString());
		}
		logger.log(Level.INFO, "Final Player Results" + playerResults.toString());
	}

}
