package com.capella.batch;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class PrintJobLauncherTest {
	
	@Autowired
	PrintJobLauncher printJobLauncher;
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	Job job;
	
	@Test
	public void testRun() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		printJobLauncher.execute();
		JobParameters jobParameters = new JobParameters();
		jobParameters.getParameters().put("date", new JobParameter(new Date()));
		JobExecution lastJobExecution = jobRepository.getLastJobExecution(job.getName(), jobParameters );
		Assert.assertNotNull(lastJobExecution);
		Assert.assertEquals("myJob", lastJobExecution.getJobInstance().getJobName());
	}

}
