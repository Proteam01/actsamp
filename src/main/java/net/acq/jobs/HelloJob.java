package net.acq.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.acq.ParseProps;
import net.acq.TrayIconManager;

public class HelloJob implements Job {

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		final String message = ParseProps.getProperty("mail.reminder.message");
		final String title = ParseProps.getProperty("mail.reminder.title");
		TrayIconManager.getInstance().showPopupBalloon(message, title);
	}

}
