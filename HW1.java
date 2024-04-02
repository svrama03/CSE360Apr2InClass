import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HW1 extends Application {
    private TextArea billTextArea;
    private CheckBox eggSandwichCheckbox, bagelCheckbox, potatoSaladCheckbox, chickenSandwichCheckbox;
    private RadioButton coffeeRadioButton, greenTeaRadioButton, blackTeaRadioButton, orangeJuiceRadioButton;
    private Button orderButton, cancelButton, confirmButton;

    private double totalCost = 0.0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Joe's Deli Breakfast Order");

        // Create UI components
        billTextArea = new TextArea();
        billTextArea.setEditable(false);

        eggSandwichCheckbox = new CheckBox("Egg Sandwich - $7.99");
        bagelCheckbox = new CheckBox("Bagel - $2.50");
        potatoSaladCheckbox = new CheckBox("Potato Salad - $4.49");
        chickenSandwichCheckbox = new CheckBox("Chicken Sandwich - $9.99");

        ToggleGroup drinkToggleGroup = new ToggleGroup();
        coffeeRadioButton = new RadioButton("Coffee - $1.99");
        greenTeaRadioButton = new RadioButton("Green Tea - $0.99");
        blackTeaRadioButton = new RadioButton("Black Tea - $1.25");
        orangeJuiceRadioButton = new RadioButton("Orange Juice - $2.25");

        coffeeRadioButton.setToggleGroup(drinkToggleGroup);
        greenTeaRadioButton.setToggleGroup(drinkToggleGroup);
        blackTeaRadioButton.setToggleGroup(drinkToggleGroup);
        orangeJuiceRadioButton.setToggleGroup(drinkToggleGroup);

        orderButton = new Button("Order");
        cancelButton = new Button("Cancel");
        confirmButton = new Button("Confirm");

        // Set actions for buttons
        orderButton.setOnAction(e -> handleOrderButton());
        cancelButton.setOnAction(e -> handleCancelButton());
        confirmButton.setOnAction(e -> handleConfirmButton());

        // Layout setup
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                eggSandwichCheckbox, bagelCheckbox, potatoSaladCheckbox, chickenSandwichCheckbox,
                new Separator(),
                coffeeRadioButton, greenTeaRadioButton, blackTeaRadioButton, orangeJuiceRadioButton,
                new Separator(),
                orderButton, cancelButton, confirmButton,
                new Separator(),
                billTextArea
        );

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleOrderButton() {
        totalCost = 0.0;
        StringBuilder bill = new StringBuilder("Your Order:\n");

        if (eggSandwichCheckbox.isSelected()) {
            totalCost += 7.99;
            bill.append("Egg Sandwich - $7.99\n");
        }
        if (bagelCheckbox.isSelected()) {
            totalCost += 2.50;
            bill.append("Bagel - $2.50\n");
        }
        if (potatoSaladCheckbox.isSelected()) {
            totalCost += 4.49;
            bill.append("Potato Salad - $4.49\n");
        }
        if (chickenSandwichCheckbox.isSelected()) {
            totalCost += 9.99;
            bill.append("Chicken Sandwich - $9.99\n");
        }

        if (coffeeRadioButton.isSelected()) {
            totalCost += 1.99;
            bill.append("Coffee - $1.99\n");
        } else if (greenTeaRadioButton.isSelected()) {
            totalCost += 0.99;
            bill.append("Green Tea - $0.99\n");
        } else if (blackTeaRadioButton.isSelected()) {
            totalCost += 1.25;
            bill.append("Black Tea - $1.25\n");
        } else if (orangeJuiceRadioButton.isSelected()) {
            totalCost += 2.25;
            bill.append("Orange Juice - $2.25\n");
        }

        double tax = totalCost * 0.07;
        double totalBill = totalCost + tax;
        bill.append("\nSubtotal: $").append(String.format("%.2f", totalCost)).append("\n");
        bill.append("Tax (7%): $").append(String.format("%.2f", tax)).append("\n");
        bill.append("Total: $").append(String.format("%.2f", totalBill));

        billTextArea.setText(bill.toString());
    }

    private void handleCancelButton() {
        eggSandwichCheckbox.setSelected(false);
        bagelCheckbox.setSelected(false);
        potatoSaladCheckbox.setSelected(false);
        chickenSandwichCheckbox.setSelected(false);

        coffeeRadioButton.setSelected(false);
        greenTeaRadioButton.setSelected(false);
        blackTeaRadioButton.setSelected(false);
        orangeJuiceRadioButton.setSelected(false);

        billTextArea.clear();
    }

    private void handleConfirmButton() {
        String confirmationMessage = "Items Ordered:\n";
        
        if (eggSandwichCheckbox.isSelected()) {
            confirmationMessage += "Egg Sandwich\n";
        }
        if (bagelCheckbox.isSelected()) {
            confirmationMessage += "Bagel\n";
        }
        if (potatoSaladCheckbox.isSelected()) {
            confirmationMessage += "Potato Salad\n";
        }
        if (chickenSandwichCheckbox.isSelected()) {
            confirmationMessage += "Chicken Sandwich\n";
        }

        if (coffeeRadioButton.isSelected()) {
            confirmationMessage += "Coffee\n";
        } else if (greenTeaRadioButton.isSelected()) {
            confirmationMessage += "Green Tea\n";
        } else if (blackTeaRadioButton.isSelected()) {
            confirmationMessage += "Black Tea\n";
        } else if (orangeJuiceRadioButton.isSelected()) {
            confirmationMessage += "Orange Juice\n";
        }

        double tax = totalCost * 0.07;
        double totalBill = totalCost + tax;

        confirmationMessage += String.format("\nSubtotal: $%.2f\n", totalCost);
        confirmationMessage += String.format("Tax (7%%): $%.2f\n", tax);
        confirmationMessage += String.format("Total: $%.2f", totalBill);

        billTextArea.setText(confirmationMessage);
    }

}
