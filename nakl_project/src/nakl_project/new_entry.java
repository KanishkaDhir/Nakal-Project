package nakl_project;


import java.awt.PageAttributes;
import java.awt.print.PageFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MultipleDocumentHandling;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.text.JTextComponent;

import org.apache.poi.hssf.record.Margin;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.PrintResolution;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class new_entry  {

	

	Group root;
	GridPane grid,grid1,grid2;
	Rectangle rect;
	Label lsrno,lname,lmob,lapplydate,lvillage,linifee,lnakl,ltentdate,lfname,lgfname,lkhewat,lkhasra;
	TextField tsrno,tname,tmob,tapplydate,tvillage,tinifee,tnakl,ttentdate,tfname,tgfname,tkhewat,tkhasra;
	Text text,text1,text2,text3,text4,text5,text6;
	String str1,str2,str3,str4,str5,str6;
	ComboBox<String> cnakl;
	Date date;
	Calendar c; 
	SimpleDateFormat sf;
	DatePicker pickdate;
	Button save,close,print,close1,back,sendsms;
	Printer pprint;
	PageLayout pagelayout;
	javafx.print.PrinterJob job;
	PageAttributes attr;
	String ss;
	Connection con;
	PreparedStatement pst1,pst2;
	Stage primaryStage;
	ContextMenu namevalid,mobvalid;
	Integer ii;
	JTextComponent component;
	TextArea ta;
	Scene scene,scene1;
	VBox vbox1,vbox2,tbox;
	Integer a,b;
	public new_entry(){
		// TODO Auto-generated method stub
		con=mysql.getConnection();
		try
		{
			pst1=con.prepareStatement("select MAX(SrNo) as id from new_entry");
			ResultSet rs=pst1.executeQuery();
			if(rs.next())
			{
			 ss=rs.getString("id");
			}
		
		}
		
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		//System.out.println(ss);
		
		if(!(ss==null))
		{
	     ii=Integer.parseInt(ss);
	    ii++;
	    
		}
		else
		{
			ss="0";
			 ii=Integer.parseInt(ss);
			ii++;
			 
		}
		String sss=""+ii;
	    
		root=new Group();
	    scene=new Scene(root,920,720);
		
	    vbox1=new VBox();
	    a=0;
	    b=0;
		rect=new Rectangle(scene.getWidth(),scene.getHeight());
		rect.setFill(Color.AZURE);
		rect.widthProperty().bind(scene.widthProperty());
		rect.heightProperty().bind(scene.heightProperty());
		
		text=new Text("NEW APPLICANT");
		text.setFont(Font.font("Segoe Print",FontWeight.BOLD, FontPosture.ITALIC, 30));
		text.setFill(Color.DARKCYAN);
		text.setTextAlignment(TextAlignment.CENTER);
		
		lsrno=new Label("SR NO.");
		lname=new Label("NAME");
		lmob=new Label("MOBILE NO.");
		lapplydate=new Label("APPLIED DATE");
		lvillage=new Label("VILLAGE");
		linifee=new Label("INITIAL FEE");
		lnakl=new Label("NAKAL(COPY)");
		ltentdate=new Label("DELIVERY DATE");
		lfname=new Label("FATHER NAME");
		lgfname=new Label("GRANDFATHER NAME");
		lkhewat=new Label("KHEWAT NO.");
		lkhasra=new Label("KHASRA NO.");
		
		
		tsrno=new TextField();
		tname=new TextField();
		tmob=new TextField();
		tapplydate=new TextField();
		tvillage=new TextField();
		tinifee=new TextField();
		ttentdate=new TextField();
		tfname=new TextField();
		tgfname=new TextField();
		tkhewat=new TextField();
		tkhasra=new TextField();
		
		cnakl=new ComboBox<String>();
		ArrayList<String> arrnakl=new ArrayList<String>();
		arrnakl.add("Jamabandi");
		arrnakl.add("Mutation");
		arrnakl.add("Khasra Girdawari");
		arrnakl.add("Roznamcha");
		arrnakl.add("MAP");
		cnakl.getItems().addAll(arrnakl);
		cnakl.getSelectionModel().select(0);
		
		date=new Date();
		sf=new SimpleDateFormat("yyyy-MM-dd");
		c=Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 3); // Adding 3 days
		String output = sf.format(c.getTime());
		
		//pickdate=new DatePicker();
		
		tsrno.setEditable(false);
		tapplydate.setEditable(false);
		tvillage.setEditable(false);
        tinifee.setEditable(false);
        ttentdate.setEditable(false);
		
        tsrno.setText(sss);
		tapplydate.setText(sf.format(date));
		ttentdate.setText(output);
		tvillage.setText("Gil pati");
		tinifee.setText("20");
		
		tsrno.setMaxWidth(150);
		tname.setMaxWidth(150);
		tmob.setMaxWidth(150);
		tapplydate.setMaxWidth(150);
		tvillage.setMaxWidth(150);
		tinifee.setMaxWidth(150);
		cnakl.setMaxWidth(150);
		ttentdate.setMaxWidth(150);
		tfname.setMaxWidth(150);
		tgfname.setMaxWidth(150);
		tkhewat.setMaxWidth(150);
		tkhasra.setMaxWidth(150);
		
		
		tname.setPromptText("ur name");
		tmob.setPromptText("ur mobile no");
		tfname.setPromptText("ur father's name");
		tgfname.setPromptText("ur grandfather's name");
		tkhewat.setPromptText("ur khewat no.");
		tkhasra.setPromptText("ur khasra no.");
		
		namevalid=new ContextMenu();
		namevalid.setAutoHide(false);
		tname.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			{
			    if(newPropertyValue)
			    	namevalid.hide();
			    
			}
			
		});
		mobvalid=new ContextMenu();
		mobvalid.setAutoHide(false);
		tmob.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
			{
			    if(newPropertyValue)
			    	mobvalid.hide();
			}
			
		});
		
		save=new Button("SAVE");
		//save.setOnAction(e->dosave());
		save.setOnMouseClicked(event->{
			if(event.getClickCount()==2 )
			{
				doalert("DUPLICATE ENTRY FOR SAME SR NO.");
			}
			if(event.getClickCount()==1 )
			{
				dosave();
			}
		});
		print=new Button("PRINT");
		print.setOnAction(e->{
			if(a==1)
			{
				print(primaryStage,vbox1);
			}
			else
			{
				doalert("Firstly save the entry");
			}
		});
		
		save.setMaxSize(150,40);
		print.setMaxSize(150, 40);
		back=new Button("BACK");
		back.setOnAction(e->{
			
			 Stage primaryStage=(Stage) back.getScene().getWindow();
			 primaryStage.close();
			
			
		 });
		
		sendsms=new Button("SEND SMS");
		//save.setOnAction(e->dosave());
		sendsms.setOnMouseClicked(event->{
			
    	               String m="NAKAL DEPARTMENT:Your tentative date of delivery is "+ttentdate.getText()+". You will be updated in case of any changes.";
    	               String resp=SST_SMS.bceSunSoftSend(tmob.getText(), m);
    	               System.out.println(resp);
                       if(resp.indexOf("Exception")!=-1)
    	                	System.out.println("Check Internet Connection");
                       else
    		           if(resp.indexOf("successfully")!=-1)
                           { System.out.println("Sent");
                             doalert("Message Sent"); 
                           }
    		          else
    		               System.out.println( "Invalid Number");
		});
		sendsms.setMaxSize(150,40);
		
                
                
                str1="NOTE:In case holiday falls on delivery date. Next";
		str2="  working day would be the delivery date ";
		str3="FARD KENDRA,TEHSIL BATHINDA";
		str4="Authorized Signatory";
		str5="Fee charged is initial fee. Actual fee will";
		str6="  be charged as per the pages on delivery.";
		
		text1=new Text(str1);
		text1.setFont(Font.font("Verdana",FontWeight.NORMAL,FontPosture.REGULAR,12));
		text1.setTextAlignment(TextAlignment.CENTER);
		
		text2=new Text(str2);
		text2.setFont(Font.font("Verdana",FontWeight.NORMAL,FontPosture.REGULAR,12));
		text2.setTextAlignment(TextAlignment.CENTER);
		
		text3=new Text(str3);
		text3.setFont(Font.font("Verdana",FontWeight.BOLD,FontPosture.REGULAR,16));
		text3.setTextAlignment(TextAlignment.CENTER);
		
		text4=new Text(str4);
		text4.setFont(Font.font("Verdana",FontWeight.NORMAL,FontPosture.REGULAR,12));
		text4.setTextAlignment(TextAlignment.CENTER);
		
		text5=new Text(str5);
		text5.setFont(Font.font("Verdana",FontWeight.NORMAL,FontPosture.REGULAR,12));
		text5.setTextAlignment(TextAlignment.CENTER);
		
		text6=new Text(str6);
		text6.setFont(Font.font("Verdana",FontWeight.NORMAL,FontPosture.REGULAR,12));
		text6.setTextAlignment(TextAlignment.CENTER);
		
		    grid1=new GridPane();
		    grid1.setGridLinesVisible(false);
		    grid1.setConstraints(text3,8,2,10,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(lsrno,8,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tsrno,9,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(lname,11,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tname,12,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid1.setConstraints(lfname,8,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tfname,9,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(lgfname,11,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tgfname,12,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid1.setConstraints(lvillage,8,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tvillage,9,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(lmob,11,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tmob,12,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid1.setConstraints(lkhewat,8,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tkhewat,9,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(lkhasra,11,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tkhasra,12,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid1.setConstraints(lnakl,8,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(cnakl,9,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(linifee,11,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tinifee,12,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		
			grid1.setConstraints(lapplydate,8,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(tapplydate,9,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(ltentdate,11,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid1.setConstraints(ttentdate,12,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid1.setConstraints(text1,8,21,2,1,HPos.RIGHT,VPos.CENTER,null,null,new Insets(10,0,10,10));
			grid1.setConstraints(text2,11,21,2,1,HPos.LEFT,VPos.CENTER,null,null,new Insets(10,10,10,0));
			grid1.setConstraints(text5,8,22,2,1,HPos.RIGHT,VPos.CENTER,null,null,new Insets(10,0,10,30));
			grid1.setConstraints(text6,11,22,2,1,HPos.LEFT,VPos.CENTER,null,null,new Insets(10,10,10,0));
			grid1.setConstraints(text4,12,27,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(30,10,10,10));
			
			
            grid1.getChildren().addAll(text3,text4,lsrno,lname,lfname,lgfname,lkhewat,lkhasra,lvillage,lmob,
            		lnakl,linifee,lapplydate,ltentdate,tsrno,tname,tfname,tgfname,tkhewat,tkhasra,tvillage,
            		tmob,cnakl,tinifee,tapplydate,ttentdate,text1,text2,text5,text6);
           
		  vbox1.getChildren().add(grid1);
		 
		

		  
		grid=new GridPane();
		grid.setConstraints(back,7,1,1,1,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(text,8,1,1,1,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(vbox1,8,2,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(save,8,21,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(print,8,22,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
	        grid.setConstraints(sendsms,8,23,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		//grid.setConstraints(ta,19,23,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		
		grid.getChildren().addAll(back,text,vbox1,save,print,sendsms);
		
		root.getChildren().addAll(rect,grid);
	    primaryStage=new Stage();
		primaryStage.setScene(scene);
	    primaryStage.show();
	    //,lsrno,lname,lvillage,lmob,lnakl,linifee,lapplydate,ltentdate,tsrno,tname,tvillage,tmob,cnakl,tinifee,tapplydate,ttentdate
		
	}


	public void print(Stage primarystage,Node vbox)
	{
		
//		tbox=new VBox();
//		tbox.getChildren().addAll(vbox,vbox);

		
		vbox2=new VBox();
		
		    grid2=new GridPane();
		    grid2.setGridLinesVisible(false);
		    grid2.setConstraints(text3,8,2,10,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(lsrno,8,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tsrno,9,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(lname,11,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tname,12,5,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(lfname,8,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tfname,9,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(lgfname,11,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tgfname,12,7,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(lvillage,8,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tvillage,9,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(lmob,11,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tmob,12,9,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(lkhewat,8,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tkhewat,9,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(lkhasra,11,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tkhasra,12,11,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(lnakl,8,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(cnakl,9,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(linifee,11,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tinifee,12,13,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(lapplydate,8,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(tapplydate,9,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(ltentdate,11,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			grid2.setConstraints(ttentdate,12,17,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
			
			grid2.setConstraints(text1,8,21,2,1,HPos.RIGHT,VPos.CENTER,null,null,new Insets(10,0,10,10));
			grid2.setConstraints(text2,11,21,2,1,HPos.LEFT,VPos.CENTER,null,null,new Insets(10,10,10,0));
			grid2.setConstraints(text5,8,22,2,1,HPos.RIGHT,VPos.CENTER,null,null,new Insets(10,0,10,30));
			grid2.setConstraints(text6,11,22,2,1,HPos.LEFT,VPos.CENTER,null,null,new Insets(10,10,10,0));
			grid2.setConstraints(text4,12,27,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(30,10,10,10));
			
			
            grid2.getChildren().addAll(text3,text4,lsrno,lname,lfname,lgfname,lkhewat,lkhasra,lvillage,
            		lmob,lnakl,linifee,lapplydate,ltentdate,tsrno,tname,tfname,tgfname,tkhewat,tkhasra,
				tvillage,tmob,cnakl,tinifee,tapplydate,ttentdate,text1,text2,text5,text6);
		  vbox2.getChildren().add(grid2);
		 
		 
		  tbox=new VBox();
		  tbox.getChildren().addAll(vbox1,vbox2);
			pprint =Printer.getDefaultPrinter();
			job=PrinterJob.createPrinterJob();
		MultipleDocumentHandling mdh=MultipleDocumentHandling.SINGLE_DOCUMENT;
          job.getJobSettings().setCopies(2);	
			pagelayout=pprint.createPageLayout(Paper.LEGAL, PageOrientation.REVERSE_PORTRAIT,MarginType.HARDWARE_MINIMUM);
		   // pagelayout=job.getPrinter().getDefaultPageLayout();	
			
			//tbox.setPrefWidth(pagelayout.getPrintableHeight());
			//tbox.setPrefHeight(pagelayout.getPrintableHeight());
			//1
			/*double ScaleX=pagelayout.getPrintableHeight();
			double ScaleY=pagelayout.getPrintableWidth();
			Scale scale=new Scale(ScaleX,ScaleY);
			vbox.getTransforms().add(scale);*/
			
			//2
			tbox.setPrefSize(pagelayout.getPrintableWidth(),pagelayout.getPrintableHeight());
			
			//4
			/*double width=pagelayout.getPrintableWidth();
			double height=pagelayout.getPrintableHeight();
		    vbox.setPrefHeight(1254);
			System.out.println("width:"+width+"height="+height);
			PrintResolution reso=job.getJobSettings().getPrintResolution();
			width /= reso.getFeedResolution();
			height /= reso.getCrossFeedResolution();
			System.out.println("width:"+width+"height="+height);
			
			double scaleX = pagelayout.getPrintableWidth() / 72 / width;
			double scaleY = pagelayout.getPrintableHeight() / 72 / height;
			System.out.println("widthX:"+scaleX+"heightY="+scaleY);
			Scale scale1=new Scale(width,height);
			vbox.getTransforms().add(scale1);*/
			
			
			//PrinterAttributes pa=pprint.
			//job.showPageSetupDialog(primarystage.getOwner());
			
			if(job!=null)
			{
				boolean success=job.showPrintDialog(primarystage.getOwner());
				if(success)
				{
					boolean suc=job.printPage(pagelayout,tbox);
					if(suc)
					{
						job.endJob();
					}
				}
			}

	}
	public void dosave()
	{
		if(tname.getText().equals("") || tname.getText().equals("[1-5]\\.[0-9]|6\\.0"))
		{
			namevalid.getItems().clear();
			namevalid.getItems().add(new MenuItem("Please enter name"));
			namevalid.show(tname,Side.RIGHT,10,0);
		}
		/*if(tname.getText().equals("[1-5]\\.[0-9]|6\\.0"))
		{
            namevalid.getItems().clear();
			namevalid.getItems().add(new MenuItem("only characters allowed"));
			namevalid.show(tname,Side.RIGHT,10,0);
        }*/
		if(tmob.getText().equals(""))
		{
			mobvalid.getItems().clear();
			mobvalid.getItems().add(new MenuItem("Please enter mobile no."));
			mobvalid.show(tmob,Side.RIGHT,10,0);
		}
		if(!tname.getText().equals("") && !tmob.getText().equals("") && !tname.getText().equals("[1-5]\\.[0-9]|6\\.0"))
		{ a=1;
		try 
		{
			//LocalDate dd=pickdate.getValue();
			//String tt=dd.toString();
			//System.out.println(tt);
			pst2=con.prepareStatement("insert into new_entry(SrNo,Name,Fname,GFname,Village,MobileNo,KhewatNo,"
					+ "KhasraNo,Nakl,InitialFee,AppliedDate,TentDeliveryDate,NoOfPages,BalanceFee,TotalFee,"
					+ "ReadyToDeliver,Delivered,ActualDate) values(?,?,?,?,?,?,?,?,?,?,?,?,'0','0','0','NO','NO','NOT YET DELIVERED')");
		    pst2.setString(1,tsrno.getText());
		    pst2.setString(2,tname.getText());
                    pst2.setString(3,tfname.getText());
		    pst2.setString(4,tgfname.getText());
		    pst2.setString(5,tvillage.getText());
		    pst2.setString(6,tmob.getText());
		    pst2.setString(7,tkhewat.getText());
		    pst2.setString(8,tkhasra.getText());
		    pst2.setString(9,cnakl.getValue());
		    pst2.setString(10,tinifee.getText());
		    pst2.setString(11,tapplydate.getText());
		    pst2.setString(12,ttentdate.getText());
		   
//		    pst2.setString(9,"0");
//		    pst2.setString(10,"0");
//		    pst2.setString(11,"0");
//		    pst2.setString(12,"NO");
//		    pst2.setString(13,"NO");
		    int res=pst2.executeUpdate();
		    doalert("Sr No. "+tsrno.getText()+": record saved");
		   
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
	void doalert(String s)
	{
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setContentText(s);
		alert.showAndWait();
	}


	
	/*public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
		
		return 0;
	}*/
}
