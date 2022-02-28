package edu.isu.cs2263.hw02;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourseProcessor extends Application {

    static List<Course> courseList = new ArrayList<>();
    static List<String> courses = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HomeWork3");
        //combo box for the dropdown list for course department

        ComboBox comboBox = new ComboBox();

        comboBox.setPromptText("Department");
        //comboBox.setEditable(true);
        comboBox.getItems().add("Computer Science");
        comboBox.getItems().add("Mathematics");
        comboBox.getItems().add("Chemistry");
        comboBox.getItems().add("Physics");
        comboBox.getItems().add("Biology");
        comboBox.getItems().add("Electrical Engineering");


        // Buttons
        Button clear = new Button("Clear");
        Button displayAll = new Button("Display All");
        Button display = new Button("Display Dept");
        Button enter = new Button("Enter");
        Button save = new Button("Save");
        Button load = new Button("Load");


        //Text fields
        TextField courseName = new TextField();
        courseName.setPromptText("Course Name");


        TextField courseNum = new TextField();
        courseNum.setPromptText("Course Number");

        TextField numOfCredits = new TextField();
        numOfCredits.setPromptText("Number of Credits");



        //View
        ListView<String> courseView = new ListView<>();
        ScrollPane pane = new ScrollPane();
        pane.prefWidthProperty().bind(courseView.widthProperty());
        pane.prefHeightProperty().bind(courseView.heightProperty());
        pane.setContent(courseView);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        //Saves course in List
        enter.setOnAction( event -> {


            //Checks if the form is empty
            if(comboBox.getValue() != null && !courseName.getText().isEmpty() && !courseNum.getText().isEmpty() && !numOfCredits.getText().isEmpty()) {

                Course course = new Course(courseName.getText(), Integer.parseInt(courseNum.getText()),
                        Integer.parseInt(numOfCredits.getText()), Course.getDeptFromName((String) comboBox.getValue()));

                courseList.add(course);
                courses.add(course.toString());


                // Clears all selections
                courseName.clear();
                courseNum.clear();
                numOfCredits.clear();
                comboBox.getSelectionModel().clearSelection();

            }});




        // Clears form data
        clear.setOnAction(event -> {

            courseName.clear();
            courseNum.clear();
            numOfCredits.clear();
            comboBox.getSelectionModel().clearSelection();
            courseView.getItems().clear();

        });


        // Filters courseView from comboBox selection
        display.setOnAction(event -> {

            if(comboBox.getValue() != null){

                List<String> list = new ArrayList<>();
                String dept = (String) comboBox.getValue();

                for(Course course: courseList){

                    if(course.getDepartmentCode().equals(Course.getDeptFromName(dept))){
                        list.add(course.toString());
                    }
                }
                courseView.getItems().clear();
                courseView.getItems().addAll(list);
                comboBox.getSelectionModel().clearSelection();
            }});


        // Displays all courses
        displayAll.setOnAction(event -> {
            courseView.getItems().clear();
            comboBox.getSelectionModel().clearSelection();
            courseView.getItems().addAll(courses);

        });


        // Save courses to a Json file
        save.setOnAction(event -> {

            try {
                saveCourses(courseList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        // load courses into List

        load.setOnAction(event -> {

            courseList = loadCourses();
            courses.clear();

            if(!courseList.isEmpty()){
                for (Course course: courseList) {
                    courses.add(course.toString());
                }

                courseView.getItems().clear();
                courseView.getItems().addAll(courses);

            }
        });




        //Layout
        HBox buttons = new HBox();
        buttons.getChildren().addAll(enter, clear, display, displayAll, save, load);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(3));


        VBox vBox = new VBox();
        vBox.getChildren().addAll(comboBox, courseName, courseNum, numOfCredits, buttons);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(5));


        HBox root = new HBox();
        root.getChildren().addAll(vBox, pane);
        root.setSpacing(10);
        root.setPadding(new Insets(5));

        Scene scene = new Scene(root, 680, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        // Handles closing event
        primaryStage.setOnCloseRequest(event -> {
             event.consume();
             logout(primaryStage);
        });

    }


    private void saveCourses(List<Course> courses) throws IOException {

        Gson gson = new Gson();

        String data =  gson.toJson(courses);

        File file = new File("Courses.json");

        try{
            if(!file.exists()){
                file.createNewFile();
            }else{
                saveTextToFile(data, file);
            }

        }catch (IOException ex){
            ex.printStackTrace();
        }


        System.out.println(data);


    }



    private void saveTextToFile(String content, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<Course> loadCourses(){


        List<Course> loadedList =  null ;

        try{

            Reader reader = Files.newBufferedReader(Paths.get("Courses.json"));

            // convert JSON array to list of courses
            loadedList = new Gson().fromJson(reader, new TypeToken<List<Course>>() {}.getType());


            // close reader
            reader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedList;
    }

    private void logout(Stage stage){

        // Confirmation Box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Do you want to quit");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }

    }







}
