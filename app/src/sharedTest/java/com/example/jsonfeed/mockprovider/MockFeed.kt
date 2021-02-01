package com.example.jsonfeed.mockprovider

fun getMockFeedValid(): String {
    return "{\n" +
            "  \"cards\": [\n" +
            "    {\n" +
            "      \"id\": \"xy7-10\",\n" +
            "      \"name\": \"Vespiquen\",\n" +
            "      \"nationalPokedexNumber\": 416,\n" +
            "      \"imageUrl\": \"https://images.pokemontcg.io/xy7/10.png\",\n" +
            "      \"imageUrlHiRes\": \"https://images.pokemontcg.io/xy7/10_hires.png\",\n" +
            "      \"types\": [\n" +
            "        \"Grass\"\n" +
            "      ],\n" +
            "      \"supertype\": \"Pokémon\",\n" +
            "      \"subtype\": \"Stage 1\",\n" +
            "      \"evolvesFrom\": \"Combee\",\n" +
            "      \"hp\": \"90\",\n" +
            "      \"number\": \"10\",\n" +
            "      \"artist\": \"kawayoo\",\n" +
            "      \"rarity\": \"Uncommon\",\n" +
            "      \"series\": \"XY\",\n" +
            "      \"set\": \"Ancient Origins\",\n" +
            "      \"setCode\": \"xy7\",\n" +
            "      \"attacks\": [\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Intelligence Gathering\",\n" +
            "          \"text\": \"You may draw cards until you have 6 cards in your hand.\",\n" +
            "          \"damage\": \"10\",\n" +
            "          \"convertedEnergyCost\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\",\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Bee Revenge\",\n" +
            "          \"text\": \"This attack does 10 more damage for each Pokémon in your discard pile.\",\n" +
            "          \"damage\": \"20+\",\n" +
            "          \"convertedEnergyCost\": 2\n" +
            "        }\n" +
            "      ],\n" +
            "      \"weaknesses\": [\n" +
            "        {\n" +
            "          \"type\": \"Fire\",\n" +
            "          \"value\": \"×2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"dp6-90\",\n" +
            "      \"name\": \"Cubone\",\n" +
            "      \"nationalPokedexNumber\": 104,\n" +
            "      \"imageUrl\": \"https://images.pokemontcg.io/dp6/90.png\",\n" +
            "      \"imageUrlHiRes\": \"https://images.pokemontcg.io/dp6/90_hires.png\",\n" +
            "      \"types\": [\n" +
            "        \"Fighting\"\n" +
            "      ],\n" +
            "      \"supertype\": \"Pokémon\",\n" +
            "      \"subtype\": \"Basic\",\n" +
            "      \"hp\": \"60\",\n" +
            "      \"retreatCost\": [\n" +
            "        \"Colorless\"\n" +
            "      ],\n" +
            "      \"convertedRetreatCost\": 1,\n" +
            "      \"number\": \"90\",\n" +
            "      \"artist\": \"Kagemaru Himeno\",\n" +
            "      \"rarity\": \"Common\",\n" +
            "      \"series\": \"Diamond & Pearl\",\n" +
            "      \"set\": \"Legends Awakened\",\n" +
            "      \"setCode\": \"dp6\",\n" +
            "      \"attacks\": [\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Headbutt\",\n" +
            "          \"text\": \"\",\n" +
            "          \"damage\": \"10\",\n" +
            "          \"convertedEnergyCost\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Fighting\",\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Bonemerang\",\n" +
            "          \"text\": \"Flip 2 coins. This attack does 20 damage times the number of heads.\",\n" +
            "          \"damage\": \"20×\",\n" +
            "          \"convertedEnergyCost\": 2\n" +
            "        }\n" +
            "      ],\n" +
            "      \"resistances\": [\n" +
            "        {\n" +
            "          \"type\": \"Lightning\",\n" +
            "          \"value\": \"-20\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"weaknesses\": [\n" +
            "        {\n" +
            "          \"type\": \"Water\",\n" +
            "          \"value\": \"+10\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}

fun getMockFeedAllIdsMissing(): String {
    return "{\n" +
            "  \"cards\": [\n" +
            "    {\n" +
//            "      \"id\": \"xy7-10\",\n" +
            "      \"name\": \"Vespiquen\",\n" +
            "      \"nationalPokedexNumber\": 416,\n" +
            "      \"imageUrl\": \"https://images.pokemontcg.io/xy7/10.png\",\n" +
            "      \"imageUrlHiRes\": \"https://images.pokemontcg.io/xy7/10_hires.png\",\n" +
            "      \"types\": [\n" +
            "        \"Grass\"\n" +
            "      ],\n" +
            "      \"supertype\": \"Pokémon\",\n" +
            "      \"subtype\": \"Stage 1\",\n" +
            "      \"evolvesFrom\": \"Combee\",\n" +
            "      \"hp\": \"90\",\n" +
            "      \"number\": \"10\",\n" +
            "      \"artist\": \"kawayoo\",\n" +
            "      \"rarity\": \"Uncommon\",\n" +
            "      \"series\": \"XY\",\n" +
            "      \"set\": \"Ancient Origins\",\n" +
            "      \"setCode\": \"xy7\",\n" +
            "      \"attacks\": [\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Intelligence Gathering\",\n" +
            "          \"text\": \"You may draw cards until you have 6 cards in your hand.\",\n" +
            "          \"damage\": \"10\",\n" +
            "          \"convertedEnergyCost\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\",\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Bee Revenge\",\n" +
            "          \"text\": \"This attack does 10 more damage for each Pokémon in your discard pile.\",\n" +
            "          \"damage\": \"20+\",\n" +
            "          \"convertedEnergyCost\": 2\n" +
            "        }\n" +
            "      ],\n" +
            "      \"weaknesses\": [\n" +
            "        {\n" +
            "          \"type\": \"Fire\",\n" +
            "          \"value\": \"×2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
//            "      \"id\": \"dp6-90\",\n" +
            "      \"name\": \"Cubone\",\n" +
            "      \"nationalPokedexNumber\": 104,\n" +
            "      \"imageUrl\": \"https://images.pokemontcg.io/dp6/90.png\",\n" +
            "      \"imageUrlHiRes\": \"https://images.pokemontcg.io/dp6/90_hires.png\",\n" +
            "      \"types\": [\n" +
            "        \"Fighting\"\n" +
            "      ],\n" +
            "      \"supertype\": \"Pokémon\",\n" +
            "      \"subtype\": \"Basic\",\n" +
            "      \"hp\": \"60\",\n" +
            "      \"retreatCost\": [\n" +
            "        \"Colorless\"\n" +
            "      ],\n" +
            "      \"convertedRetreatCost\": 1,\n" +
            "      \"number\": \"90\",\n" +
            "      \"artist\": \"Kagemaru Himeno\",\n" +
            "      \"rarity\": \"Common\",\n" +
            "      \"series\": \"Diamond & Pearl\",\n" +
            "      \"set\": \"Legends Awakened\",\n" +
            "      \"setCode\": \"dp6\",\n" +
            "      \"attacks\": [\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Headbutt\",\n" +
            "          \"text\": \"\",\n" +
            "          \"damage\": \"10\",\n" +
            "          \"convertedEnergyCost\": 1\n" +
            "        },\n" +
            "        {\n" +
            "          \"cost\": [\n" +
            "            \"Fighting\",\n" +
            "            \"Colorless\"\n" +
            "          ],\n" +
            "          \"name\": \"Bonemerang\",\n" +
            "          \"text\": \"Flip 2 coins. This attack does 20 damage times the number of heads.\",\n" +
            "          \"damage\": \"20×\",\n" +
            "          \"convertedEnergyCost\": 2\n" +
            "        }\n" +
            "      ],\n" +
            "      \"resistances\": [\n" +
            "        {\n" +
            "          \"type\": \"Lightning\",\n" +
            "          \"value\": \"-20\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"weaknesses\": [\n" +
            "        {\n" +
            "          \"type\": \"Water\",\n" +
            "          \"value\": \"+10\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}

