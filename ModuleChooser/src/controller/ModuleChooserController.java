package controller;

import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.OverviewPane;
import view.ReserveModulesPane;
import view.SelectModulesPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;

public class ModuleChooserController {

	//fields to be used throughout class
	private ModuleChooserRootPane view;
	private StudentProfile model;
	
	
	private CreateStudentProfilePane cspp;
	private SelectModulesPane smp;
	private ReserveModulesPane rmp;
	private ModuleChooserMenuBar mstmb;
	private OverviewPane op;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		smp = view.getSelectModulesPane();
		mstmb = view.getModuleSelectionToolMenuBar();
		rmp= view.getReserveModulesPane();
		op = view.getOverviewPane();

		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		cspp.addCoursesToComboBox(generateAndGetCourses());
		

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		smp.addTerm1(new addTerm1Handler());
		smp.removeTerm1(new removeTerm1Handler());
		smp.addTerm2(new addTerm2Handler());
		smp.removeTerm2(new removeTerm2Handler());
		smp.reset(new resetHandler());
		smp.submit(new submitHandler());
		rmp.addbtnTerm1(new add1Handler());
		rmp.removeTerm1(new remove1Handler());
		rmp.addbtnTerm2(new add2Handler());
		rmp.removeTerm2(new remove2Handler());
		rmp.submitTerm1(new submitTerm1Handler());
		rmp.submitTerm2(new submitTerm2Handler());
		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
	}
//	private class addTerm1Handler implements EventHandler<ActionEvent>{
	//		public void handle(ActionEvent e) {
	//		smp.addSelected();
				
	//	}
	//}
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(cspp.inputValidation()==true)
			{
			StudentProfile newStudent= new StudentProfile();
			model.setStudentCourse(cspp.getSelectedCourse());
			model.setStudentEmail(cspp.getStudentEmail());
			model.setStudentPnumber(cspp.getStudentPnumber());
			model.setStudentName(cspp.getStudentName());
			model.setSubmissionDate(cspp.getStudentDate());
			op.addStudentProfileToList1(model);
			
			smp.Fill1(model);
			cspp.inputValidationLblChange(cspp.inputValidation());
			
			}
			else
			{
				cspp.inputValidationLblChange(cspp.inputValidation());
			}
		}
	}
	private class addTerm1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.addSelected();
			smp.removeSelected();
			smp.calculateMarks1();
		}
	}
	private class removeTerm1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.addSelected1();
			smp.removeSelected1();
			smp.calculateMarks1();
		}
	}
	private class addTerm2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.addSelected2();
			smp.removeSelected2();
			smp.calculateMarks2();
		}
	}
	private class removeTerm2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.addSelected3();
			smp.removeSelected3();
			smp.calculateMarks2();
		}
	}
	private class resetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.removeAll();
			smp.Fill1(model);
			smp.calculateMarks1();
			smp.calculateMarks2();
			
		}
		}
	private class add1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			rmp.addSelected();
			rmp.removeSelected();
			
		}
		}
	private class remove1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			rmp.addSelected1();
			rmp.removeSelected1();
			
		}
		}
	private class add2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			rmp.addSelected2();
			rmp.removeSelected2();
			
		}
		}
	private class remove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			rmp.addSelected3();
			rmp.removeSelected3();
			
		}
		}
	private class submitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(smp.getCredits1()==60 && smp.getCredits2()==60)
			{
			
			setSelectedModules(smp.getSelectedModules1());
			setSelectedModules(smp.getSelectedModules2());
			op.addSelectedModulesToList1(model);
			//rmp.unselectedFill(smp.submitAll());
			ObservableList<Module> term1UnselectedModules = smp.getAllUnselectedTerm1();
			ObservableList<Module> term2UnselectedModules = smp.getAllUnselectedTerm2();
			rmp.unselectedFill(term1UnselectedModules);
			rmp.unselectedFill1(term2UnselectedModules);
			}
			else
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("not enough credits");
				a.show();
			}
			
	}
}
	private class submitTerm1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			setReservedModules(rmp.getReservedModules1());
			
			
		}
		}
	
	private class submitTerm2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			setReservedModules(rmp.getReservedModules2());
			op.addReservedModulesToList1(model);
			
		}
		}
	public void setSelectedModules(Collection<Module> modules) {
		for(Module module :modules)
		{
			model.addSelectedModule(module);
			
		}
	}
	public void setReservedModules(Collection<Module> modules) {
		for(Module module :modules)
		{
			model.addSelectedModule(module);
			
		}
	}
	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}



}

