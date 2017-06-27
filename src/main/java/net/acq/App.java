package net.acq;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import net.acq.jobs.HelloJob;
import net.acq.jobs.RunCaps;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(final String[] args) {
		final App app = new App();
		app.startApp();
	}

	public void startApp() {
		TrayIconManager tIconManager = null;
		tIconManager = TrayIconManager.getInstance();
		tIconManager.createTrayIcon();
		try {
			final JobDetail mailJob = JobBuilder.newJob(HelloJob.class).withIdentity("helloJob", "helloGroup").build();
			final String cronForMailReminder = ParseProps.getProperty("mail.reminder.expression");
			final String capsTriggerSchedule = ParseProps.getProperty("caps.keys.expression");
			final Trigger triggerMailReminder = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggergroup1")
					.withSchedule(CronScheduleBuilder.cronSchedule(cronForMailReminder)).build();
			final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			final JobDetail capsJob = JobBuilder.newJob(RunCaps.class).withIdentity("runcaps", "capsgroup").build();
			final Trigger capsTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggergroup2")
					.withSchedule(CronScheduleBuilder.cronSchedule(capsTriggerSchedule)).build();
			scheduler.start();
			scheduler.scheduleJob(mailJob, triggerMailReminder);
			scheduler.scheduleJob(capsJob, capsTrigger);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
