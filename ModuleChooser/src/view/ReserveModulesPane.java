package view;



import java.util.ArrayList;
import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import model.Module;

public class ReserveModulesPane extends Accordion {
	private Label lblUnselectedTerm1, lblReservedTerm1 ,lblReserve , lblUnselectedTerm2,lblReservedTerm2, lblReserve2;
	private TitledPane t1,t2;
	private Button btnAdd,btnRemove, btnConfirm, btnAdd1,btnRemove1,btnConfirm1;
	private ListView<Module> lstUnselectedTerm1, lstReservedTerm1, lstUnselectedTerm2, lstReservedTerm2;
	public ReserveModulesPane() {
		
		
		RowConstraints row0 = new RowConstraints();
		RowConstraints row1 = new RowConstraints();
		row1.setVgrow(Priority.ALWAYS);
		row1.setPercentHeight(90);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(50);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		
		column0.setHalignment(HPos.LEFT);
		column1.setHalignment(HPos.LEFT);
		GridPane gridpane1;
		GridPane gridpane2;
		gridpane1= new GridPane();
		gridpane2= new GridPane();
		
		//gridpane1.getColumnConstraints().addAll(column0,column1);
		gridpane1.getRowConstraints().addAll(row0,row1);
		gridpane2.getRowConstraints().addAll(row0,row1);
		
		Label lblUnselectedTerm1 = new Label("Unselected Term1 modules");
		Label lblReservedTerm1 = new Label("Reserved Term1 modules");
		Label lblUnselectedTerm2 = new Label("Unselected Term2 modules");
		Label lblReservedTerm2 = new Label("Reserved Term2 modules");
		
		lstUnselectedTerm1 = new ListView<Module>();
		lstReservedTerm1 = new ListView<Module>();
		lstUnselectedTerm2 = new ListView<Module>();
		lstReservedTerm2 = new ListView<Module>();
		
		
		Label lblReserve = new Label("Reserve 30 credits worth of term 1 modules");
		Label lblReserve1 = new Label("Reserve 30 credits worth of term 2 modules");
		
		btnAdd = new Button ("ADD");
		btnRemove = new Button ("Remove");
		btnConfirm = new Button ("Confirm");
		btnAdd1 = new Button ("ADD");
		btnRemove1= new Button ("Remove");
		btnConfirm1 = new Button ("Confirm");
		HBox HBox1= new HBox(lblReserve,btnAdd,btnRemove,btnConfirm);
		HBox1.setAlignment(Pos.BASELINE_CENTER);
		HBox1.setSpacing(10);
		
		
		lstUnselectedTerm1.setPrefWidth(column1.getPercentWidth());
		//this.getChildren().add(lblUnselectedTerm1);
		gridpane1.add(lblUnselectedTerm1, 0, 0);
		gridpane1.add(lstUnselectedTerm1,0,1);
		gridpane1.add(lblReservedTerm1, 1, 0);
		gridpane1.add(lstReservedTerm1, 1, 1);
		gridpane1.add(HBox1, 0, 2);
		gridpane1.setHgap(10);
		gridpane1.setAlignment(Pos.TOP_CENTER);
		
		gridpane1.getColumnConstraints().addAll(column0,column1);
		
		
		
		HBox HBox2= new HBox(lblReserve1,btnAdd1,btnRemove1,btnConfirm1);
		HBox2.setAlignment(Pos.BASELINE_CENTER);
		HBox2.setSpacing(10);
		gridpane2.add(lblUnselectedTerm2, 0, 0);
		gridpane2.add(lstUnselectedTerm2,0,1);
		gridpane2.add(lblReservedTerm2, 1, 0);
		gridpane2.add(lstReservedTerm2, 1, 1);
		gridpane2.add(HBox2, 0, 2);
		gridpane2.setHgap(10);
		gridpane2.setAlignment(Pos.TOP_CENTER);
		gridpane2.getColumnConstraints().addAll(column0,column1);
		
		
		//this.getChildren().add(gridpane1);
		this.setPadding(new Insets(25, 25, 25, 25));
		//this.getChildren().add(HBox1);
		//this.setAlignment(Pos.CENTER);
		t1 = new TitledPane("Term1 modules", gridpane1);
		t2 = new TitledPane("Term2 modules", gridpane2);
		this.getPanes().addAll(t1,t2);
	}
	
	public void addbtnTerm1(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	public void removeTerm1(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	public void addbtnTerm2(EventHandler<ActionEvent> handler) {
		btnAdd1.setOnAction(handler);
	}
	public void removeTerm2(EventHandler<ActionEvent> handler) {
		btnRemove1.setOnAction(handler);
	}
	public void submitTerm1(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	public void submitTerm2(EventHandler<ActionEvent> handler) {
		btnConfirm1.setOnAction(handler);
	}
	public void unselectedFill(ObservableList<Module> modules)
	{
		
		 for (Module module : modules) {
			lstUnselectedTerm1.getItems().add(module);		
		}
	}
public void unselectedFill1(ObservableList<Module> modules)
	{
		 for (Module module : modules) {
			lstUnselectedTerm2.getItems().add(module);
		 }
	}

	public void addSelected()
	{
		
		
		if (getUnselectedTerm1() == null)
		{
			
		}
		else
		{
			lstReservedTerm1.getItems().addAll(getUnselectedTerm1());
		}
		
	}
	public void removeSelected()
	{
	
		lstUnselectedTerm1.getItems().remove(getUnselectedTerm1());
		
	}
	public  Module getUnselectedTerm1()
	{
		return lstUnselectedTerm1.getSelectionModel().getSelectedItem();
	}
	public Module getReservedTerm1()
	{
		return lstReservedTerm1.getSelectionModel().getSelectedItem();
	}
	public void removeSelected1()
	{
		lstReservedTerm1.getItems().remove(getReservedTerm1());
	}
	public void addSelected1()
	{
		if (getReservedTerm1() == null)
		{
			
		}
		else
		{
			lstUnselectedTerm1.getItems().addAll(getReservedTerm1());
		}
		
	}
	//term 2
	
	public void addSelected2()
	{
		
		
		if (getUnselectedTerm2() == null)
		{
			
		}
		else
		{
			lstReservedTerm2.getItems().addAll(getUnselectedTerm2());
		}
		
	}
	public void removeSelected2()
	{
	
		lstUnselectedTerm2.getItems().remove(getUnselectedTerm2());
		
	}
	public  Module getUnselectedTerm2()
	{
		return lstUnselectedTerm2.getSelectionModel().getSelectedItem();
	}
	public Module getReservedTerm2()
	{
		return lstReservedTerm2.getSelectionModel().getSelectedItem();
	}
	public void removeSelected3()
	{
		lstReservedTerm2.getItems().remove(getReservedTerm2());
	}
	public void addSelected3()
	{
		if (getReservedTerm2() == null)
		{
			
		}
		else
		{
			lstUnselectedTerm2.getItems().addAll(getReservedTerm2());
		}
		
	}
	public Collection<Module> getReservedModules1()
	{
		Collection<Module> List1 = new ArrayList<Module>();
		int tempTerm1Reserved = lstReservedTerm1.getItems().size();
		for(int i=0;i<tempTerm1Reserved;i++)
		{
			List1.add(lstReservedTerm1.getItems().get(i));
		
		}
		return List1;
	}
	public Collection<Module> getReservedModules2()
	{
		Collection<Module> List1 = new ArrayList<Module>();
		int tempTerm2Reserved = lstReservedTerm2.getItems().size();
		for(int i=0;i<tempTerm2Reserved;i++)
		{
			List1.add(lstReservedTerm2.getItems().get(i));
		
		}
		return List1;
	}
}