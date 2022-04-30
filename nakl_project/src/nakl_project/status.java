package nakl_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class status {

	
	Scene scene;
	Group root;
	GridPane grid,grid1;
	Rectangle rect;
	Text txt;
	Label lsrno,lname,lpage,linifee,lbalfee,ltotal,lready,ldel,lmob;
	TextField tsrno,tname,tpage,tinifee,tbalfee,ttotal,tmob;
	RadioButton readyyes,readyno,deliveryes,deliverno;
	ToggleGroup group1,group2;
	Button get,ok,back,print,sendsms;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	Stage stage;
        
	VBox vbox;
	Printer pprint;
	PageLayout pagelayout;
	PrinterJob job;
	//CheckBox readyyes,readyno,deliveryes,deliverno;
	public status() {
		con=mysql.getConnection();
		grid=new GridPane();
		root=new Group();
		scene=new Scene(root,680,720);
		
		rect=new Rectangle(scene.getHeight(),scene.getWidth());
		rect.setFill(Color.AZURE);
		rect.widthProperty().bind(scene.widthProperty());
		rect.heightProperty().bind(scene.heightProperty());
		
		txt=new Text("STATUS");
		txt.setFont(Font.font("Segoe Print",FontWeight.BOLD, FontPosture.ITALIC, 30));
		txt.setFill(Color.DARKCYAN);
		
		lsrno=new Label("SR NO.");
		lname=new Label("NAME");
		lpage=new Label("NO. OF PAGES");
		linifee=new Label("INITIAL FEE");
		ltotal=new Label("TOTAL FEE");
		lbalfee=new Label("BALANCE FEE");
		lready=new Label("READY TO DELIVER");
		ldel=new Label("DELIVERED");
		lmob=new Label("MOBILE NO.");
		
		tsrno=new TextField();
		tname=new TextField();
		tpage=new TextField();
		tinifee=new TextField();
		ttotal=new TextField();
		tbalfee=new TextField();
		tmob=new TextField();
		
		tname.setEditable(false);
		tinifee.setEditable(false);
		ttotal.setEditable(false);
		tbalfee.setEditable(false);
		tmob.setEditable(false);
		
		tsrno.setMaxWidth(200);
		tname.setMaxWidth(200);
		tpage.setMaxWidth(200);
		tinifee.setMaxWidth(200);
		ttotal.setMaxWidth(200);
		tbalfee.setMaxWidth(200);
		tmob.setMaxWidth(200);
		
		tsrno.textProperty().addListener(e->{
			doget();
		});
                
		tpage.textProperty().addListener(e->{
			 String ss=tpage.getText().trim();
                        //String ss=tpage.getText();
                        if(!ss.equals(""))
                        {
			Integer su=Integer.parseInt(ss);
                        
			//Integer su=Integer.valueOf(ss);
                       // String sto=""+tpage.getText()*20;
                        
                        //Integer stotal=su*20;
			String stot=""+su*20;
			
			String sini=tinifee.getText();
			Integer sin=Integer.parseInt(sini);
		        //String sini="20";
                        //Integer sin=Integer.parseInt(sini);
			
                        Integer sbal=(su*20)-sin;
			String sba=""+0;
		    if(!stot.equals("0"))
		    {
		     sba=""+sbal;
		    }
                     ttotal.setText(stot);
		    tbalfee.setText(sba);
                        }
		   
		});
	
		readyyes=new RadioButton("YES");
		readyno=new RadioButton("NO");
		deliveryes=new RadioButton("YES");
		deliverno=new RadioButton("NO");
		//deliveryes.setVisible(false);
		//deliverno.setVisible(false);
		
	    group1=new ToggleGroup();
	    group2=new ToggleGroup();
	    
	    readyyes.setToggleGroup(group1);
	    readyno.setToggleGroup(group1);
	    deliveryes.setToggleGroup(group2);
	    deliverno.setToggleGroup(group2);
		
		/*readyyes=new CheckBox("YES");
		readyno=new CheckBox("NO");
		deliveryes=new CheckBox("YES");
		deliverno=new CheckBox("NO");
		
//	    get=new Button("GET");
//	    get.setOnAction(e->{
//	    	doget();
//	    });*/
	    
	   /* if(readyyes.addEventFilter(eventType, eventFilter);)
	    {
	        deliveryes.setVisible(true);
	        deliverno.setVisible(true);
	    }*/
	    
	    ok=new Button("OK");
	    ok.setMaxSize(100, 100);
	    ok.setOnAction(e->{
	    	dok();
	    });
	    back=new Button("BACK");
		 back.setOnAction(e->{
			 Stage primaryStage=(Stage) back.getScene().getWindow();
			 primaryStage.close();
		 });
	   print=new Button("PRINT");
	   print.setOnAction(e->{
		   doprint();
	   });
	
           sendsms=new Button("SEND SMS");
           
                 sendsms.setOnAction(e->{
                  
                    sendsms();  
                 });
           
           
           
           grid.setConstraints(lsrno,10,3,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lname,10,5,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lmob,10,7,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lpage,10,9,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(linifee,10,11,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(ltotal,10,13,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lbalfee,10,15,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(lready,10,17,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(ldel,10,19,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tsrno, 15,3,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		//grid.setConstraints(get, 17,3,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tname, 15,5,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tmob,15,7,2,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tpage, 15,9,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tinifee, 15,11,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(ttotal, 15,13,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(tbalfee, 15,15,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(readyyes, 14,17,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(readyno, 15,17,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(deliveryes, 14,19,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.setConstraints(deliverno, 15,19,2, 2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid.getChildren().addAll(back,txt,lsrno,lname,lpage,linifee,ltotal,lbalfee,lready,ldel,tsrno,tname,tpage,
				tinifee,ttotal,tbalfee,readyyes,readyno,deliveryes,deliverno,ok,lmob,tmob);
		
		vbox=new VBox();
		vbox.getChildren().add(grid);
		
		grid1=new GridPane();
		grid1.setConstraints(back,7,1,1,1,HPos.CENTER, VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid1.setConstraints(txt,10,1,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid1.setConstraints(ok, 10,20,1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid1.setConstraints(vbox, 10,3,1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid1.setConstraints(print, 13,1,1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
                grid1.setConstraints(sendsms, 13,2,1, 1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10,10,10,10));
		grid1.getChildren().addAll(back,txt,ok,vbox,print,sendsms);
		
		stage=new Stage();
		root.getChildren().addAll(rect,grid1);
		stage.setScene(scene);
        stage.show();	
        
	}
	public void sendsms()
        {
            if(readyyes.isSelected())
           {
             String m="NAKAL DEPARTMENT:Your nakal is ready. You can collect it from DC office,BTI.";
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
           }       
        }
	public void doget()
	{
		String ready,del;
		try
		{
			pst=con.prepareStatement("select * from new_entry where SrNo=?");
			pst.setString(1,tsrno.getText());
			rs=pst.executeQuery();
			while(rs.next())
			{
				tname.setText(rs.getString("Name"));
				tinifee.setText(rs.getString("InitialFee"));
				tpage.setText(rs.getString("NoOfPages"));
				tmob.setText(rs.getString("MobileNo"));
				ready=rs.getString("ReadyToDeliver");
				if(ready.equals("YES"))
					readyyes.setSelected(true);
					else
					readyno.setSelected(true);
				
			    del=rs.getString("Delivered");
				if(del.equals("YES"))
					deliveryes.setSelected(true);
				else
					deliverno.setSelected(true);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void dok()
	{
		try 
		{
			
			String ready=null,del=null;
			if(readyyes.isSelected())
                        { ready="YES";
                            
                        }
                        if(readyno.isSelected())
				ready="NO";
			if(readyyes.isSelected())
			{
			   if(deliveryes.isSelected())
				   del="YES";
			}
			if(deliverno.isSelected())
				del="NO";
			Date date=new Date();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			String finaldate=sf.format(date);
			
			pst=con.prepareStatement("update new_entry set NoOfPages=?,BalanceFee=?,TotalFee=?,"
					+ "ReadyToDeliver=?,Delivered=?,ActualDate=? where SrNo=?");
	        pst.setString(1,tpage.getText());
	        pst.setString(2,tbalfee.getText());
	        pst.setString(3,ttotal.getText());
	        pst.setString(4,ready);
	        pst.setString(5,del);
	        pst.setString(6, finaldate);
	        pst.setString(7,tsrno.getText());
	        
			int rr=pst.executeUpdate();
	        doalert(""+rr);
	        
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void doalert(String rr)
	{
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setContentText("Record Updates:"+rr);
		alert.showAndWait();
	}
   public void doprint()
   {
	   pprint=Printer.getDefaultPrinter();
		System.out.println(Printer.getDefaultPrinter());
		pagelayout=pprint.createPageLayout(Paper.A4,PageOrientation.PORTRAIT,0.5f,0.5f,0.5f,0.25f);
		job=PrinterJob.createPrinterJob();
		if(job!=null)
		{
			boolean successPrintDialog=job.showPrintDialog(stage.getOwner());
			if(successPrintDialog)
			{
				//jobStatus.textProperty().bind(job.jobStatusProperty().asString());
				boolean success=job.printPage(vbox);
				if(success)
				{
					job.endJob();
				}
			}
		}
   }
}
