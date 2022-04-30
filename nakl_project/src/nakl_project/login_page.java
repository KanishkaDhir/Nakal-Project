
package nakl_project;
import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class login_page extends Application
{

	public static void main(String[] args) 
	{
		
		launch(args);

	}
	
	Text txt;
	Reflection ref;
	GridPane grid;
	ImageView imgview;
	Label pwd;
	PasswordField txtpwd;
	Button login;
	Rectangle rectcolors;
	Group root;
	public void start(Stage stage) throws Exception
	{
		root=new Group();
		Scene sc=new Scene(root,680,720);
		
		grid=new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setGridLinesVisible(false);
	
		
		rectcolors=new Rectangle(sc.getWidth(), sc.getHeight());
		rectcolors.setFill(Color.AZURE);
		rectcolors.widthProperty().bind(sc.widthProperty());
		rectcolors.heightProperty().bind(sc.heightProperty());
		
		root.getChildren().addAll(rectcolors,grid);
		
		//----------------setting text
		ref=new Reflection();
		ref.setFraction(5);
		
		
		txt=new Text("LOGIN....");
		txt.setFont(Font.font("Segoe Print",FontWeight.BOLD, FontPosture.ITALIC, 50));
		txt.setFill(Color.DARKCYAN);
		txt.setStroke(Color.ORANGE);
		txt.setStrokeWidth(0.5);
		txt.setEffect(ref);
		
		//--------------setting image
		DropShadow sh=new DropShadow();
		sh.setColor(Color.BLUE);
		sh.setSpread(0.2);
		
		/*imgview=new ImageView();
		imgview.setFitWidth(200);
		imgview.setFitHeight(200);
		imgview.setEffect(sh);
		//imgview.setImage(new Image(new FileInputStream(new File("itsme.jpg"))));
		*/
		//------------set label
		pwd=new Label("PASSWORD");
		pwd.setFont(Font.font("Segoe Print",FontWeight.EXTRA_BOLD,FontPosture.ITALIC,15));
		pwd.setMaxSize(100, 100);
		pwd.setTextFill(Color.DARKCYAN);
		
		//------------set text field of pwd
		txtpwd=new PasswordField();
		txtpwd.setMaxSize(600, 50);
		txtpwd.setPromptText("enter password:");
		txtpwd.setFont(Font.font("Segoe Print",FontWeight.BOLD,FontPosture.ITALIC,15));
		
		//-------------------setting button login
		login=new Button("LOGIN");
		login.setPrefSize(80, 20);
		login.setTextFill(Color.DARKCYAN);
		login.setFont(Font.font("Segoe Print",FontWeight.BOLD,FontPosture.ITALIC,12));
		login.setOnAction(e->dologin());
		
		
		//------------set into grid
		grid.setConstraints(txt,13, 20,7, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,5,3,5));
		//grid.setConstraints(imgview,13, 0,5, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,5,3,5));
		grid.setConstraints(pwd,10, 35,3, 3, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,5,3,5));
		grid.setConstraints(txtpwd,13, 35,5, 3, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,5,3,5));
		grid.setConstraints(login,18,35 ,3, 3, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,5,3,5));
		
		
		//-----add into grid
		grid.getChildren().addAll(txt,pwd,txtpwd,login);
		
		stage.setScene(sc);
		stage.show();
	}
	
	void dologin()
	{
		String msg=txtpwd.getText();
		if(msg.equals("PLRS*123"))
		{
			login.setOnAction(e->{
				new first_one();
			});
		}
		else
		{
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("INVALID PASSWORD");
			alert.setContentText("please enter the correct password");
			alert.showAndWait();
			txtpwd.clear();
		}
	}

}



