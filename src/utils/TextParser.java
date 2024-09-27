package utils;

import Model.AdventureModel;
import java.util.ArrayList;
import java.util.List;

public class TextParser {
    private AdventureModel model;

    public TextParser(AdventureModel model) {
        this.model = model;
    }

    // Convert input string to a 2D char array
    public char[][] convertTo2DArray(String input) {
        int length = input.length();
        char[][] array = new char[1][input.length()];
        for (int i = 0; i < length; i++) {
            array[0][i] = input.charAt(i);
        }
        return array;
    }

    // Extract words from the char array
    public List<String> extraWordsFromArray(char[][] array) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        for (int j = 0; j < array[0].length; j++) {
            char c = array[0][j];

            if (c == ' ' || j == array[0].length - 1) {
                if ((j == array[0].length - 1) && c != ' ') {
                    currentWord.append(c);
                }
                if (!currentWord.isEmpty()) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            } else {
                currentWord.append(c);
            }
        }
        return words;
    }

    // Parse the input sentence and get an appropriate response
    public String parseSentence(String sentence) {
        char[][] array = convertTo2DArray(sentence);
        List<String> words = extraWordsFromArray(array);

        if (words.size() < 2) {
            return "I couldn't understand that. Please try a different command.";
        }

        String verb = null;
        String noun = null;

        // Identify the verb and noun in the sentence
        for (String word : words) {
            if (model.isVerb(word)) {
                verb = word;
            } else if (model.isNoun(word)) {
                noun = word;
            }
        }

        // Construct the action with a space between verb and noun, like "search bag"
        if (verb != null && noun != null) {
            String action = verb + " " + noun;
            return model.getResponses(action);
        } else {
            return "I couldn't understand that. Please try a different command.";
        }
    }
}
