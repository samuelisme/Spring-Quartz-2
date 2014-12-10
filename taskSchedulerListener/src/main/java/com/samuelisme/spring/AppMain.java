package com.samuelisme.spring;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.samuelisme.spring.triggerListener.TaskTriggerListener;

public class AppMain {
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("quartz-context.xml");
		
		try {
			Scheduler scheduler = (Scheduler) context.getBean("schedulerFactoryBean");
			TriggerListener taskTriggerListener = (TaskTriggerListener) context.getBean("taskTriggerListener");
			SimpleTrigger t = (SimpleTrigger)context.getBean("firstTrigger");
			Matcher<TriggerKey> matcher = KeyMatcher.keyEquals(t.getKey());
			scheduler.getListenerManager().addTriggerListener(taskTriggerListener, matcher);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
