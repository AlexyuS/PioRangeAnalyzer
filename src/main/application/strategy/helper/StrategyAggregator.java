package main.application.strategy.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.application.cards.AggregatedCardStrategy;
import main.application.cards.CardFactory;
import main.application.cards.CardHand;
import main.application.cards.IndividualCardHand;
import main.application.cards.IndividualCardStrategy;
import main.application.exception.InvalidCardHandFormat;

public class StrategyAggregator {
	private final static Logger LOGGER = Logger.getLogger(StrategyAggregator.class.getName());

	public static final List<AggregatedCardStrategy> aggregateIndividualCards(List<IndividualCardStrategy> cardStrategy){
		List<AggregatedCardStrategy> aggregatedCards = new ArrayList<>();

			for (IndividualCardStrategy individualCardStrategy : cardStrategy) {
				CardHand cardHand = createAggregatedCardHand(individualCardStrategy.getCardHand());
				
				AggregatedCardStrategy aggregatedCard = new AggregatedCardStrategy(cardHand);
				int index = aggregatedCards.indexOf(aggregatedCard);
				if (index == -1) {
					LOGGER.info("New card hand " + cardHand + " is created in the list for strategy" + "");
					LOGGER.info("--------with occurance :" + individualCardStrategy.getOccurancePercentage());
					LOGGER.info("--------and absolute occurance:" + individualCardStrategy.getAbsoluteOccurance());
					aggregatedCard.setOccuranceAbsolute(individualCardStrategy.getAbsoluteOccurance());
					aggregatedCard.setOccurancePercentage(individualCardStrategy.getOccurancePercentage());
					aggregatedCards.add(aggregatedCard);
				} else {
					LOGGER.info("New card hand" + cardHand + " is aggregated in the list for strategy " + "");
					LOGGER.info("--------with occurance :" + individualCardStrategy.getOccurancePercentage());
					LOGGER.info("--------and absolute occurance:" + individualCardStrategy.getAbsoluteOccurance());
					AggregatedCardStrategy existingAggregatedCardStrategy = aggregatedCards.get(index);
					existingAggregatedCardStrategy.increaseOccuranceAbsolute(individualCardStrategy.getAbsoluteOccurance());
					existingAggregatedCardStrategy.increaseOccurancePercentage(individualCardStrategy.getOccurancePercentage());
				}
			}
			aggregatedCards.forEach(e->e.settleUpStrategy());
		return aggregatedCards;
	}
	
	private static CardHand createAggregatedCardHand(IndividualCardHand individualCardHand) {
		CardFactory cardFactory = new CardFactory();
		if(individualCardHand.getHighCard()==individualCardHand.getLowCard()) {
			return  cardFactory.pairedFrom(individualCardHand);
		}
		if(individualCardHand.getHighCardColor()!=individualCardHand.getLowCardColor()) {
			return cardFactory.offsuitedFrom(individualCardHand);
		}
		if(individualCardHand.getHighCardColor()==individualCardHand.getLowCardColor()) {
			return cardFactory.suitedFrom(individualCardHand);
		}
		
		throw new InvalidCardHandFormat("Invalid card hand format "+individualCardHand);
	}
}
