package com.example.springbatch.Tasklet;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.sql.DataSource;

import com.example.springbatch.Repository.FacebookProccessRepository;
import com.example.springbatch.listener.JobCompletionNotificationListener;
import com.example.springbatch.mapper.FacebookDB_CSV;
import com.example.springbatch.model.Facebookprocess;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DemoTasklet implements Tasklet, StepExecutionListener {
  
    private DataSource dataSource;
    private String sql;
    private FacebookProccessRepository facebookProccessRepository;
    PrintWriter writer;
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    public DemoTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public DemoTasklet(FacebookProccessRepository facebookProccessRepository) {
        this.facebookProccessRepository = facebookProccessRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        JdbcTemplate myJDBC=new JdbcTemplate(dataSource);  
        sql="select DISTINCT  ad_name_lpid  from facebook_process_csv"; 
        List<String> listNameLpid = myJDBC.queryForList(sql, String.class);

        for( int i=0; i<listNameLpid.size();i++){
            sql = "select id, ad_name_lpid, cpc, cpm, ctr, sumclick, sumcost, sumimpression, date_fb from facebook_process_csv where ad_name_lpid='"+listNameLpid.get(i)+"'";
            List<Facebookprocess> facebookprocesses = myJDBC.query(sql, new FacebookDB_CSV());
            
            String pathFile = "D:/batch/internship_2022_bachkhoa_java_1/spring-batch-demo/src/main/resources/output/" + listNameLpid.get(i) + "_" + System.currentTimeMillis() + ".csv";
            File csvOutputFile = new File(pathFile);
            
            CsvMapper mapper = new CsvMapper();
            
            CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("id")
                .addColumn("ad_name_lpid")
                .addColumn("cpc")
                .addColumn("cpm")
                .addColumn("ctr")
                .addColumn("sumclick")
                .addColumn("sumcost")
                .addColumn("sumimpression")
                .addColumn("date_fb")
                .build();

            ObjectWriter writer = mapper.writerFor(Facebookprocess.class).with(schema);

            writer.writeValues(csvOutputFile).writeAll(facebookprocesses);
        }
        
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void beforeStep(StepExecution arg0) {
        // TODO Auto-generated method stub

    }

}
