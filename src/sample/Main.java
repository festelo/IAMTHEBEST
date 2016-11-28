package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public Stage primaryStage;

    //Создание окошка авторизации и регистрации
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Controller controller = new Controller(this);
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    //Точка входа
    public static void main(String[] args) {
        launch(args);
    }


}