package view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;
import model.StudentProfile;

public class OverviewPane extends BorderPane{
	private ListView profile, SelectedModules, ReservedModules;
	private Button SaveOverview;
	public OverviewPane()
	
	{
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(100);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(45);
		
		column0.setHalignment(HPos.LEFT);
		column1.setHalignment(HPos.LEFT);
		
	SaveOverview = new Button("Save Overview");
	profile = new ListView();
	
	
	profile.setMaxWidth(Double.MAX_VALUE);
	SelectedModules = new ListView();
	SelectedModules.setPrefWidth(400);
	ReservedModules = new ListView();
	ReservedModules.setPrefWidth(400);
	
	HBox hbox0 = new HBox();
	VBox vbox0 = new VBox();
	hbox0.getChildren().add(SaveOverview);
	
	GridPane gridpane1 = new GridPane();
	GridPane container = new GridPane();
	GridPane gridpane2 = new GridPane();
	gridpane2.add(SelectedModules, 0, 0);
	gridpane2.add(ReservedModules, 1, 0);
	gridpane1.add(profile, 0, 0);
	container.add(gridpane1, 0, 0);
	container.add(gridpane2, 0, 1);
	GridPane.setHgrow(profile, Priority.ALWAYS);
	GridPane.setHgrow(gridpane1, Priority.ALWAYS);
	GridPane.setHgrow(gridpane2, Priority.ALWAYS);
	GridPane.setHgrow(SelectedModules, Priority.ALWAYS);
	GridPane.setHgrow(ReservedModules, Priority.ALWAYS);
	
	gridpane1.setMaxWidth(Double.MAX_VALUE);
	container.getColumnConstraints().addAll(column0);
	container.setVgap(15);
	container.setHgap(15);
	gridpane2.setHgap(15);
	//this.getChildren().addAll(Hbox0,Hbox1,Hbox2);
	this.setPadding(new Insets(50, 50, 50, 50));
	this.setCenter(container);
	hbox0.setAlignment(Pos.BASELINE_CENTER);
	
	this.setBottom(hbox0);
	
	
	//this.setBottom(Hbox2);
	}
	
	public void addStudentProfileToList1(model.StudentProfile student) {
		profile.getItems().add("Name: "+student.getStudentName().getFullName());
		profile.getItems().add("PNo: "+student.getStudentPnumber());
		profile.getItems().add("Email: "+student.getStudentEmail());
		profile.getItems().add("Date: "+student.getSubmissionDate());
		profile.getItems().add("Course: " + student.getStudentCourse());
		
		profile.getSelectionModel().select(0); //select first course by default
	}
	public void addSelectedModulesToList1(model.StudentProfile student) {
		for(Module module: student.getAllSelectedModules())
		{
		SelectedModules.getItems().add(module.getModuleCode()+": " +module.getModuleName());
		}
	}
	public void addReservedModulesToList1(model.StudentProfile student) {
		for(Module module: student.getAllReservedModules())
		{
		ReservedModules.getItems().add(module.getModuleCode()+": " +module.getModuleName());
		}
	}
	
}
