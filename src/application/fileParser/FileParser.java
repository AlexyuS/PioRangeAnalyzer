package application.fileParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import application.cards.Card;
import application.cards.CardHand;
import application.cards.Color;

public class FileParser {

	private List<StrategyHolder> strategies;
	private final File fileToRead;

	public FileParser(String file) {
		this.fileToRead = new File(file);
	}

	public List<StrategyHolder> getStrategies() throws Exception {
		this.strategies = new ArrayList<>();

		try (Scanner reader = new Scanner(this.fileToRead)) {
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
		for (int i=1;i<rawStrategy.length;i++) {
			strategies.add(new StrategyHolder(rawStrategy[i]));
		}

		return strategies;
	}

	// Hand,B 124,B 82,B 54,CHECK
	private void addStrategyForLine(List<StrategyHolder> availableStrategies, String line) throws Exception {
		Iterator<StrategyHolder> li = availableStrategies.iterator();

		String[] rawStrategyLine = line.split(",");
		CardHand cards = convertoToCardHand(rawStrategyLine[0]);
		Integer index = 1;
		while (li.hasNext()) {
			li.next().addCards(cards, Double.parseDouble(rawStrategyLine[index]));
			index++;
		}

	}

	public CardHand convertoToCardHand(String rawHand) throws Exception {
		char[] cards = rawHand.trim().toCharArray();

		if (cards.length != 4) {
			throw new IllegalArgumentException("Incorrect card format");
		}

		Card highCard = Card.createFrom("" + cards[0]);
		Color highCardColor = Color.createFrom("" + cards[1]);

		Card lowCard = Card.createFrom("" + cards[2]);
		Color lowCardColor = Color.createFrom("" + cards[3]);

		return new CardHand(highCard, highCardColor, lowCard, lowCardColor);

	}

}
