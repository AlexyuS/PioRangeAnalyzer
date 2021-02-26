package main.application.fileParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import main.application.cards.Card;
import main.application.cards.IndividualCardHand;
import main.application.cards.CardColor;
import main.application.cards.IndividualCardStrategy;
import main.application.exception.InvalidFileFormat;
import main.application.strategy.StrategyHolder;

@Component
public class StrategyReader {
	private final static Logger LOGGER = Logger.getLogger(StrategyReader.class.getName());

	private List<StrategyHolder> strategies;
	private final StrategyHolder parentStrategy;
	Supplier<List<IndividualCardStrategy>> supplier = () -> {
		return new ArrayList<>();
	};

	BiConsumer<List<IndividualCardStrategy>, List<IndividualCardStrategy>> consumer = (sourceList, resultList) -> {
		if (sourceList != null) {
			sourceList.addAll(resultList);
		}
	};

	/**
	 * Used only for multi-threading
	 */
	BiConsumer<List<IndividualCardStrategy>, List<IndividualCardStrategy>> reducer = (sourceList, resultList) -> {

	};
	
	public StrategyReader(StrategyHolder parent) {
		this.parentStrategy = parent;
	}
	
	public StrategyReader() {
		this.parentStrategy=null;
	}

	public List<StrategyHolder> getStrategies(String content) throws Exception {
		this.strategies = new ArrayList<>();

		try (Scanner reader = new Scanner(content)) {
			if (!reader.hasNextLine()) {
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

	public StrategyHolder parsePreflopStrategy(String content) {
		StrategyHolder preflopStrategy = new StrategyHolder("Preflop",null);
		List<String> splittedHands = Arrays.asList(content.split(","));

		List<IndividualCardStrategy> individualCardStrategy = splittedHands.stream().map(e -> {
			try {
				return stringToCardMapper(e);
			} catch (InvalidFileFormat e1) {
				LOGGER.warning("Invalid file format found. Parsing has stopped");
				return null;
			}
		}).collect(supplier, consumer, reducer);
		preflopStrategy.setIndividualCards(individualCardStrategy);

		return preflopStrategy;
	}

	private List<IndividualCardStrategy> stringToCardMapper(String rawString) throws InvalidFileFormat {
		String[] cardAndOccurance = rawString.split(":");

		if (cardAndOccurance.length == 1) {
			return convertRawAggregatedHandsIntoCards(cardAndOccurance[0], 100);
		} else if (cardAndOccurance.length == 2) {
			return convertRawAggregatedHandsIntoCards(cardAndOccurance[0],
					Double.parseDouble(cardAndOccurance[1])*100);
		} else {
			throw new InvalidFileFormat(
					"Invalid file format. Found raw string: " + rawString + " when mapping string to card");
		}
	}

	private List<IndividualCardStrategy> convertRawAggregatedHandsIntoCards(String rawCardString,
			double occurancePercentage) {

		rawCardString = rawCardString.trim();

		char[] characters = rawCardString.toCharArray();

		try {
			if (characters.length == 3) {
				return handleOnlySuitedOrOffsuitedGroup(occurancePercentage, characters);
			} else if (characters.length == 2 && characters[0] == characters[1]) {
				return handleOnlyPocketPairGroup(occurancePercentage, characters);
			} else if (characters.length == 2 && characters[0] != characters[1]) {
				return handleSuitedAndOffsuitedGroup(occurancePercentage, characters);
			} else if (characters.length == 4) {
				return handleSingleCard(occurancePercentage, characters);
			} else {
				throw new InvalidFileFormat(
						"Invalid file format. Found raw card string:" + rawCardString + " during file parsing");
			}

		} catch (Exception e) {
			LOGGER.warning("Error while parsing the preflop file" + e.getMessage());
			return null;
		}
	}

	private List<IndividualCardStrategy> handleSingleCard(double occurancePercentage, char[] characters) throws Exception {
		Card hc = Card.createFrom(characters[0]);
		Card lc = Card.createFrom(characters[2]);

		CardColor hcColor = CardColor.createFrom(characters[1]);
		CardColor lcColor = CardColor.createFrom(characters[3]);
		return Arrays.asList(createIndividualCardStrategy(hc, hcColor, lc, lcColor, occurancePercentage));
	}

	private List<IndividualCardStrategy> handleSuitedAndOffsuitedGroup(double occurancePercentage, char[] characters) throws Exception {
		Card hc = Card.createFrom(characters[0]);
		Card lc = Card.createFrom(characters[1]);

		List<IndividualCardStrategy> suitedCards = createSuitedBundleTypeCards(hc, lc, occurancePercentage);
		List<IndividualCardStrategy> offsuitedCards = createOffSuitedBundleTypeCards(hc, lc,
				occurancePercentage);

		suitedCards.addAll(offsuitedCards);
		return suitedCards;
	}

	private List<IndividualCardStrategy> handleOnlyPocketPairGroup(double occurancePercentage, char[] characters) throws Exception {
		Card pair = Card.createFrom(characters[0]);
		return createPairBundleTypeCards(pair, occurancePercentage);
	}

	private List<IndividualCardStrategy> handleOnlySuitedOrOffsuitedGroup(double occurancePercentage, char[] characters) throws Exception {
		Card highCard = Card.createFrom(characters[0]);
		Card lowCard = Card.createFrom(characters[1]);

		if (characters[2] == 's') {
			return createSuitedBundleTypeCards(highCard, lowCard, occurancePercentage);
		} else {
			return createOffSuitedBundleTypeCards(highCard, lowCard, occurancePercentage);
		}
	}

	private List<IndividualCardStrategy> createSuitedBundleTypeCards(Card highCard, Card lowCard, double occurance) {
		List<IndividualCardStrategy> individualSuitedBundle = new ArrayList<>();

		IndividualCardHand diamonds = new IndividualCardHand(highCard, CardColor.DIAMONDS, lowCard, CardColor.DIAMONDS);
		IndividualCardHand hearts = new IndividualCardHand(highCard, CardColor.HEARTS, lowCard, CardColor.HEARTS);
		IndividualCardHand spades = new IndividualCardHand(highCard, CardColor.SPADES, lowCard, CardColor.SPADES);
		IndividualCardHand clubs = new IndividualCardHand(highCard, CardColor.CLUBS, lowCard, CardColor.CLUBS);

		IndividualCardStrategy diamondsPair = new IndividualCardStrategy(diamonds, occurance);
		IndividualCardStrategy heartsPair = new IndividualCardStrategy(hearts, occurance);
		IndividualCardStrategy spadesPair = new IndividualCardStrategy(spades, occurance);
		IndividualCardStrategy clubsPair = new IndividualCardStrategy(clubs, occurance);

		individualSuitedBundle.add(diamondsPair);
		individualSuitedBundle.add(heartsPair);
		individualSuitedBundle.add(spadesPair);
		individualSuitedBundle.add(clubsPair);

		return individualSuitedBundle;

	}

	private List<IndividualCardStrategy> createOffSuitedBundleTypeCards(Card highCard, Card lowCard, double occurance) {
		List<IndividualCardStrategy> individualSuitedBundle = new ArrayList<>();

		IndividualCardHand ds = new IndividualCardHand(highCard, CardColor.DIAMONDS, lowCard, CardColor.SPADES);
		IndividualCardHand dh = new IndividualCardHand(highCard, CardColor.DIAMONDS, lowCard, CardColor.HEARTS);
		IndividualCardHand dc = new IndividualCardHand(highCard, CardColor.DIAMONDS, lowCard, CardColor.CLUBS);

		IndividualCardHand sh = new IndividualCardHand(highCard, CardColor.SPADES, lowCard, CardColor.HEARTS);
		IndividualCardHand sd = new IndividualCardHand(highCard, CardColor.SPADES, lowCard, CardColor.DIAMONDS);
		IndividualCardHand sc = new IndividualCardHand(highCard, CardColor.SPADES, lowCard, CardColor.CLUBS);

		IndividualCardHand ch = new IndividualCardHand(highCard, CardColor.CLUBS, lowCard, CardColor.HEARTS);
		IndividualCardHand cs = new IndividualCardHand(highCard, CardColor.CLUBS, lowCard, CardColor.SPADES);
		IndividualCardHand cd = new IndividualCardHand(highCard, CardColor.CLUBS, lowCard, CardColor.DIAMONDS);

		IndividualCardHand hs = new IndividualCardHand(highCard, CardColor.HEARTS, lowCard, CardColor.SPADES);
		IndividualCardHand hd = new IndividualCardHand(highCard, CardColor.HEARTS, lowCard, CardColor.DIAMONDS);
		IndividualCardHand hc = new IndividualCardHand(highCard, CardColor.HEARTS, lowCard, CardColor.CLUBS);

		IndividualCardStrategy dsPair = new IndividualCardStrategy(ds, occurance);
		IndividualCardStrategy dhPair = new IndividualCardStrategy(dh, occurance);
		IndividualCardStrategy dcPair = new IndividualCardStrategy(dc, occurance);

		IndividualCardStrategy shPair = new IndividualCardStrategy(sh, occurance);
		IndividualCardStrategy sdPair = new IndividualCardStrategy(sd, occurance);
		IndividualCardStrategy scPair = new IndividualCardStrategy(sc, occurance);

		IndividualCardStrategy chPair = new IndividualCardStrategy(ch, occurance);
		IndividualCardStrategy csPair = new IndividualCardStrategy(cs, occurance);
		IndividualCardStrategy cdPair = new IndividualCardStrategy(cd, occurance);

		IndividualCardStrategy hsPair = new IndividualCardStrategy(hs, occurance);
		IndividualCardStrategy hdPair = new IndividualCardStrategy(hd, occurance);
		IndividualCardStrategy hcPair = new IndividualCardStrategy(hc, occurance);

		individualSuitedBundle.add(dsPair);
		individualSuitedBundle.add(dhPair);
		individualSuitedBundle.add(dcPair);
		individualSuitedBundle.add(shPair);
		individualSuitedBundle.add(sdPair);
		individualSuitedBundle.add(scPair);
		individualSuitedBundle.add(chPair);
		individualSuitedBundle.add(csPair);
		individualSuitedBundle.add(cdPair);
		individualSuitedBundle.add(hsPair);
		individualSuitedBundle.add(hdPair);
		individualSuitedBundle.add(hcPair);

		return individualSuitedBundle;

	}

	private List<IndividualCardStrategy> createPairBundleTypeCards(Card pair, double occurance) {
		List<IndividualCardStrategy> individualPairBundle = new ArrayList<>();
		IndividualCardHand cs = new IndividualCardHand(pair, CardColor.SPADES, pair, CardColor.CLUBS);
		IndividualCardHand ds = new IndividualCardHand(pair, CardColor.SPADES, pair, CardColor.DIAMONDS);
		IndividualCardHand sh = new IndividualCardHand(pair, CardColor.SPADES, pair, CardColor.HEARTS);

		IndividualCardHand ch = new IndividualCardHand(pair, CardColor.HEARTS, pair, CardColor.CLUBS);
		IndividualCardHand dh = new IndividualCardHand(pair, CardColor.HEARTS, pair, CardColor.DIAMONDS);
		
		IndividualCardHand cd = new IndividualCardHand(pair, CardColor.DIAMONDS, pair, CardColor.CLUBS);


		IndividualCardStrategy cdCard = new IndividualCardStrategy(cd, occurance);
		IndividualCardStrategy csCard = new IndividualCardStrategy(cs, occurance);
		IndividualCardStrategy chCard = new IndividualCardStrategy(ch, occurance);
		IndividualCardStrategy dsCard = new IndividualCardStrategy(ds, occurance);
		IndividualCardStrategy dhCard = new IndividualCardStrategy(dh, occurance);
		IndividualCardStrategy shCard = new IndividualCardStrategy(sh, occurance);

		individualPairBundle.add(cdCard);
		individualPairBundle.add(csCard);
		individualPairBundle.add(chCard);
		individualPairBundle.add(dsCard);
		individualPairBundle.add(dhCard);
		individualPairBundle.add(shCard);

		return individualPairBundle;
	}

	private IndividualCardStrategy createIndividualCardStrategy(Card highCard, CardColor hcColor, Card lowCard,
			CardColor lcColor, double occurance) {
		IndividualCardHand cardhand = new IndividualCardHand(highCard, hcColor, lowCard, lcColor);
		
		return new IndividualCardStrategy(cardhand, occurance);
	}

	private List<StrategyHolder> determinateAvailableStrategies(String line) {
		List<StrategyHolder> strategies = new ArrayList<StrategyHolder>();

		String[] rawStrategy = line.trim().split(",");
		for (int i = 1; i < rawStrategy.length; i++) {
			strategies.add(new StrategyHolder(rawStrategy[i],parentStrategy));
		}

		return strategies;
	}

	// Hand,B 124,B 82,B 54,CHECK
	private void addStrategyForLine(List<StrategyHolder> availableStrategies, String line) throws Exception {
		Iterator<StrategyHolder> strategiesIterator = availableStrategies.iterator();

		String[] rawStrategyLine = line.split(",");
		IndividualCardHand cards = convertTOCardHand(rawStrategyLine[0]);
		Integer index = 1;

		while (strategiesIterator.hasNext()) {
			IndividualCardStrategy suitedBundle = wrapInSuitedBundle(cards, rawStrategyLine[index]);
			strategiesIterator.next().addCards(suitedBundle);
			index++;
		}

	}

	private IndividualCardHand convertTOCardHand(String rawHand) throws Exception {
		char[] cards = rawHand.trim().toCharArray();

		if (cards.length != 4) {
			throw new IllegalArgumentException("Incorrect card format");
		}

		Card highCard = Card.createFrom(cards[0]);
		CardColor highCardColor = CardColor.createFrom(cards[1]);

		Card lowCard = Card.createFrom(cards[2]);
		CardColor lowCardColor = CardColor.createFrom(cards[3]);

		return new IndividualCardHand(highCard, highCardColor, lowCard, lowCardColor);

	}

	private IndividualCardStrategy wrapInSuitedBundle(IndividualCardHand card, String occurance) {
		return new IndividualCardStrategy(card, Double.parseDouble(occurance));
	}

}
