package edu.ncsu.csc216.app_manager.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.app_manager.model.application.Application;
import edu.ncsu.csc216.app_manager.model.application.Application.AppType;
import edu.ncsu.csc216.app_manager.model.command.Command; 

/**
 * Concrete class that maintains a current list of Applications in the Application Manager system. 
 * These applications are ordered by id and duplicate ids are not allowed.
 * @author Diya Patel 
 */
public class AppList {
	
	/** counter for application list */
	private int counter;
	/** list of applications */ 
	ArrayList<Application> appList; 

	/**
	 * AppList Constructor 
	 */
	public AppList() {
		appList = new ArrayList<Application>(); 
	}
	
	/**
	 * adds application to list  
	 * @param type the type of the application 
	 * @param summary the summary of the application 
	 * @param note the note of the application 
	 * @return counter when application is added  
	 */
	public int addApp(AppType type, String summary, String note) {
		counter++; 
		Application a = new Application(counter, type, summary, note); 
		addApp(a); 
		return counter; 
	} 
	
	/**
	 * adds the applications to the list 
	 * @param applications the applications to add 
	 */
	public void addApps(List<Application> applications) {
		appList.clear(); 
		for(int i = 0; i < applications.size(); i++) {
			addApp(applications.get(i)); 
		}
		counter = appList.get(appList.size() - 1).getAppId();
	}
	
	/**
	 * adds the following given application 
	 * @param application the application to add 
	 */
	private void addApp(Application application) {
		if(this.getAppById(application.getAppId()) == null) {
			if(appList.size() == 0 || appList.get(appList.size() - 1).getAppId() < application.getAppId()) {
				appList.add(application); 
			}
			else 
			{
				for(int i = 0; i < appList.size(); i++) {
					if(appList.get(i).getAppId() > application.getAppId()) {
						appList.add(i, application);	
						break; 
					}
				}
			} 
			
		}
	}
	
	/**
	 * gets the list of applications 
	 * @return appList the list of applications 
	 */
	public List<Application> getApps(){
		return appList; 
	}
	
	/**
	 * gets the list of applications based on the type 
	 * @param type the application type
	 * @return list the list of applications of that type  
	 * @throws IllegalArgumentException if type is null 
	 */
	public List<Application> getAppsByType(String type) {
		if(type == null) {
			throw new IllegalArgumentException("Invalid information."); 
		}
		ArrayList<Application> typeApp = new ArrayList<Application>(); 
		
		for(int i = 0; i < appList.size(); i++) {
			if(appList.get(i).getAppType().equals(type)) {
				typeApp.add(appList.get(i));
			}
		}
		return typeApp; 
	}
	   
	/**
	 * gets the application based on the id. 
	 * @param appId the id of the application 
	 * @return null if there is no matching application 
	 */
	public Application getAppById(int appId) {
		for(int i = 0; i < appList.size(); i++) {
			if(appList.get(i).getAppId() == appId) {
				return appList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * executes the following command 
	 * @param id the id of the application 
	 * @param command the command to execute 
	 */
	public void executeCommand(int id, Command command) {

		Application application = getAppById(id); 
		if(application != null) {
			application.update(command); 
		}
	}
	
	/**
	 * deleted an application based on the given id. 
	 * @param id the id of the application  
	 */
	public void deleteAppById(int id) {
		Application application = getAppById(id); 
		if(application != null) {
			appList.remove(application); 
		}
		
		
	}
}