package blackjack;

import java.util.Collections;
import java.util.Stack;

public class CardStack {

	Stack<String> stack;

	public CardStack() {
		init();
	}

	public void init() {
		stack = new Stack<>();
		for (int i = 1; i <= 13; i++) {
			stack.add(i + ",s");
			stack.add(i + ",d");
			stack.add(i + ",c");
			stack.add(i + ",h");
		}
		shuffle();

	}

	public void shuffle() {
		Collections.shuffle(stack);

	}

	public String pop() {
		return (!stack.isEmpty()) ? stack.pop() : null;
	}
}
