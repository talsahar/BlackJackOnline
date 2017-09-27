package blackjack;

import java.util.LinkedList;

public class Hand {
	private LinkedList<String> hand;
	private boolean isStand;

	public Hand() {
		hand = new LinkedList<>();
	}

	public void add(String... s) {
		for (String tmp : s)
			hand.add(tmp);

	}

	public LinkedList<String> getHand() {
		return hand;
	}

	public boolean isLegal() {
		return (getSum() <= 21) ? true : false;

	}

	public int getSum() {
		int sum = 0;
		for (String s : hand)
			sum += Integer.parseInt(s.split(",")[0]);

		return sum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		hand.forEach(c -> builder.append(c).append(":"));
		return builder.toString();
	}

	public String unknownToString() {
		StringBuilder builder = new StringBuilder(hand.get(0));
		for (int i = 0; i < hand.size() - 1; i++)
			builder.append(":unknown");
		return builder.toString();
	}

	public void clear() {
		hand.clear();
	}

	public boolean isStand() {
		return isStand;
	}

	public void setStand(boolean isStand) {
		this.isStand = isStand;
	}

}
