package com.capella.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:printJob.xml"})
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
		JobExecution lastJobExecution = jobRepository.getLastJobExecution(job.getName(), null);
		System.out.println("last : " +lastJobExecution.getJobId());
	}

}
