
package com.example.springbatch.config;
import com.example.springbatch.Repository.FacebookRepository;
import com.example.springbatch.listener.JobCompletionNotificationListener;
import com.example.springbatch.model.Facebook;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public FacebookRepository FacebookRepository;
    
    // job
    @Bean
    public Job importFacebookJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importFacebookJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .preventRestart()
                .flow(step1)
                .end()
                .build();
            }
    // step
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Facebook, Facebook>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    // reader
    @Bean
    public FlatFileItemReader<Facebook> reader() {
        return new FlatFileItemReaderBuilder<Facebook>()
                .name("personItemReader")
                .resource(new ClassPathResource("FacebookCSV_3000.csv"))
                .delimited()
                .names(new String[] { "date_fb", "media", "namelpid", "cost", "impression", "click", "cv" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Facebook>() {
                    {
                        setTargetType(Facebook.class);
                    }
                })
                .build();
    }

    // processer


    // writer
    @Bean
    public ItemWriter<Facebook> writer() {
        RepositoryItemWriter<Facebook> writer = new RepositoryItemWriter<>(); 
		writer.setRepository(FacebookRepository);
		writer.setMethodName("save");
		return writer;
    }

}