package controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import models.*;

public class Controller implements Initializable {

    @FXML
    private HBox hbox;

    @FXML
    private ComboBox<?> placeCB;

    @FXML
    private ComboBox<?> roofCB;

    @FXML
    private ComboBox<?> wallCB;

    @FXML
    private ComboBox<?> windowCB;

    @FXML
    private ComboBox<?> doorCB;

    @FXML
    private ComboBox<?> chimneyCB;

    @FXML
    private Canvas canvas;

    @FXML
    private Button rotateBtn;

    @FXML
    private TextField angleTF;

    private Roof roof = new Roof();
    private Wall wall = new Wall();
    private Window window = new Window();
    private Door door = new Door();
    private Place place = new Place();
    private Chimney chimney = new Chimney();
    private Compositer house;
    private double rotation = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAll();
        initHouse();
        initComponents();
        canvas.getGraphicsContext2D().fillRect(0,0,440,400);
        house.draw(canvas,ARect.full,rotation);

        rotateBtn.setOnAction(actionEvent -> {
            try {
                rotation = Double.parseDouble(angleTF.getText());
                canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
                house.draw(canvas,ARect.full,rotation);
            } catch (NumberFormatException ex){
                ex.printStackTrace();
            }
        });

        placeCB.setOnAction(actionEvent -> {
            place.current=placeCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
        roofCB.setOnAction(actionEvent -> {
            roof.current=roofCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
        wallCB.setOnAction(actionEvent -> {
            wall.current=wallCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
        chimneyCB.setOnAction(actionEvent -> {
            chimney.current=chimneyCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
        doorCB.setOnAction(actionEvent -> {
            door.current=doorCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
        windowCB.setOnAction(actionEvent -> {
            window.current=windowCB.getSelectionModel().getSelectedIndex();
            canvas.getGraphicsContext2D().clearRect(-220,-200,1000,1000);
            house.draw(canvas,ARect.full,rotation);
        });
    }

    private void initAll() {
        initComponent(roof, 4);
        initComponent(door, 4);
        initComponent(window, 4);
        initComponent(place, 4);
        initComponent(chimney, 4);
        initComponent(wall, 4);
    }

    private void initHouse(){
        house = new Compositer();
        house.setDraw(false);
        house.setPosition(ARect.full);
        house.addChild(place);
        house.addChild(roof);
        house.addChild(wall);
        house.addChild(door);
        house.addChild(chimney);
        house.addChild(window);
        house.setPosition(new ARect(0.25,0.4,0.5,0.6));
        house.loadTextures();
        house.setDraw(true);
    }

    private void initComponents() {
        ObservableList places = FXCollections.observableArrayList();
        places.addAll("1","2","3","4");
        placeCB.setItems(places);
        placeCB.getSelectionModel().select(0);
        roofCB.setItems(places);
        roofCB.getSelectionModel().select(0);
        wallCB.setItems(places);
        wallCB.getSelectionModel().select(0);
        chimneyCB.setItems(places);
        chimneyCB.getSelectionModel().select(0);
        windowCB.setItems(places);
        windowCB.getSelectionModel().select(0);
        doorCB.setItems(places);
        doorCB.getSelectionModel().select(0);
    }

    private void initComponent(Compositer c, int n){
        String[] pathes = new String[n]; //Загрузка текстур крыш
        String className = c.getClass().getName().substring(7);
        for (int i=1; i<n+1; i++){
            pathes[i-1] = "images/"+className+"s/"+i+".png";
        }
        switch (className){
            case "Roof": roof = new Roof(pathes); roof.setPosition(new ARect(150,100, 440, 280)); break;
            case "Wall": wall = new Wall(pathes); wall.setPosition(new ARect(150,240, 440, 280)); break;
            case "Door": door = new Door(pathes); door.setPosition(new ARect(385,250, 200, 270)); break;
            case "Place": place = new Place(pathes); place.setPosition(new ARect(0,0, 880, 800)); break;
            case "Window": window = new Window(pathes); window.setPosition(new ARect(170,280, 200, 140)); break;
            case "Chimney": chimney = new Chimney(pathes); chimney.setPosition(new ARect(180,60, 100, 150)); break;
            default: System.err.println("Неверное имя класса");
        }
    }

}
