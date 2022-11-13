package main.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.gameDetails.card.environment.Environment;
import main.gameDetails.card.hero.Hero;
import main.gameDetails.details.CardDetails;

public class OutputCardFormat {
    public ObjectNode outputcard (CardDetails cardDetails) {
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
