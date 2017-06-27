package net.acq.jobs;

import java.io.IOException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import net.acq.ParseProps;

public class RunCaps implements Job {

	@Override
	public void execute(final JobExecutionContext context) throws JobExecutionException {
		final Runtime runtime = Runtime.getRuntime();
		final String command = ParseProps.getProperty("caps.keys.command");
		try {
			System.out.println("Running caps Schedule");
			runtime.exec(command);
			System.out.println("Success...");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
