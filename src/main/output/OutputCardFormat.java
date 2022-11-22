package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.details.CardDetails;

/**
 * Class that writes in the json format for the output
 */
public class OutputCardFormat {
    /**
     * @param cardDetails the card we want to print
     * @return the format of the card
     */
    public ObjectNode outputCard(final CardDetails cardDetails) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode card = mapper.createObjectNode();

        card.put("mana", cardDetails.getMana());

        Environment env = new Environment();
        Hero hero = new Hero();

        if (!env.getEnvironmentCards().contains(cardDetails.getName())) {
            if (hero.getHeroCards().contains(cardDetails.getName())) {
                card.put("health", cardDetails.getHealth());
            } else {
                card.put("attackDamage", cardDetails.getAttackDamage());
                card.put("health", cardDetails.getHealth());
            }
        }

        card.put("description", cardDetails.getDescription());

        ArrayNode colors = mapper.createArrayNode();

        for (String color : cardDetails.getColors()) {
            colors.add(color);
        }

        card.set("colors", colors);
        card.put("name", cardDetails.getName());

        return card;
    }
}
