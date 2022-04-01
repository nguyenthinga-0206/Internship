package com.example.springbatch.config;

import com.example.springbatch.Repository.FacebookProccessRepository;
import com.example.springbatch.Tasklet.myTasklet;
import com.example.springbatch.listener.JobCompletionNotificationListener;
import com.example.springbatch.model.Facebookprocess;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FaceBookconfiguration {
    
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public FacebookProccessRepository facebookProcessRepository;

   

     // job
     @Bean
     public Job importFacebookProcessJob(JobCompletionNotificationListener listener, Step job2_step1) {
         return jobBuilderFactory.get("importFacebookProcessJob")
                 .incrementer(new RunIdIncrementer())
                 .listener(listener)
                 .preventRestart()
                 .flow(job2_step1)
                 .end()
                 .build();
             }
     // step
     @Bean
     public Step job2_step1() {
         return stepBuilderFactory.get("job2_step1")
                .tasklet(new myTasklet(facebookProcessRepository))
                .build();
     }
 
     // reader
     @Bean
     public FlatFileItemReader<Facebookprocess> reader_facebook() {
        return null;
     }
 
     // processer
 
 
     // writer
     @Bean
     public ItemWriter<Facebookprocess> job2_writer() {
        RepositoryItemWriter<Facebookprocess> writer = new RepositoryItemWriter<>(); 
		writer.setRepository(facebookProcessRepository);
		writer.setMethodName("save");
        return writer;
     }
}


