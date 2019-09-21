package model.enumeration;

import model.interfaces.CoinPair;
import model.interfaces.Player;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */
public enum BetType {
	COIN1 {
		
		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult) {
			int playerCurrentPoints = player.getPoints();
			int playerNewPoints = 0;
			
			if(player.getResult().getCoin1().equals(spinnerResult.getCoin1())) {
				playerNewPoints = playerCurrentPoints + player.getBet();
			}
			else {
				playerNewPoints = playerCurrentPoints - player.getBet();
			}
			player.setPoints(playerNewPoints);
		}
	},
	COIN2 {

		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult) {
			int playerCurrentPoints = player.getPoints();
			int playerNewPoints = 0;
			
			if(player.getResult().getCoin2().equals(spinnerResult.getCoin2())) {
				playerNewPoints = playerCurrentPoints + player.getBet();
			}
			else {
				playerNewPoints = playerCurrentPoints - player.getBet();
			}
			player.setPoints(playerNewPoints);
		}
	},
	BOTH {

		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult) {
			int playerCurrentPoints = player.getPoints();
			int playerNewPoints = 0;
			
			if(player.getResult().getCoin1().equals(spinnerResult.getCoin1()) &&
					player.getResult().getCoin2().equals(spinnerResult.getCoin2())) {
				playerNewPoints = playerCurrentPoints + (player.getBet() * 2);
			}
			else {
				playerNewPoints = playerCurrentPoints - player.getBet();
			}
			player.setPoints(playerNewPoints);
		}
	},
	NO_BET {

		@Override
		public void applyWinLoss(Player player, CoinPair spinnerResult) {
			// no implementation required
		}
	};

	/**
	 * This method is to be overridden for each bet type<br>
	 * see assignment specification for betting odds and how win/loss is applied
	 * 
	 * @param player        - the player to apply the win/loss points balance
	 *                      adjustment
	 * @param spinnerResult - the CoinPair result of the spinner to compare to
	 */
	public abstract void applyWinLoss(Player player, CoinPair spinnerResult);
}