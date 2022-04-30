package nakl_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class balance {


	Scene scene;
	Group root;
	GridPane grid;
	Rectangle rect;
	Connection con;
	Text txt;
	TextField txtbal,txtapp;
	Label lfrom,lto;
	DatePicker dfrom,dto;
	RadioButton radioready,radiodeliver,radioapplied;
	ToggleGroup group1;
	TableView<applicant_ready> tready;
	VBox vbox;
	ScrollPane sp;
	PreparedStatement ps;
	Button go,totalini,totaltt,back;
	Stage stage;
	Integer sumapp,sumdel,sumbal;
	Printer pprint;
	PageLayout pagelayout;
	PrinterJob job;
	ResultSet rs;
	public balance() {
		// TODO Auto-generated method stub
		con=mysql.getConnection();
		root=new Group();
		scene=new Scene(root,1050,680);
		
		rect=new Rectangle(scene.getWidth(),scene.getHeight());
		rect.setFill(Color.AZURE);
		rect.widthProperty().bind(scene.widthProperty());
		rect.heightProperty().bind(scene.heightProperty());
		
		txt=new Text("BALANCE");
		txt.setFont(Font.font("Segoe Print",FontWeight.BOLD,FontPosture.ITALIC,30));
		txt.setFill(Color.DARKCYAN);
		
		txtbal=new TextField();
		txtbal.setVisible(false);
		txtbal.setEditable(false);
		
		txtapp=new TextField();
		txtapp.setVisible(false);
		txtapp.setEditable(false);
		
		lfrom=new Label("FROM");
		lto=new Label("TO");
		
		dfrom=new DatePicker();
		dto=new DatePicker();
		

		String pattern = "yyyy-MM-dd";

		dfrom.setPromptText(pattern.toLowerCase());

		dfrom.setConverter(new StringConverter<LocalDate>() {
		     
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
		
		dto.setPromptText(pattern.toLowerCase());

		dto.setConverter(new StringConverter<LocalDate>() {
		     
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
		
		
		
		group1=new ToggleGroup();
		radioready=new RadioButton("READY TO DELIVERY");
		radiodeliver=new RadioButton("DELIVERED");
		radioapplied=new RadioButton("APPLIED");
		radioready.setToggleGroup(group1);
		radiodeliver.setToggleGroup(group1);
		radioapplied.setToggleGroup(group1);
		
		
		go=new Button("GO");
		go.setOnAction(e->go());
		
		totaltt=new Button("SUM TOTAL ");
		totaltt.setVisible(false);
		
		back=new Button("BACK");
		 back.setOnAction(e->{
			 Stage primaryStage=(Stage) back.getScene().getWindow();
			 primaryStage.close();
		 });
		
		 radioapplied.selectedProperty().addListener(new ChangeListener<Boolean>(){

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				txtapp.setVisible(true);
				totaltt.setVisible(false);
				txtbal.setVisible(false);
			}
			 
		 });
			
		 radioready.selectedProperty().addListener(new ChangeListener<Boolean>(){

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					txtapp.setVisible(false);
					totaltt.setVisible(false);
					txtbal.setVisible(true);
				}
				 
			 });
		 
		 radiodeliver.selectedProperty().addListener(new ChangeListener<Boolean>(){

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					txtapp.setVisible(false);
					totaltt.setVisible(true);
					txtbal.setVisible(true);
				}
				 
			 });
		 
		tready=new TableView<>();
		
		TableColumn<applicant_ready,String> colsrno=new TableColumn<>("SR NO.");
		colsrno.setMinWidth(80);
		colsrno.setCellValueFactory(new PropertyValueFactory<>("SrNo"));
		
		TableColumn<applicant_ready,String> colname=new TableColumn<>("NAME");
		colname.setMinWidth(100);
		colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
		

		TableColumn<applicant_ready,String> colmob=new TableColumn<>("MOBILE NO.");
		colmob.setMinWidth(100);
		colmob.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
		
		
		TableColumn<applicant_ready,String> colnakl=new TableColumn<>("NAKL");
		colnakl.setMinWidth(90);
		colnakl.setCellValueFactory(new PropertyValueFactory<>("Nakl"));
		
		TableColumn<applicant_ready,String> colpage=new TableColumn<>("NO.OF PAGES");
		colpage.setMinWidth(80);
		colpage.setCellValueFactory(new PropertyValueFactory<>("NoOfPages"));
		
		TableColumn<applicant_ready,String> colini=new TableColumn<>("INITIAL FEE");
		colini.setMinWidth(80);
		colini.setCellValueFactory(new PropertyValueFactory<>("InitialFee"));
		
		TableColumn<applicant_ready,String> colbal=new TableColumn<>("BALANCE FEE");
		colbal.setMinWidth(100);
		colbal.setCellValueFactory(new PropertyValueFactory<>("BalanceFee"));
		
		TableColumn<applicant_ready,String> coltot=new TableColumn<>("TOTAL FEE");
		coltot.setMinWidth(100);
		coltot.setCellValueFactory(new PropertyValueFactory<>("TotalFee"));	
		
		TableColumn<applicant_ready,String> colapp=new TableColumn<>("APPLIED DATE");
		colapp.setMinWidth(100);
		colapp.setCellValueFactory(new PropertyValueFactory<>("AppliedDate"));	
		
		TableColumn<applicant_ready,String> coldel=new TableColumn<>("DELIVERY DATE");
		coldel.setMinWidth(100);
		coldel.setCellValueFactory(new PropertyValueFactory<>("TentativeDeliveryDate"));	
		
		TableColumn<applicant_ready,String> colact=new TableColumn<>("ACTUAL DATE");
		colact.setMinWidth(100);
		colact.setCellValueFactory(new PropertyValueFactory<>("ActualDate"));	
		
		tready.getColumns().addAll(colsrno,colname,colmob,colnakl,colpage,colini,colbal,coltot,colapp,coldel,colact);
		
		
	   sp=new ScrollPane();
	   sp.setContent(tready);
		sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
//		sp.fitToHeightProperty();
//		sp.fitToWidthProperty();
//		System.out.println(sp.getHvalue());
//		sp.getHmax();
		
        vbox=new VBox();
         vbox.getChildren().addAll(tready,sp);
	    
		
		grid=new GridPane();
		grid.setConstraints(back,7,1,1,1,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(txt,8,1,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lfrom,7,4,3,3,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(dfrom,9,4,3,3,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lto,12,4,3,3,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(dto,14,4,3,3,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(radioapplied,9,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(radioready,12,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(radiodeliver,14,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(vbox,7,9,30,30,HPos.CENTER,VPos.CENTER,null,null,new Insets(0,0,0,0));
		grid.setConstraints(go,16,7,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,20));
		grid.setConstraints(txtapp,9,80,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(txtbal,12,80,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(totaltt,14,80,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		//grid.setConstraints(print,16,1,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));

		grid.getChildren().addAll(back,txt,lfrom,lto,dfrom,dto,radioapplied,radioready,radiodeliver,
				vbox,go,txtapp,txtbal,totaltt);
		
		stage=new Stage();
		root.getChildren().addAll(rect,grid);
		stage.setScene(scene);
		stage.show();
	}
	public void go()
	{  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String sf = (dfrom.getValue()).format(formatter);
		String st = (dto.getValue()).format(formatter);
		
		
		if(radioapplied.isSelected())
		{		
			sumapp=0;
		       ObservableList<applicant_ready> all=FXCollections.observableArrayList();
		       try
		       {
		         ps=con.prepareStatement("select * from new_entry where AppliedDate between ? and ?");
		         ps.setString(1, sf);
		         ps.setString(2, st);
		         rs=ps.executeQuery();
		         
		         XSSFWorkbook wb=new XSSFWorkbook();
		 		XSSFSheet sheet=wb.createSheet("Balance.xlsx");
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
		 		header.createCell(10).setCellValue("ActualDate");
		 		
		 		
		 		int index=1;
		 	
		         while(rs.next())
		          {
			        applicant_ready ar=new applicant_ready(rs.getString("SrNo"),rs.getString("Name"),rs.getString("MobileNo"),
			        		rs.getString("Nakl"),rs.getString("NoOfPages"),rs.getString("InitialFee"),rs.getString("BalanceFee"),
					   rs.getString("TotalFee"),rs.getString("AppliedDate"),rs.getString("TentDeliveryDate"),rs.getString("ActualDate"));
			        
			        all.add(ar);
			        sumapp=sumapp+Integer.parseInt(rs.getString("InitialFee"));
			        

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
					row.createCell(10).setCellValue(rs.getString("ActualDate"));
					
					index++;
					
					
					
					
		          }
		         txtapp.setText("Initial Fee:"+sumapp);
		         FileOutputStream fileout;
					
					fileout = new FileOutputStream("Balance.xlsx");
					wb.write(fileout);
					fileout.close();
							
	            } 
		         catch (Exception e)
		          {
			         e.printStackTrace();
	              }
		          tready.setItems(all);
		         
		}
		if(radioready.isSelected())
		{		
			sumbal=0;
		       ObservableList<applicant_ready> all=FXCollections.observableArrayList();
		       try
		       {
		    	   System.out.println("hi");
		         ps=con.prepareStatement("select * from new_entry where ReadyToDeliver='YES' and Delivered='NO' and ActualDate >=? and ActualDate<= ?");// 
                        
		         ps.setString(1, sf);
		       ps.setString(2, st);
		         System.out.print(""+ps);
                         rs=ps.executeQuery();
                     
		        
		        XSSFWorkbook wb=new XSSFWorkbook();
			 		XSSFSheet sheet=wb.createSheet("Balance.xlsx");
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
			 		header.createCell(10).setCellValue("ActualDate");
			 		
			 		
			 		int index=1;
		         while(rs.next())
		          {
		        	 System.out.println(rs.getInt("SrNo"));
		        	// System.out.println(rs.getString("SrNo"));
			        applicant_ready ar=new applicant_ready(rs.getString("SrNo"),rs.getString("Name"),rs.getString("MobileNo"),
			        		rs.getString("Nakl"),rs.getString("NoOfPages"),rs.getString("InitialFee"),rs.getString("BalanceFee"),
					   rs.getString("TotalFee"),rs.getString("AppliedDate"),rs.getString("TentDeliveryDate"),rs.getString("ActualDate"));
			        all.add(ar);
			        sumbal=sumbal+Integer.parseInt(rs.getString("BalanceFee"));
                           
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
                                        row.createCell(10).setCellValue(rs.getString("ActualDate"));
					
					index++;
					System.out.println(index);
		          }
		            // totalbal.setOnAction(e->total(sumbal));
		         txtbal.setText("Balance Fee:"+sumbal);
		         FileOutputStream fileout;
					
					fileout = new FileOutputStream("Balance.xlsx");
					wb.write(fileout);
					fileout.close();
				
		            
	            } 
		         catch (Exception e)
		          {
			         e.printStackTrace();
	              }
		          tready.setItems(all);
		}
		if(radiodeliver.isSelected())
		{		
			sumdel=0;
			sumbal=0;
		       ObservableList<applicant_ready> all=FXCollections.observableArrayList();
		       try
		       {
		         ps=con.prepareStatement("select new_entry.* from new_entry  where Delivered='YES' and ActualDate between ? and ?");
		         ps.setString(1, sf);
		         ps.setString(2, st);
                         System.out.print(""+ps);
		         rs=ps.executeQuery();
                       
		         XSSFWorkbook wb=new XSSFWorkbook();
			 		XSSFSheet sheet=wb.createSheet("Balance.xlsx");
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
			 		header.createCell(10).setCellValue("ActualDate");
			 		
			 		
			 		int index=1;
		         while(rs.next())
		          {
			        applicant_ready ar=new applicant_ready(rs.getString("SrNo"),rs.getString("Name"),rs.getString("MobileNo"),
			        		rs.getString("Nakl"),rs.getString("NoOfPages"),rs.getString("InitialFee"),rs.getString("BalanceFee"),
					   rs.getString("TotalFee"),rs.getString("AppliedDate"),rs.getString("TentDeliveryDate"),rs.getString("ActualDate"));
			        all.add(ar);
			        sumdel=sumdel+Integer.parseInt(rs.getString("TotalFee"));
			        sumbal=sumbal+Integer.parseInt(rs.getString("BalanceFee"));
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
					row.createCell(10).setCellValue(rs.getString("ActualDate"));					

					index++;
					
					
		          }
		         txtbal.setText("Balance Fee:"+sumbal);
		         totaltt.setOnAction(e->total(sumdel));
		         FileOutputStream fileout;
					
					fileout = new FileOutputStream("Balance.xlsx");
					wb.write(fileout);
					fileout.close();
		        
	            } 
		         catch (Exception e)
		          {
			         e.printStackTrace();
	              }
		          tready.setItems(all);
		}
	}
	public void alert(int sum)
	{
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("TOTAL IS");
		alert.setContentText("TOTAL FEE:"+sum);
		alert.showAndWait();
	}
	public void total(int sum)
	{
		alert(sum);
		
	}
	
	
}
