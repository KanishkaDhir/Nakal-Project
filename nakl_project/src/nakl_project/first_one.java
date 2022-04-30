package nakl_project;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.DatabaseMetaData;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class first_one  {

	

    Scene scene;
    HBox h1;
    GridPane grid;
    Text txt;
    String text;
    Button btnew,btnbal,btnsta,btnpen,btnimport,btnexport,back;
    Circle cir;
    Rectangle rect,rectangle; 
    Group root;
    Reflection ref;
    Stage stage;
    PreparedStatement pst;
    Connection con;
	public first_one() {
		// TODO Auto-generated method stub
		con=mysql.getConnection();
		root=new Group();
		scene=new Scene(root,680,720);
		
		
		text="NAKAL PROJECT";
		txt=new Text();
		txt.setText(text);
		txt.setFill(Color.DARKCYAN);
		//txt.setStroke(Color.BLACK);
		//txt.setStrokeWidth(1.0);
		txt.setFont(Font.font("Segoe Print",FontWeight.SEMI_BOLD,FontPosture.ITALIC, 50));
		txt.setTextAlignment(TextAlignment.CENTER);
		/*h1=new HBox();
		h1.setMaxSize(200,100);
		h1.getChildren().add(btn);
		*/
		rect=new Rectangle(scene.getWidth(),scene.getHeight());
		rect.setFill(Color.AZURE);
		rect.widthProperty().bind(scene.widthProperty());
		rect.heightProperty().bind(scene.heightProperty());
		
		 rectangle=new Rectangle();
	       rectangle.setFill(Color.AZURE);
	       rectangle.setStroke(Color.GREENYELLOW);
	      rectangle.setX(150.0f); 
	      rectangle.setY(75.0f); 
	      rectangle.setWidth(350.0f); 
	      rectangle.setHeight(20.0f); 
	        
	      rectangle.setArcWidth(40.0); 
	      rectangle.setArcHeight(30.0);  
	      
		
		btnew=new Button("APPLICANT");
		btnew.setShape(rectangle);
		btnew.setPrefSize(300, 50);
	    btnew.setTextFill(Color.DARKCYAN);
	    btnew.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnew.setOnAction(e->{
	    	new new_entry();
	    });
	        
		btnbal=new Button("BALANCE");
		btnbal.setShape(rectangle);
		btnbal.setPrefSize(300, 50);
		btnbal.setTextFill(Color.DARKCYAN);
	    btnbal.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnbal.setOnAction(e->{
	    	new balance();
	    });
	    
		btnsta=new Button("STATUS");
		btnsta.setShape(rectangle);
		btnsta.setPrefSize(300, 50);
		btnsta.setTextFill(Color.DARKCYAN);
	    btnsta.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnsta.setOnAction(e->{
	    	new status();
	    });
	    
		btnpen=new Button("PENDING");
		btnpen.setShape(rectangle);
		btnpen.setPrefSize(300, 50);
		btnpen.setTextFill(Color.DARKCYAN);
	    btnpen.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnpen.setOnAction(e->{
	    	new pending();
	    });
	    
	    btnimport=new Button("RECOVERY");
		btnimport.setShape(rectangle);
		btnimport.setPrefSize(300, 50);
		btnimport.setTextFill(Color.DARKCYAN);
	    btnimport.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnimport.setOnAction(e->{
	    try{
	    	java.sql.DatabaseMetaData md=con.getMetaData();
			ResultSet rs=md.getTables(null, null, "new_entry", null);
			if(rs.next())
			{
				System.out.println("found");
			}
			else
			{
				System.out.println("not found");
				String query="create table new_entry(SrNo bigint(100) primary key,Name varchar(200),Fname varchar(200),"
						+ "GFname varchar(200),Village varchar(100),MobileNo varchar(50),KhewatNo varchar(100),"
						+ "KhasraNo varchar(100),Nakl varchar(50),InitialFee varchar(50),AppliedDate varchar(100),"
						+ "TentDeliveryDate varchar(100),NoOfPages varchar(100),BalanceFee varchar(100),"
						+ "TotalFee varchar(100),ReadyToDeliver varchar(100),Delivered varchar(100),ActualDate varchar(100))";
				
				pst=con.prepareStatement(query);
				pst.execute();
			}
			
		     String query="insert into new_entry(SrNo,Name,Fname,GFname,Village,MobileNo,KhewatNo,"
		     		+ "KhasraNo,Nakl,InitialFee,AppliedDate,TentDeliveryDate"
		     		+ ",NoOfPages,BalanceFee,TotalFee,ReadyToDeliver,Delivered,ActualDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
			pst=con.prepareStatement(query);
			
			FileInputStream filein=new FileInputStream(new File("UserDetails.xlsx"));
			XSSFWorkbook wb=new XSSFWorkbook(filein);
			XSSFSheet sheet=wb.getSheetAt(0);
			Row row;
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				row=sheet.getRow(i);
				pst.setString(1, row.getCell(0).getStringCellValue());
				pst.setString(2, row.getCell(1).getStringCellValue());
				pst.setString(3, row.getCell(2).getStringCellValue());
				pst.setString(4, row.getCell(3).getStringCellValue());
				pst.setString(5, row.getCell(4).getStringCellValue());
				pst.setString(6, row.getCell(5).getStringCellValue());
				pst.setString(7, row.getCell(6).getStringCellValue());
				pst.setString(8, row.getCell(7).getStringCellValue());
				pst.setString(9, row.getCell(8).getStringCellValue());
				pst.setString(10, row.getCell(9).getStringCellValue());
				pst.setString(11, row.getCell(10).getStringCellValue());
				pst.setString(12, row.getCell(11).getStringCellValue());
				pst.setString(13, row.getCell(12).getStringCellValue());
				pst.setString(14, row.getCell(13).getStringCellValue());
				pst.setString(15, row.getCell(14).getStringCellValue());
				pst.setString(16, row.getCell(15).getStringCellValue());
				pst.setString(17, row.getCell(16).getStringCellValue());
				pst.setString(18, row.getCell(17).getStringCellValue());
				pst.execute();
			}
			
			filein.close();
			Alert al=new Alert(AlertType.INFORMATION);
    		al.setContentText("your data has been recovered");
    		al.showAndWait();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    	
	    });
	    
	    btnexport=new Button("BACKUP");
		btnexport.setShape(rectangle);
		btnexport.setPrefSize(300, 50);
		btnexport.setTextFill(Color.DARKCYAN);
	    btnexport.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
	    btnexport.setOnAction(e->{
	    	try{
	    		String query="select * from new_entry";
	    		pst=con.prepareStatement(query);
	    		ResultSet rs=pst.executeQuery();
	    		
	    		XSSFWorkbook wb=new XSSFWorkbook();
	    		XSSFSheet sheet=wb.createSheet("UserDetails.xlsx");
	    		XSSFRow header=sheet.createRow(0);
	    		header.createCell(0).setCellValue("SrNo");
	    		header.createCell(1).setCellValue("Name");
	    		header.createCell(2).setCellValue("FatherName");
	    		header.createCell(3).setCellValue("GrandFatherName");
	    		header.createCell(4).setCellValue("Village");
	    		header.createCell(5).setCellValue("MobileNo");
	    		header.createCell(6).setCellValue("KhewatNo");
	    		header.createCell(7).setCellValue("KhasraNo");
	    		header.createCell(8).setCellValue("Nakl");
	    		header.createCell(9).setCellValue("InitialFee");
	    		header.createCell(10).setCellValue("AppliedDate");
	    		header.createCell(11).setCellValue("TentDeliveryDate");
	    		header.createCell(12).setCellValue("NoOfPages");
	    		header.createCell(13).setCellValue("BalanceFee");
	    		header.createCell(14).setCellValue("TotalFee");
	    		header.createCell(15).setCellValue("ReadyToDeliver");
	    		header.createCell(16).setCellValue("Delivered");
	    		header.createCell(17).setCellValue("ActualDate");
	    		
	    		int index=1;
	    		while(rs.next())
	    		{
	    			XSSFRow row=sheet.createRow(index);
	    			row.createCell(0).setCellValue(rs.getString("SrNo"));
	    			row.createCell(1).setCellValue(rs.getString("Name"));
	    			row.createCell(2).setCellValue(rs.getString("Fname"));
	    			row.createCell(3).setCellValue(rs.getString("GFname"));
	    			row.createCell(4).setCellValue(rs.getString("Village"));
	    			row.createCell(5).setCellValue(rs.getString("MobileNo"));
	    			row.createCell(6).setCellValue(rs.getString("KhewatNo"));
	    			row.createCell(7).setCellValue(rs.getString("KhasraNo"));
	    			row.createCell(8).setCellValue(rs.getString("Nakl"));
	    			row.createCell(9).setCellValue(rs.getString("InitialFee"));
	    			row.createCell(10).setCellValue(rs.getString("AppliedDate"));
	    			row.createCell(11).setCellValue(rs.getString("TentDeliveryDate"));
	    			row.createCell(12).setCellValue(rs.getString("NoOfPages"));
	    			row.createCell(13).setCellValue(rs.getString("BalanceFee"));
	    			row.createCell(14).setCellValue(rs.getString("TotalFee"));
	    			row.createCell(15).setCellValue(rs.getString("ReadyToDeliver"));
	    			row.createCell(16).setCellValue(rs.getString("Delivered"));
	    			row.createCell(17).setCellValue(rs.getString("ActualDate"));
	    			index++;
	    		}
	    		
	    		FileOutputStream fileout=new FileOutputStream("UserDetails.xlsx");
	    		wb.write(fileout);
	    		fileout.close();
	    		
	    		Alert al=new Alert(AlertType.INFORMATION);
	    		al.setContentText("your data has been backed up");
	    		al.showAndWait();
			}
	    	catch (Exception e1)
	    	{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    });
	    back=new Button("BACK");
		back.setOnAction(e->{
			 Stage primaryStage=(Stage) back.getScene().getWindow();
			 primaryStage.close();
		 });
	    
	    grid=new GridPane();
	    grid.setConstraints(back,7,5,1,2,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(txt, 14,5,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnew, 16,15,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnsta, 16,20,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnbal, 16,25,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnpen, 16,30,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnexport, 16,35,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(btnimport, 16,40,5,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.getChildren().addAll(txt,btnew,btnsta,btnbal,btnpen,btnexport,btnimport,back);
		
		stage=new Stage();
		root.getChildren().addAll(rect,grid);
		stage.setScene(scene);
		stage.show();
		
	}

}
