import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainClass extends Application
{
    @Override
    public void start(Stage YahtzeeStage)
    {

        // Roll Button Set Up

        Button RollButton = new Button("Roll");
        RollButton.setPrefSize(1500,150);
        TextField ResultField = new TextField("Dice:");
        ResultField.setEditable(false);
        ResultField.setAlignment(Pos.CENTER);
        GridPane DiceGrid = new GridPane();
        Button[] DiceLabel = new Button[5];

        final String[] dice_symbols = { // Used as the visual representation of the dice rolls
            "⚀", "⚁", "⚂", "⚃", "⚄", "⚅"
        };

        dice dice1 = new dice(); // Creates an instance of the dice class

        // Sets up the dice buttons

        for (int i = 0; i < 5; i++) 
        {
            DiceLabel[i] = new Button("?");

            DiceLabel[i].setPrefSize(300, 150);
            DiceLabel[i].setStyle("-fx-font-size: 125px;");

            DiceGrid.add(DiceLabel[i], i, 0);
        }

        TextField OptionField = new TextField("Options:");
        OptionField.setEditable(false);
        OptionField.setAlignment(Pos.CENTER);
        GridPane buttonGrid = new GridPane();

        // Sets up scoring buttons

        String[][] buttonLabels = 
        {
            {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes", "Chance"},
            {"3 of a Kind", "4 of a Kind", "Full House", "Small Straight", "Large Straight", "Yahtzee", "Bonus"}
        };
        for (int row = 0; row < buttonLabels.length; row++) 
        {
            for (int col = 0; col < buttonLabels[row].length; col++) 
            {
                Button btn = new Button(buttonLabels[row][col]);
                btn.setPrefSize(225, 150);
                buttonGrid.add(btn, col, row);
            }
        }

        // Visuals Setup for points and rolls left
        int roundleft = 3;
        int totalleft = 20;
        TextField PointField = new TextField("Points:");
        PointField.setEditable(false);
        PointField.setAlignment(Pos.CENTER);
        TextField PointDisplay = new TextField("0");
        PointDisplay.setEditable(false);
        PointDisplay.setAlignment(Pos.CENTER);
        TextField RollsLeft = new TextField("RollsLeft (Round):");
        RollsLeft.setEditable(false);
        RollsLeft.setAlignment(Pos.CENTER);
        TextField RollsLeft2 = new TextField("RollsLeft (Total):");
        RollsLeft2.setEditable(false);
        RollsLeft2.setAlignment(Pos.CENTER);
        TextField RollsLeftNumber = new TextField(String.valueOf(roundleft));
        RollsLeftNumber.setEditable(false);
        RollsLeftNumber.setAlignment(Pos.CENTER);
        TextField RollsLeftNumber2 = new TextField(String.valueOf(totalleft));
        RollsLeftNumber2.setEditable(false);
        RollsLeftNumber2.setAlignment(Pos.CENTER);
        RollsLeftNumber.setPrefWidth(150);
        RollsLeftNumber2.setPrefWidth(150);
        HBox.setHgrow(RollsLeft, Priority.ALWAYS);
        HBox.setHgrow(RollsLeft2, Priority.ALWAYS);
        HBox.setHgrow(RollsLeftNumber, Priority.ALWAYS);
        HBox.setHgrow(RollsLeftNumber2, Priority.ALWAYS);
        HBox RollsLeftBox = new HBox(0, RollsLeftNumber, RollsLeftNumber2);
        HBox RollsLeftDisplay = new HBox(0, RollsLeft, RollsLeft2);
        VBox vbox = new VBox(10, PointField, PointDisplay, RollsLeftDisplay, RollsLeftBox, 
            RollButton, ResultField, DiceGrid, OptionField, buttonGrid);
        Scene scene = new Scene(vbox, 1500, 1000);
        YahtzeeStage.setScene(scene);
        YahtzeeStage.setTitle("Yahtzee");
        YahtzeeStage.show();
        
        RollButton.setOnAction(event -> updateDice(dice_symbols, DiceLabel, dice1)); // Runs the updateDice method when the roll button is pressed.
        RollButton.setOnAction(event -> updateRolls(roundleft, totalleft, RollsLeftNumber, RollsLeftNumber2));
    }
    public void updateRolls(int roundleft, int totalleft, TextField RollsLeftNumber, TextField RollsLeftNumber2)
    {
        if(roundleft == 0)
        {
            totalleft = totalleft - 1;
            roundleft = 3;
            RollsLeftNumber.setText(String.valueOf(roundleft));
            RollsLeftNumber2.setText(String.valueOf(totalleft));
        }
        else
        {
            roundleft = roundleft - 1;
            RollsLeftNumber.setText(String.valueOf(roundleft));
        }
    }
    public static void main(String[] args) // Main Method
    {
        launch(args);
    }
    
    public void updateDice(String[] dice_symbols, Button[] DiceLabel, dice dice1) // Event that runs when the roll button is pressed. Used to update the dice visual.
    {
        boolean[] hold = {true, true, true, true, true};

        dice1.diceRoll(hold);
        
        int[] dice_vals = dice1.getResults();
        for (int i = 0; i < 5; i++) 
        {
            DiceLabel[i].setText(dice_symbols[dice_vals[i]]);
        }
    }

}
