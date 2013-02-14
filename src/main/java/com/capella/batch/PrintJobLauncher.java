package com.capella.batch;
import java.util.Date;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("printJobLauncer")
public class PrintJobLauncher {
	@Autowired
	@Qualifier("myJob")
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	public void execute() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
		JobParameters jobParameters = new JobParameters();
		Map<String, JobParameter> parameters = jobParameters.getParameters();
		parameters.put("date", new JobParameter(new Date()));
		jobLauncher.run(job, jobParameters );
	}
}
