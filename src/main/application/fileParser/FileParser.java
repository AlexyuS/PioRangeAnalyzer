package main.application.fileParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import main.application.cards.Card;
import main.application.cards.CardHand;
import main.application.cards.CardColor;
import main.application.cards.CardStrategy;
import main.application.strategy.StrategyHolder;

@Component
public class FileParser {

	private List<StrategyHolder> strategies;

	public List<StrategyHolder> getStrategies(String content) throws Exception {
		this.strategies = new ArrayList<>();

		try (Scanner reader = new Scanner(content)) {
			if(!reader.hasNextLine()) {
				throw new Exception("File is empty");
			}
			
			this.strategies = determinateAvailableStrategies(reader.nextLine());
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				addStrategyForLine(strategies, line);
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		}
		return this.strategies;
	}

	private List<StrategyHolder> determinateAvailableStrategies(String line) {
		List<StrategyHolder> strategies = new ArrayList<StrategyHolder>();

		
		String[] rawStrategy = line.trim().split(",");
		for (int i=0;i<rawStrategy.length;i++) {
			strategies.add(new StrategyHolder(rawStrategy[i]));
		}

		return strategies;
	}

	// Hand,B 124,B 82,B 54,CHECK
	private void addStrategyForLine(List<StrategyHolder> availableStrategies, String line) throws Exception {
		Iterator<StrategyHolder> strategiesIterator = availableStrategies.iterator();

		String[] rawStrategyLine = line.split(",");
		CardHand cards = convertoToCardHand(rawStrategyLine[0]);
		Integer index = 1;
		
		while (strategiesIterator.hasNext()) {
			CardStrategy suitedBundle = wrapInSuitedBundle(cards,rawStrategyLine[index]);
			strategiesIterator.next().addCards(suitedBundle);
		}

	}

	private CardHand convertoToCardHand(String rawHand) throws Exception {
		char[] cards = rawHand.trim().toCharArray();

		if (cards.length != 4) {
			throw new IllegalArgumentException("Incorrect card format");
		}

		Card highCard = Card.createFrom("" + cards[0]);
		CardColor highCardColor = CardColor.createFrom("" + cards[1]);

		Card lowCard = Card.createFrom("" + cards[2]);
		CardColor lowCardColor = CardColor.createFrom("" + cards[3]);

		return new CardHand(highCard, highCardColor, lowCard, lowCardColor);

	}
	
	private CardStrategy wrapInSuitedBundle(CardHand card,String occurance) {
		return new CardStrategy(card, Double.parseDouble(occurance));
	}
	
	

}
