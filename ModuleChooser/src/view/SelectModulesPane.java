package view;

import java.awt.Container;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.TreeSet;

import controller.ModuleChooserController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.Schedule;
import model.StudentProfile;
import javafx.scene.control.ListView;

public class SelectModulesPane extends BorderPane{
	private ListView<model.Module> lstTerm1Unselected, lstTerm2Unselected,  lstTerm1Selected, lstTerm2Selected ,lstSelectedAllYear, lstTerm1Credits, lstTerm2Credits;
	private Button Term1Add, Term1Remove, Term2Add, Term2Remove, Reset, Submit;
	private TextField txtTerm1Credits, txtTerm2Credits;
	private List list1;
	
	public SelectModulesPane(){
		
		
		
		list1 = new List();
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(45);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(45);
		
		column0.setHalignment(HPos.LEFT);
		column1.setHalignment(HPos.LEFT);
		
		this.setPadding(new Insets (20));
		
		
		
		
		//list1.add(item);

		
		//labels
		Label lblTerm1Unselected = new Label("Unselected Term 1 modules");
		Label lblTerm2Unselected = new Label("Unselected Term 2 modules");
		Label lblTerm1 = new Label("Term 1");
		Label lblTerm2 = new Label("Term 2");
		Label lblYearLongModules = new Label("Selected Year Long modules");
		Label lblTerm1Selected= new Label("Selected Term 1 modules");
		Label lblTerm2Selected= new Label("Selected Term 2 modules");
		Label lblTerm1Credits = new Label("Current term 1 credits");
		Label lblTerm2Credits = new Label("Current term 2 credits");
		//ListViews
		lstTerm1Unselected = new ListView();
		lstTerm2Unselected = new ListView();
		lstTerm1Selected = new ListView();
		lstTerm2Selected = new ListView();
		lstSelectedAllYear =	new ListView();
		lstTerm1Credits = new ListView();
		lstTerm2Credits = new ListView();
		//Buttons 
		Term1Add = new Button("ADD");
		Term1Remove = new Button("REMOVE");
		Term2Add = new Button("ADD");
		Term2Remove = new Button("REMOVE");
		Reset = new Button("RESET");
		Submit = new Button("SUBMIT");
		//Textfields
		txtTerm1Credits= new TextField("0");
		txtTerm2Credits= new TextField("0");
		txtTerm1Credits.setMaxWidth(35);
		txtTerm2Credits.setMaxWidth(35);
		
		HBox Term1=new HBox(lblTerm1,Term1Add,Term1Remove);
		Term1.setSpacing(15);
		Term1.setAlignment(Pos.CENTER);
		
		HBox Term2=new HBox(lblTerm2,Term2Add,Term2Remove);
		Term2.setSpacing(15);
		Term2.setAlignment(Pos.CENTER);
		
		HBox CurrentTerm1 = new HBox(lblTerm1Credits,txtTerm1Credits);
		CurrentTerm1.setAlignment(Pos.BASELINE_RIGHT);
		CurrentTerm1.setSpacing(10);
		
		//rightBox
		
		HBox CurrentTerm2 = new HBox(lblTerm2Credits,txtTerm2Credits);
		CurrentTerm2.setAlignment(Pos.BASELINE_LEFT);
		CurrentTerm2.setSpacing(10);
		
		//CurrentTerm1
		
		GridPane LeftBox1 = new GridPane();
		LeftBox1.add(lblTerm1Unselected, 0, 0);
		LeftBox1.add(lstTerm1Unselected, 0, 1);
		LeftBox1.add(Term1, 0, 2);
		GridPane.setVgrow(LeftBox1, Priority.ALWAYS);
		
		
		//LeftBox.getChildren().add(Term1);
		VBox LeftBox = new VBox(lblTerm1Unselected,lstTerm1Unselected, Term1 , lblTerm2Unselected,lstTerm2Unselected, Term2,CurrentTerm1);
		//LeftBox.getChildren().add(Term1);
		VBox RightBox = new VBox(lblYearLongModules,lstSelectedAllYear,lblTerm1Selected,lstTerm1Selected,lblTerm2Selected,lstTerm2Selected,CurrentTerm2);
		LeftBox.setMinWidth(200);
		RightBox.setMinWidth(200);
		LeftBox.setPrefWidth(300);
		RightBox.setPrefWidth(300);
		LeftBox.setMaxWidth(Double.MAX_VALUE);
		RightBox.setMaxWidth(Double.MAX_VALUE);
		LeftBox.setMaxHeight(Double.MAX_VALUE);
		RightBox.setMaxHeight(Double.MAX_VALUE);
		
		
		LeftBox.setSpacing(10);
		RightBox.setSpacing(10);
		GridPane.setVgrow(RightBox, Priority.ALWAYS);
		GridPane.setVgrow(LeftBox, Priority.ALWAYS);
		//LeftBox.setVgrow(lstTerm1Unselected, Priority.ALWAYS);
		//lstTerm1Unselected.setPrefSize(350, 100);
		VBox.setVgrow(LeftBox, Priority.ALWAYS);
		VBox.setVgrow(RightBox, Priority.ALWAYS);
		VBox.setVgrow(lblTerm1Unselected, Priority.ALWAYS);
		HBox.setHgrow(LeftBox, Priority.ALWAYS);
		HBox.setHgrow(lstTerm1Unselected, Priority.ALWAYS);
		
		
		//LeftBox.setFillWidth(true);
		
		GridPane Container = new GridPane();
		
		Container.add(LeftBox, 0, 0);
		Container.add(RightBox, 1, 0);
		Container.setHgap(15);
		Container.getColumnConstraints().addAll(column0,column1);
		
		Container.setPrefHeight(200);
		
		//Container.setMinWidth(Double.MAX_VALUE);
		Container.setAlignment(Pos.CENTER);
		//Container.setPrefWidth(2000);
		Container.setMaxWidth(Double.MAX_VALUE);
		Container.setMinWidth(200);
		
		this.setCenter(Container);
		
		HBox HBoxButtons = new HBox(Reset,Submit);
		HBoxButtons.setSpacing(10);
		HBoxButtons.setAlignment(Pos.CENTER);
		this.setBottom(HBoxButtons);
	
	}
	public void addModulesToList(model.Module[] modules) {
		lstTerm1Unselected.getItems().addAll(modules);
		lstTerm1Unselected.getSelectionModel().select(0); //select first course by default
	}
	public void addModulesToList1(model.Module[] modules) {
		
		lstTerm2Unselected.getItems().addAll(modules);
		lstTerm2Unselected.getSelectionModel().select(0); //select first course by default
	}
	public void addTerm1(EventHandler<ActionEvent> handler) {
		Term1Add.setOnAction(handler);
	}
	public void removeTerm1(EventHandler<ActionEvent> handler) {
		Term1Remove.setOnAction(handler);
	}
	public void addTerm2(EventHandler<ActionEvent> handler) {
		Term2Add.setOnAction(handler);
	}
	public void removeTerm2(EventHandler<ActionEvent> handler) {
		Term2Remove.setOnAction(handler);
	}
	public void reset(EventHandler<ActionEvent> handler) {
		Reset.setOnAction(handler);
	}
	public void submit(EventHandler<ActionEvent> handler) {
		Submit.setOnAction(handler);
	}
	//term1
	public void addSelected()
	{
		
		
		if (getUnselectedTerm1() == null)
		{
			
		}
		else
		{
			lstTerm1Selected.getItems().addAll(getUnselectedTerm1());
		}
		
	}
	public void removeSelected()
	{
	
		lstTerm1Unselected.getItems().remove(getUnselectedTerm1());
		
	}
	public  Module getUnselectedTerm1()
	{
		return lstTerm1Unselected.getSelectionModel().getSelectedItem();
	}
	public Module getSelectedTerm1()
	{
		return lstTerm1Selected.getSelectionModel().getSelectedItem();
	}
	public void removeSelected1()
	{
		lstTerm1Selected.getItems().remove(getSelectedTerm1());
	}
	public void addSelected1()
	{
		if (getSelectedTerm1() == null)
		{
			
		}
		else
		{
			lstTerm1Unselected.getItems().addAll(getSelectedTerm1());
		}
		
	}
	//term2 
	public void addSelected2()
	{
		if (getUnselectedTerm2() == null)
		{
			
		}
		else
		{
			lstTerm2Selected.getItems().addAll(getUnselectedTerm2());
		}
		
	}
	public void removeSelected2()
	{
		lstTerm2Unselected.getItems().remove(getUnselectedTerm2());
	}
	public  Module getUnselectedTerm2()
	{
		return lstTerm2Unselected.getSelectionModel().getSelectedItem();
	}
	public ObservableList<Module> getAllUnselectedTerm1()
	{
		return lstTerm1Unselected.getItems();
	}
	public ObservableList<Module> getAllUnselectedTerm2()
	{
		return lstTerm2Unselected.getItems();
	}
	public Module getSelectedTerm2()
	{
		return lstTerm2Selected.getSelectionModel().getSelectedItem();
	}
	public void removeSelected3()
	{
		lstTerm2Selected.getItems().remove(getSelectedTerm2());
	}
	public void addSelected3()
	{
		if (getSelectedTerm2() == null)
		{
			
		}
		else
		{
			lstTerm2Unselected.getItems().addAll(getSelectedTerm2());
		}
		
	}
	public void Fill1(StudentProfile student)
	{
		//lstTerm1Unselected.getItems().addAll(student.getStudentCourse().getAllModulesOnCourse());
		Collection<Module> module1= student.getStudentCourse().getAllModulesOnCourse();
		
		FilterModules(module1);
	}
	public Collection<Module> FilterModules(Collection<Module> modules)
	{
		if(lstTerm1Unselected.getItems().isEmpty())
		{
			for (Module module : modules) {
				Schedule new1 = module.getDelivery();
				
				Module itemToAdd = module;
				if(new1==Schedule.TERM_1)
				{
					if(module.isMandatory()==false)
					{
						lstTerm1Unselected.getItems().add(itemToAdd);
					}
					if(module.isMandatory()== true)
					{
						lstTerm1Selected.getItems().add(itemToAdd);
						calculateMarks1();
					}
				}
				if(new1==Schedule.TERM_2)
				{
					if(module.isMandatory()==false)
					{
						lstTerm2Unselected.getItems().add(itemToAdd);
					}
					if(module.isMandatory()== true)
					{
						lstTerm2Selected.getItems().add(itemToAdd);
						calculateMarks2();
					}
				}
				if(new1==Schedule.YEAR_LONG)
				{
					lstSelectedAllYear.getItems().add(itemToAdd);
				}
		}
		}
		else {
			
		
		
		}
		
		
		return modules;
	}
	public void removeAll()
	{
		int tempTerm2Selected = lstTerm2Selected.getItems().size();
		for(int i=0;i<tempTerm2Selected;i++)
		{
		lstTerm2Selected.getItems().remove(0);
		}
		int tempTerm1Selected = lstTerm1Selected.getItems().size();
		for(int i=0;i<tempTerm1Selected;i++)
		{
		lstTerm1Selected.getItems().remove(0);
		}
		int tempTerm1Unselected = lstTerm1Unselected.getItems().size();
		for(int i=0;i<tempTerm1Unselected;i++)
		{
		lstTerm1Unselected.getItems().remove(0);
		}
		int tempTerm2Unselected = lstTerm2Unselected.getItems().size();
		for(int i=0;i<tempTerm2Unselected;i++)
		{
		lstTerm2Unselected.getItems().remove(0);
		}
		int tempAllYearSelected = lstSelectedAllYear.getItems().size();
		for(int i=0;i<tempAllYearSelected;i++)
		{
		lstSelectedAllYear.getItems().remove(0);
		}
	}
	public Collection<Module> submitAll()
	{
		Collection<Module> List1 = new TreeSet<Module>();
		int tempTerm3Unselected = lstTerm1Unselected.getItems().size();
		for(int i=0;i<tempTerm3Unselected;i++)
		{
		List1.add(lstTerm1Unselected.getItems().get(i));
		
		}
		return List1;
	}

	public void calculateMarks1()
	{
		
		int credits = 0;
		credits = ((lstTerm1Selected.getItems().size())*15+15*lstSelectedAllYear.getItems().size());
		String strcredits = Integer.toString(credits);
		txtTerm1Credits.setText(strcredits);
		
	}
	public void calculateMarks2()
	{
		
		int credits = 0;
		credits = ((lstTerm2Selected.getItems().size())*15)+(15*lstSelectedAllYear.getItems().size());
		String strcredits = Integer.toString(credits);
		txtTerm2Credits.setText(strcredits);
		
	}
	public Collection<Module> getSelectedModules1()
	{
		Collection<Module> List1 = new ArrayList<Module>();
		int tempTerm1Selected = lstTerm1Selected.getItems().size();
		for(int i=0;i<tempTerm1Selected;i++)
		{
			List1.add(lstTerm1Selected.getItems().get(i));
		
		}
		return List1;
	}
	public Collection<Module> getSelectedModules2()
	{
		Collection<Module> List1 = new ArrayList<Module>();
		int tempTerm2Selected = lstTerm2Selected.getItems().size();
		for(int i=0;i<tempTerm2Selected;i++)
		{
			List1.add(lstTerm2Selected.getItems().get(i));
		
		}
		return List1;
	}
	public int getCredits1()
	{
		String temp = txtTerm1Credits.getText();
		Integer temp1 = Integer.valueOf(temp);
		return temp1;
	}
	public int getCredits2()
	{
		String temp = txtTerm2Credits.getText();
		Integer temp1 = Integer.valueOf(temp);
		return temp1;
	}
}
