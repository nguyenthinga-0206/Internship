package com.example.springbatch.config;

import javax.sql.DataSource;

import com.example.springbatch.Tasklet.DemoTasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// tag::setup[]
@Configuration
@EnableBatchProcessing
public class BatchTaskletConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    // end::setup[]

    // @Autowired
    // public DataSource dataSource;

    // // reader database
    // @Bean
    // public JdbcCursorItemReader<Facebook> readerSQL() {
    //     JdbcCursorItemReader<Facebook> reader = new JdbcCursorItemReader<Facebook>();
    //     reader.setDataSource(dataSource);
    //     reader.setSql("select * from facebook where namelpid='FBAPCwat190514ZMIa1'");
    //     reader.setRowMapper(new FacebookRowMapper());
    //     return reader;
    // }

    // @Bean
    // public FacebookItemProcessor processor1() {
    //     return new FacebookItemProcessor();
    // }

    // // writer csv
    // @Bean
    // public FlatFileItemWriter<Facebook> writerCSV() {
    //     FlatFileItemWriter<Facebook> writer = new FlatFileItemWriter<Facebook>();
    //     writer.setResource(new FileSystemResource("spring-batch-demo/src/main/resources/output/facebook.csv"));
    //     writer.setLineAggregator(new DelimitedLineAggregator<Facebook>() {
    //         {
    //             setDelimiter(",");
    //             setFieldExtractor(new BeanWrapperFieldExtractor<Facebook>() {
    //                 {
    //                     setNames(new String[] { "date", "media", "namelpid", "cost", "impression", "click", "cv" });
    //                 }
    //             });
    //         }
    //     });
    //     return writer;
    // }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new DemoTasklet(dataSource))
                .build();
    }

    // @Bean
    // public Job exportFacebookJob(Step step2) {
    //     return jobBuilderFactory.get("exportFacebookJob")
    //             .flow(step2)
    //             .build();
    // }

    @Bean
    public Job exportFacebookNgaJob( Step step2) {
        return jobBuilderFactory.get("exportFacebookNgaJob")
                .incrementer(new RunIdIncrementer())
                .preventRestart()
                .flow(step2)
                .end()
                .build();
            }
}