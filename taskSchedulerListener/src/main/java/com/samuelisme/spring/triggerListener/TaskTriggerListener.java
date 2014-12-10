package com.samuelisme.spring.triggerListener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TaskTriggerListener implements TriggerListener {


	private Class cls;
	private Class[] params;
	private Method method;
	private String name;
	private ArrayList<TaskObject> taskObjList;
	
	
	private void execute() {
		try {
			if (!taskObjList.isEmpty()) {
				for (TaskObject taskObj: taskObjList) {
					cls = Class.forName(taskObj.getClassName());
					method = cls.getDeclaredMethod(taskObj.getMethodName(), params);
					method.invoke(taskObj.getObject(), null);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TaskTriggerListener ()  {
//		name = "taskTriggerListener";
		params = new Class[0];
	}
	
	public String getName() {
		return name;
	}

//	public void triggerComplete(Trigger arg0, JobExecutionContext arg1, int arg2) {
//		
//		this.execute();
//	}

	public void triggerFired(Trigger arg0, JobExecutionContext arg1) {
		//
	}

	public void triggerMisfired(Trigger arg0) {
		//
		
	}

	public boolean vetoJobExecution(Trigger arg0, JobExecutionContext arg1) {
		//
		return false;
	}

	public ArrayList<TaskObject> getTaskObjList() {
		return taskObjList;
	}

	public void setTaskObjList(ArrayList<TaskObject> taskObjList) {
		this.taskObjList = taskObjList;
	}

	public void triggerComplete(Trigger arg0, JobExecutionContext arg1,
			CompletedExecutionInstruction arg2) {
	
		this.execute();
	}

	
}
