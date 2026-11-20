import javafx.application.Application;
//import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
public class MainClass extends Application
{
    @Override
    public void start(Stage YahtzeeStage)
    {
        TextField Field1 = new TextField();
        VBox vbox = new VBox(10, Field1);
        Scene scene = new Scene(vbox, 300, 460);
        YahtzeeStage.setScene(scene);
        YahtzeeStage.setTitle("Yahtzee");
        YahtzeeStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
