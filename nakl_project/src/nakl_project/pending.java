package nakl_project;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableView.ResizeFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class pending {

	

	Scene scene;
	Group root;
	Rectangle rect;
	GridPane grid;
	Text txt;
	TableView<applicant_pending> table;
	DatePicker date;
	Button go,back;
	Connection con;
	PreparedStatement ps;
	Stage stage;
	ScrollPane sp;
	VBox vbox;
	Printer pprint;
	PageLayout pagelayout;
	PrinterJob job;
	ResultSet rs;
	public pending(){
		// TODO Auto-generated method stub
		con=mysql.getConnection();
		root=new Group();
		scene=new Scene(root,680,720);
		
		rect=new Rectangle();
		rect.setFill(Color.AZURE);
		rect.widthProperty().bind(scene.widthProperty());
		rect.heightProperty().bind(scene.heightProperty());
		
		txt=new Text("PENDING");
		txt.setFill(Color.DARKCYAN);
		txt.setFont(Font.font("Segoe Print",FontWeight.BOLD, FontPosture.ITALIC,30));
		
		date=new DatePicker();

		String pattern = "yyyy-MM-dd";

		date.setPromptText(pattern.toLowerCase());

		date.setConverter(new StringConverter<LocalDate>() {
		     
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

		     public String toString(LocalDate date) {
		         if (date != null) {
		             return dateFormatter.format(date);
		         } else {
		             return "";
		         }
		     }

		     public LocalDate fromString(String string) {
		         if (string != null && !string.isEmpty()) {
		             return LocalDate.parse(string, dateFormatter);
		         } else {
		             return null;
		         }
		     }
		 });
		
		go=new Button("GO");
		go.setOnAction(e->show());
		back=new Button("BACK");
		 back.setOnAction(e->{
			 Stage primaryStage=(Stage) back.getScene().getWindow();
			 primaryStage.close();
		 });
		
		table=new TableView<applicant_pending>();
		//table.setMaxWidth(900);
		TableColumn<applicant_pending,String> colsrno=new TableColumn<applicant_pending, String>("SR NO.");
		colsrno.setMaxWidth(80);
		colsrno.setCellValueFactory(new PropertyValueFactory<>("SrNo"));
		
		TableColumn<applicant_pending,String> colname=new TableColumn<applicant_pending, String>("NAME");
		colname.setMaxWidth(100);
		colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		TableColumn<applicant_pending,String> colmob=new TableColumn<applicant_pending, String>("MOBILE NO.");
		colmob.setMaxWidth(100);
		colmob.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
		
		TableColumn<applicant_pending,String> colnakl=new TableColumn<applicant_pending, String>("NAKL");
		colnakl.setMaxWidth(100);
		colnakl.setCellValueFactory(new PropertyValueFactory<>("Nakl"));
		
		TableColumn<applicant_pending,String> colini=new TableColumn<applicant_pending, String>("INITIAL FEE");
		colini.setMaxWidth(100);
		colini.setCellValueFactory(new PropertyValueFactory<>("InitialFee"));
		
		TableColumn<applicant_pending,String> colapply=new TableColumn<applicant_pending, String>("APPLIED DATE");
		colapply.setMaxWidth(120);
		colapply.setCellValueFactory(new PropertyValueFactory<>("AppliedDate"));
		
		TableColumn<applicant_pending,String> coltent=new TableColumn<applicant_pending, String>("DELIVERY DATE");
		coltent.setMaxWidth(120);
		coltent.setCellValueFactory(new PropertyValueFactory<>("TentativeDeliveryDate"));
		
		table.getColumns().addAll(colsrno,colname,colmob,colnakl,colini,colapply,coltent);

		
		sp=new ScrollPane();
	    sp.setContent(table);
		sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
			vbox=new VBox();
	         vbox.getChildren().addAll(table,sp);
		    
		grid=new GridPane();
		grid.setConstraints(back,7,1,1,1,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(txt,8, 1,2 ,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(date,9, 3,2 ,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(go,12, 3,2 ,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(vbox,7, 7,50,50,HPos.CENTER,VPos.CENTER,null,null,new Insets(0,0,0,0));
		//grid.setConstraints(print,9, 70,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		
		grid.getChildren().addAll(back,txt,date,go,vbox);
		
		stage=new Stage();
		root.getChildren().addAll(rect,grid);
		stage.setScene(scene);
		stage.show();
		
	}
	public void show()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String dd = (date.getValue()).format(formatter);
		
		ObservableList<applicant_pending> app=FXCollections.observableArrayList();
		
		
		
		try 
		{
			ps=con.prepareStatement("select * from new_entry where TentDeliveryDate<= ? and ReadyToDeliver='NO' ");
			ps.setString(1,dd);
			System.out.print(""+ps);
			 rs=ps.executeQuery();
			 XSSFWorkbook wb=new XSSFWorkbook();
				XSSFSheet sheet=wb.createSheet("Pending.xlsx");
				XSSFRow header=sheet.createRow(0);
				header.createCell(0).setCellValue("SrNo");
				header.createCell(1).setCellValue("Name");
				
				header.createCell(2).setCellValue("MobileNo");
				header.createCell(3).setCellValue("Nakl");
				
				header.createCell(4).setCellValue("NoOfPages");
				
				header.createCell(5).setCellValue("InitialFee");
				header.createCell(6).setCellValue("BalanceFee");
				header.createCell(7).setCellValue("TotalFee");
				
				header.createCell(8).setCellValue("AppliedDate");
				header.createCell(9).setCellValue("DeliveryDate");
				
				
				int index=1;
			while(rs.next())
			{
				applicant_pending ap=new applicant_pending(rs.getString("SrNo"),rs.getString("Name"),
						rs.getString("MobileNo"),rs.getString("Nakl"),rs.getString("InitialFee")
						,rs.getString("AppliedDate"),rs.getString("TentDeliveryDate"));
				app.add(ap);
				XSSFRow row=sheet.createRow(index);
				row.createCell(0).setCellValue(rs.getString("SrNo"));
				row.createCell(1).setCellValue(rs.getString("Name"));
				
				row.createCell(2).setCellValue(rs.getString("MobileNo"));
				row.createCell(3).setCellValue(rs.getString("Nakl"));
				row.createCell(4).setCellValue(rs.getString("NoOfPages"));
				row.createCell(5).setCellValue(rs.getString("InitialFee"));
				row.createCell(6).setCellValue(rs.getString("BalanceFee"));
				row.createCell(7).setCellValue(rs.getString("TotalFee"));
				
				row.createCell(8).setCellValue(rs.getString("AppliedDate"));
				row.createCell(9).setCellValue(rs.getString("TentDeliveryDate"));
				
				index++;
			}
			//System.out.println(app);
			FileOutputStream fileout;
			fileout = new FileOutputStream("Pending.xlsx");
			
			wb.write(fileout);
			fileout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		table.setItems(app);
	}
	

}
