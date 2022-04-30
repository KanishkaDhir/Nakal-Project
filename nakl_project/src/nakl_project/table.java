package nakl_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class table extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       launch(args);
	}

	TableView<applicant_ready> tready;
	Scene scene;
	public void start(Stage st) throws Exception {
		// TODO Auto-generated method stub
          tready=new TableView<>();
		
		TableColumn<applicant_ready,String> colsrno=new TableColumn<>("SR NO.");
		colsrno.setMinWidth(100);
		colsrno.setCellValueFactory(new PropertyValueFactory<>("SrNo"));
		
		TableColumn<applicant_ready,String> colname=new TableColumn<>("NAME");
		colname.setMinWidth(100);
		colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
//		TableColumn<applicant_ready,String> colmob=new TableColumn<>("MOBILE NO.");
//		colmob.setMinWidth(100);
//		colmob.setCellValueFactory(new PropertyValueFactory<>("MobileNo"));
//		
		TableColumn<applicant_ready,String> colnakl=new TableColumn<>("NAKL");
		colnakl.setMinWidth(100);
		colnakl.setCellValueFactory(new PropertyValueFactory<>("Nakl"));
		
		TableColumn<applicant_ready,String> colpage=new TableColumn<>("NO. OF PAGES");
		colpage.setMinWidth(100);
		colpage.setCellValueFactory(new PropertyValueFactory<>("NoOfPages"));
		
		TableColumn<applicant_ready,String> colini=new TableColumn<>("INITIAL FEE");
		colini.setMinWidth(100);
		colini.setCellValueFactory(new PropertyValueFactory<>("InitialFee"));
		
		TableColumn<applicant_ready,String> colbal=new TableColumn<>("BALANCE FEE");
		colbal.setMinWidth(100);
		colbal.setCellValueFactory(new PropertyValueFactory<>("BalanceFee"));
		
		TableColumn<applicant_ready,String> coltot=new TableColumn<>("TOTAL FEE");
		coltot.setMinWidth(100);
		coltot.setCellValueFactory(new PropertyValueFactory<>("TotalFee"));	
		
		tready.getColumns().addAll(colsrno,colname,colnakl,colpage,colini,colbal,coltot);
		
		scene=new Scene(tready,800,800);
		st.setScene(scene);
		st.show();
	}

}
