package Model;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AdventureModel {
    private Set<String> verbs;
    private Set<String> nouns;
    private Map<String, String> responses;
    private boolean hasKey;
    private boolean escaped;

    public AdventureModel() {
        // Define available verbs
        verbs = new HashSet<>();
        verbs.add("grab");
        verbs.add("look");
        verbs.add("use");
        verbs.add("search");
        verbs.add("unlock");
        verbs.add("check");

        // Define available nouns (locations to search)
        nouns = new HashSet<>();
        nouns.add("key");
        nouns.add("bag");
        nouns.add("door");
        nouns.add("coat");
        nouns.add("flower pot");
        nouns.add("table");
        nouns.add("bed");
        nouns.add("cabinet");
        nouns.add("kitchen counter");
        nouns.add("couch");
        nouns.add("chair");
        nouns.add("wardrobe");

        // Define responses for actions in the room
        responses = new HashMap<>();
        responses.put("look room", "The room is on fire! You need to escape. There's smoke and heat everywhere.");
        responses.put("search bag", "You frantically search the bag... you find the key!");
        responses.put("use key", "You use the key on the door. It unlocks, and you escape the room!");
        responses.put("look door", "The door is locked. You need to find the key to escape.");

        // Add responses for additional search locations
        responses.put("search coat", "You check the coat pockets, but there's nothing useful inside.");
        responses.put("search flower pot", "You look under the flower pot, but find only dirt.");
        responses.put("search table", "The table is cluttered with papers, but there's nothing useful.");
        responses.put("search bed", "You look under the bed but only find dust bunnies.");
        responses.put("search cabinet", "You open the cabinet, but it's empty.");
        responses.put("search kitchen counter", "You search the kitchen counter but only find some utensils.");
        responses.put("search couch", "You check the couch cushions but only find some loose change.");
        responses.put("search chair", "You search the chair but there's nothing unusual.");
        responses.put("search pocket", "You put your hand in your pocket but its empty.");
        responses.put("search wardrobe", "You search the wardrobe but its empty.");
        responses.put("check flower pot", "You look under the flower pot, but find only dirt.");
        responses.put("check table", "The table is cluttered with papers, but there's nothing useful.");
        responses.put("check bed", "You look under the bed but only find dust bunnies.");
        responses.put("check cabinet", "You open the cabinet, but it's empty.");
        responses.put("check kitchen counter", "You search the kitchen counter but only find some utensils.");
        responses.put("check couch", "You check the couch cushions but only find some loose change.");
        responses.put("check chair", "You search the chair but there's nothing unusual.");
        responses.put("check pocket", "You put your hand in your pocket but its empty.");

        responses.put("key error", "You need to find the key first before you open the door.");



        hasKey = false;
        escaped = false;
    }

    public boolean isVerb(String word) {
        return verbs.contains(word);
    }

    public boolean isNoun(String word) {
        return nouns.contains(word);
    }

    public String getResponses(String action) {
        // Check if the player is searching the bag to find the key
        if (action.equals("search bag") || action.equals("check bag") ){
            hasKey = true;
            return responses.get("search bag");
        }

        // check if the player want to use the key but doesn't have it yet
        if(action.equals("use key") & !hasKey) return responses.get("key error");

        // Check if the player is using the key on the door to escape
        if (action.equals("use key")) {
            escaped = true;
            return responses.get("use key");
        }



        // Return the appropriate response for other locations
        return responses.getOrDefault(action, "You can't do that right now.");
    }

    public boolean hasEscaped() {
        return escaped;
    }
}
