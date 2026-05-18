package edu.ncsu.csc216.app_manager.model.manager;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command;
import edu.ncsu.csc216.app_manager.model.io.AppReader;
import edu.ncsu.csc216.app_manager.model.io.AppWriter;  

/**
 * Concrete class that maintains the AppList and handles Commands from the GUI. 
 * AppManager implements the Singleton Design Pattern.
 * @author Diya Patel 
 */
public class AppManager {
	
	/** App Manager Instance */
	static AppManager appManager = null;
	/** appList instance */ 
	AppList appList; 
	
	
	/**
	 * AppManager Constructor 
	 */
	private AppManager() {
		appList = new AppList(); 
	}
	
	/**
	 * gets the instance of the application 
	 * @return instance of the application 
	 */
	public static AppManager getInstance() {
		if(appManager == null) {
			appManager = new AppManager();
		}
		return appManager; 
	}
	
	/**
	 * saves the applications to a file 
	 * @param fileName the name of the file 
	 */
	public void saveAppsToFile(String fileName) {
		AppWriter.writeAppsToFile(fileName, appList.getApps());
	}
	
	/**
	 * loads the applications from the given file 
	 * @param fileName the name of the file 
	 * @throws FileNotFoundException if file is not found        
	 */
	public void loadAppsFromFile(String fileName) {
		ArrayList<Application> applications; 
	    applications = AppReader.readAppsFromFile(fileName); 
		appList.addApps(applications); 
	}
	
	/**
	 * creates a new application list 
	 */
	public void createNewAppList() {
		appList = new AppList(); 
	}
	
	/**
	 * gets the application list as an array 
	 * @return appListArray an array made of applications 
	 */
	public Object[][] getAppListAsArray(){
		Object[][] appListArray = new Object[appList.getApps().size()][4];
		for(int i = 0; i < appList.getApps().size(); i++) {
			appListArray[i][0] = appList.getApps().get(i).getAppId(); 
			appListArray[i][1] = appList.getApps().get(i).getStateName();
			appListArray[i][2] = appList.getApps().get(i).getAppType();
			appListArray[i][3] = appList.getApps().get(i).getSummary();
		}
		return appListArray; 
	}
	
	/**
	 * gets the application list as an array based on the type 
	 * @param type the type of applications 
	 * @return appListArray an array made of applications of the specific type 
	 */
	public Object[][] getAppListAsArrayByAppType(String type){
		Object[][] appListArray = new Object[appList.getAppsByType(type).size()][4];
		for(int i = 0; i < appList.getAppsByType(type).size(); i++) {
			appListArray[i][0] = appList.getAppsByType(type).get(i).getAppId(); 
			appListArray[i][1] = appList.getAppsByType(type).get(i).getStateName();
			appListArray[i][2] = appList.getAppsByType(type).get(i).getAppType();
			appListArray[i][3] = appList.getAppsByType(type).get(i).getSummary();
		}
		return appListArray;  
	}
	
	/**
	 * returns the application with the matching id 
	 * @param id the id of the application 
	 * @return application the application matching the id 
	 */
	public Application getAppById(int id) {
		return appList.getAppById(id); 
	}
	
	/**
	 * executes the following command 
	 * @param id the id of the application 
	 * @param command the command to execute 
	 * @throws IllegalArgumentException if id is not found 
	 */
	public void executeCommand(int id, Command command) {
		appList.executeCommand(id, command);  
	}
	
	/**
	 * deleted an application based on the given id. 
	 * @param id the id of the application 
	 * @throws IllegalArgumentException if id is not found 
	 */
	public void deleteAppById(int id) {
		appList.deleteAppById(id); 
	}
	
	/**
	 * adds the application to the List 
	 * @param appType the type of application 
	 * @param summary the summary of the application 
	 * @param note the note of the application 
	 */
	public void addAppToList(AppType appType, String summary, String note) {
		appList.addApp(appType, summary, note);  
	}

}