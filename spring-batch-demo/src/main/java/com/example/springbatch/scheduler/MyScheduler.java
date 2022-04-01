package com.example.springbatch.scheduler;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.springbatch.listener.JobCompletionNotificationListener;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	@Component
	@EnableScheduling
	public class MyScheduler {
	
		@Autowired
		private JobLauncher jobLauncher;
		

		@Autowired
		@Qualifier("importFacebookJob")
		private Job job1;

		@Autowired
		@Qualifier("importFacebookProcessJob")
		private Job job2;

		@Autowired
		@Qualifier("exportFacebookNgaJob")
		private Job job3;

		private JobExecution jobExecution;

		private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
		private JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		@Scheduled(fixedRate  = 30000)
		public void myScheduler(){
			
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
   			    LocalDateTime now = LocalDateTime.now();
				File file=new File("D:/batch/internship_2022_bachkhoa_java_1/spring-batch-demo/src/main/resources/FacebookCSV_3000.csv");
				if(file.exists()){

					jobExecution = jobLauncher.run(job1, jobParameters);
                    System.out.println("Job's Status:::"+jobExecution.getStatus());
                    File fileRename=new File("D:/batch/internship_2022_bachkhoa_java_1/spring-batch-demo/src/main/resources/oldfile/FacebookCSV_3000-"+dtf.format(now).toString()+".csv" );
					if(file.renameTo(fileRename)){
                        log.info("file rename successful");
                    }
                    else{
                        log.info("file rename fail");
                    }
					
				}
				else{
					log.info("file not exits");
				}
				
				jobExecution = jobLauncher.run(job2, jobParameters);
				System.out.println("Job's Status:::"+jobExecution.getStatus());

				jobExecution = jobLauncher.run(job3, jobParameters);
				System.out.println("Job's Status:::"+jobExecution.getStatus());
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
					log.info("file not exits");
			}
			
			
		}
	}