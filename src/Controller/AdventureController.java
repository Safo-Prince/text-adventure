package Controller;

import Model.AdventureModel;
import View.AdventureView;
import utils.TextParser;

import java.util.Scanner;

public class AdventureController {
    private AdventureModel model;
    private AdventureView view;
    private TextParser parser;

    public AdventureController(AdventureModel model, AdventureView view, TextParser parser) {
        this.model = model;
        this.view = view;
        this.parser = parser;
    }

    // Handle user input
    public void handleInput(String userInput) {
        String response = parser.parseSentence(userInput);
        view.showOutput(response);
    }

    // Main game loop
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The room is on fire! You must find the key and escape before it's too late.");

        while (!model.hasEscaped()) {
            System.out.println("Enter a message describing your action:");
            String userInput = scanner.nextLine();  // Read user input

            // Handle input and display the response
            handleInput(userInput);
        }

        System.out.println("Congratulations! You've escaped the room!");
    }
}
