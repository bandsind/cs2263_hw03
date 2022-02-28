/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs2263.hw02;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class App extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HomeWork3");
        //combo box for the dropdown list for course department

        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.setPromptText("Department");
        comboBox.setEditable(true);
        comboBox.getItems().add("Computer Science");
        comboBox.getItems().add("Mathematics");
        comboBox.getItems().add("Chemistry");
        comboBox.getItems().add("Physics");
        comboBox.getItems().add("Biology");
        comboBox.getItems().add("Electrical Engineering");


        HBox hbox = new HBox(comboBox);
        //GridPane initiation
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(comboBox,0,0);


        // text fields
        TextField courseName = new TextField();
        courseName.setPromptText("Course Name");
        GridPane.setConstraints(courseName,0 ,1);
        gridPane.getChildren().add(courseName);
        courseName.setPrefColumnCount(10);



        // text field for
        TextField courseNum = new TextField();
        courseNum.setPromptText("Course Number");
        courseNum.setPrefColumnCount(10);

        //Sets more than one constraint at once
        GridPane.setConstraints(courseNum,0 ,2);
        gridPane.getChildren().add(courseNum);

        // Number of Credits text fields
        TextField numOfCredits = new TextField();
        numOfCredits.setPromptText("Number of Credits");
        GridPane.setConstraints(numOfCredits,0, 3);
        gridPane.getChildren().add(numOfCredits);



        // save button
        Button enter = new Button("Save");
        GridPane.setConstraints(enter,1, 0);
        gridPane.getChildren().add(enter);


        // load button
        Button load = new Button("Load");
        GridPane.setConstraints(load,0, 9);
        gridPane.getChildren().add(load);

       //exit button
        Button exit = new Button("Exit");
        GridPane.setConstraints(exit,2, 9);
        gridPane.getChildren().add(exit);

       // clear button
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear,4, 0);
        gridPane.getChildren().add(clear);

        // display all button
        Button displayAll = new Button("Display All");
        GridPane.setConstraints(displayAll,5, 0);
        gridPane.getChildren().add(displayAll);

        // Style for the VBox border
        ListView<String> courseView = new ListView<>();
        VBox vbox = new VBox();
        String cssLayout = "-fx-border-color: blue;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";

        vbox.setStyle(cssLayout);


        gridPane.add(vbox,20,9,200,90);









        final Label label = new Label();
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setFont(new Font("Arial", 15));
        GridPane.setConstraints(label, 0, 5);
        GridPane.setColumnSpan(label,100);
        gridPane.getChildren().add(label);
        label.setWrapText(true);

        //actions


        EventHandler<MouseEvent> handler=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    CourseProcessor.saveCourse(courseName.getText(),courseNum.getText(),numOfCredits.getText(),comboBox.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Label label1=new Label("This course is saved to the Courses catalog"+"    Course Name: "+courseName.getText()+"   Course Number: "+courseNum.getText()+"  NO. of Credits: "+numOfCredits.getText()+"  Department Code: "+CourseProcessor.getDepCode(comboBox.getValue(),""));
                vbox.getChildren().add(label1);
            }
        };

        enter.setOnMouseClicked(handler);


        EventHandler<MouseEvent> handler1=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    vbox.getChildren().clear();

                    Course[] c= CourseProcessor.displayCourse();
                    for (int i = 0; i < c.length; i++) {
                        String name = "Course Name:  "+ c[i].getName();
                        String number= "  Course Number: "+String.valueOf(c[i].getNumber());
                        String credits= " No. Of Credits: "+String.valueOf(c[i].getCredit());
                        String dep=" Department code: "+c[i].getDepartmentCode();
                        String fin=name+" "+number+" "+credits+" "+dep;
                        Label label2=new Label(fin);
                        label2.setFont(new Font("Arial", 20));
                        vbox.getChildren().add(label2);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        displayAll.setOnMouseClicked(handler1);
        load.setOnMouseClicked(handler1);
        // Exit handler for the exit button
        EventHandler<MouseEvent> handler3 =new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        };
        exit.setOnMouseClicked(handler3);

        //clears the text fields
        EventHandler<MouseEvent> handler2=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                courseName.clear();
                courseNum.clear();
                numOfCredits.clear();
                vbox.getChildren().clear();
            }
        };

        clear.setOnMouseClicked(handler2);
        Group root = new Group(gridPane);

        Scene scene = new Scene(root, 800, 400);


        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
