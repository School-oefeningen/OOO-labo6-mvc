package domain.launcher;

import domain.model.GeheimschriftFacade;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.Checker;

public class GeheimschriftView {
    private Stage stage = new Stage();
    private Scene scene;
    private Pane root;
    TextField uikomstResultaat = new TextField();
    private final GeheimschriftFacade geheimschriftFacade = new GeheimschriftFacade();

    Label stringLabel = new Label("Tekst die je gaat coderen/decoderen");
    TextField stringTextfield = new TextField();
    Label intCaesar = new Label("Geeft een cijfer als je voor Caesarencryptie kiest");
    TextField caesarGetalLabel = new TextField();
    ComboBox combobox = new ComboBox(FXCollections.observableArrayList(geheimschriftFacade.getAllEncryptionTypes()));
    Button codeerBtn = new Button("Codeer");
    Button decodeerBtn = new Button("Decodeer");
    Label uitkomstLabel = new Label("Resultaat");
    Label errorLabel = new Label("");

    public GeheimschriftView(GeheimschriftController controller) {
        root = createNodeHierarchy(controller);
        root = createNodeHierarchy(controller);
        scene = new Scene(root,500,200);
        stage.setScene(scene);
        stage.setTitle("Counter");
        stage.setResizable(false);
        stage.setX(700);
        stage.setY(200);
        stage.show();
        controller.setView(this);
    }

    public void setResult(String s) {
        uikomstResultaat.setText(s);
    }

    private Pane createNodeHierarchy(GeheimschriftController controller) {
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.BASELINE_CENTER);
        pane.setVgap(5);
        pane.setHgap(5);
        pane.getChildren().addAll(stringLabel, stringTextfield, intCaesar, caesarGetalLabel, combobox, codeerBtn, decodeerBtn, uitkomstLabel, uikomstResultaat, errorLabel);

        codeerBtn.setOnAction(a -> {
            try {
                String encryptieType = (String) combobox.getValue();
                String input = stringTextfield.getText();
                String caesarString = caesarGetalLabel.getText();
                int caesarGetal = 0;

                if (Checker.isEmptyString(encryptieType) || Checker.isEmptyString(input)) {
                    errorLabel.setText("Geef tekst in en selecteer een encryptiemethode");
                }

                if (geheimschriftFacade.isChoiceCaesarEncryption(encryptieType)) {
                    if (Checker.isEmptyString(caesarString)) errorLabel.setText("Geef een caesar getal in");
                    else {
                        caesarGetal = Integer.parseInt(caesarString);
                        encrypt(encryptieType, caesarGetal, input, controller);
                    }
                } else {
                    encrypt(encryptieType, caesarGetal, input, controller);
                }
            } catch (NumberFormatException  e) {
                errorLabel.setText("Geef een caesar getal in");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        decodeerBtn.setOnAction(a -> {
            try {
                String encryptieType = (String) combobox.getValue();
                String input = stringTextfield.getText();
                String caesarString = caesarGetalLabel.getText();
                int caesarGetal = 0;

                if (Checker.isEmptyString(encryptieType) || Checker.isEmptyString(input)) {
                    errorLabel.setText("Geef tekst in en selecteer een encryptiemethode");
                }

                if (geheimschriftFacade.isChoiceCaesarEncryption(encryptieType)) {
                    if (Checker.isEmptyString(caesarString)) errorLabel.setText("Geef een caesar getal in");
                    else {
                        caesarGetal = Integer.parseInt(caesarString);
                        decrypt(encryptieType, caesarGetal, input, controller);
                    }
                } else {
                    decrypt(encryptieType, caesarGetal, input, controller);
                }
            } catch (NumberFormatException  e) {
                errorLabel.setText("Geef een caesar getal in");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        return pane;
    }

    private void encrypt(String encryptieType, int caesarGetal, String input, GeheimschriftController controller) {
        geheimschriftFacade.setGeheimschriftBehaviour(encryptieType, caesarGetal);
        String output = geheimschriftFacade.encryptString(input);
        controller.updateEncryptHistory(output);
    }

    private void decrypt(String encryptieType, int caesarGetal, String input, GeheimschriftController controller) {
        geheimschriftFacade.setGeheimschriftBehaviour(encryptieType, caesarGetal);
        String output = geheimschriftFacade.decryptString(input);
        controller.update(output);
    }
}
