import Controller.AdventureController;
import Model.AdventureModel;
import View.AdventureView;
import utils.TextParser;


public class Main {
    public static void main(String[] args) {
        // Initialize the model, view, and parser
        AdventureModel model = new AdventureModel();
        AdventureView view = new AdventureView();
        TextParser parser = new TextParser(model);

        // Create the controller and pass the model, view, and parser
        AdventureController controller = new AdventureController(model, view, parser);

        // Start the game loop
        controller.run();
    }
}
