package com.example.springbatch.Tasklet;

import java.util.List;

import javax.sql.DataSource;

import com.example.springbatch.Repository.FacebookProccessRepository;
import com.example.springbatch.model.Facebookprocess;
import com.example.springbatch.util.FacebookProcessMapper;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class myTasklet implements Tasklet, StepExecutionListener  {
    
    

    private String sql;

    private FacebookProccessRepository facebookProccessRepository;
    
    public myTasklet(){


    }
    
    @Autowired
    public myTasklet(FacebookProccessRepository facebookProccessRepository) {
        this.facebookProccessRepository = facebookProccessRepository;
    }

    public DataSource getdataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbatch_demo");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        return dataSource;
    }
    public void beforeStep(StepExecution stepExecution) {}
    
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        JdbcTemplate myJDBC=new JdbcTemplate(getdataSource());  
        sql="select  DISTINCT  namelpid  from facebook_csv"; 
        List<String> listNameLpid = myJDBC.queryForList(sql, String.class);
        System.out.println("Number of records effected: "+ listNameLpid);


        for( int i=0; i<listNameLpid.size();i++){
            for(int year=2005;year<=2013;year++){
                for(int month=1 ;month<13;month++){
                    sql = "select id, namelpid, sum(cost) as sumcost, sum(click) as sumclick, sum(impression) as sumimpression, date_fb from facebook_csv where namelpid='"+listNameLpid.get(i)+"' and date_fb like '%"+year+""+((month<10)?"-0":"-")+month+"-%'";
                    List<Facebookprocess> ListFacebookProcess = myJDBC.query(sql, new FacebookProcessMapper());
                for (Facebookprocess facebookprocess : ListFacebookProcess) {
                    if (facebookprocess.getDate_fb()!=null){
                        if(facebookprocess.getSumimpression()==0){
                            facebookprocess.setCpm(0);
                            facebookprocess.setCtr(0);
                        }
                        else{
                            facebookprocess.setCpm((facebookprocess.getSumcost()*10000)/facebookprocess.getSumimpression());
                            facebookprocess.setCtr(facebookprocess.getSumclick()/facebookprocess.getSumimpression());
                        }
                        if (facebookprocess.getSumclick()==0){
                            facebookprocess.setCpc(0);
                        }
                        else{
                            facebookprocess.setCpc(facebookprocess.getSumcost()/facebookprocess.getSumclick());
                        }
                        
                        // sql="Insert "
                        System.out.println("name: "+facebookprocess.getAd_name_lpid()+ " date_fb: "+facebookprocess.getDate_fb());
                        System.out.println("sumcost: "+facebookprocess.getSumcost()+ "---- sumclick:" +facebookprocess.getSumclick()+"---sumimpreesion:"+ facebookprocess.getSumimpression());
                        System.out.println("CPM: "+facebookprocess.getCpm()+"---CTR: "+facebookprocess.getCtr()+"---CPC: "+facebookprocess.getCpc());
                        // sql = "insert into  facebook_process_csv(id,ad_name_lpid,date_fb,cpc,cpm,ctr)"+ 
                        //     "values(?,?,?,?,?,?)";
                        // String date = facebookprocess.getDate_fb().substring(0,7);
                        // myJDBC.update(sql,facebookprocess.getId(),facebookprocess.getAd_name_lpid(),date,
                        //                 facebookprocess.getCpc(),facebookprocess.getCpm(),facebookprocess.getCtr());
                        Facebookprocess facebookprocess2 = new Facebookprocess(facebookprocess.getAd_name_lpid(),facebookprocess.getCpm(),
                                                            facebookprocess.getCtr(), facebookprocess.getCpc(), facebookprocess.getDate_fb());
                        if (facebookprocess2!=null){
                            facebookProccessRepository.save(facebookprocess2);
                        }
                       
                    }

                }
            }
    
        }
        }
        return RepeatStatus.FINISHED;
         
    }
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
    
}
