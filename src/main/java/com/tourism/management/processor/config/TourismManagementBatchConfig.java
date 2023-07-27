package com.tourism.management.processor.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.tourism.management.processor.model.BranchDetail;
import com.tourism.management.processor.model.BranchDetailCSVModel;

@Configuration
@EnableBatchProcessing
public class TourismManagementBatchConfig {

	@Bean
	public Job job(JobRepository jobRepository,PlatformTransactionManager transactionManager,
			ItemReader<BranchDetailCSVModel> itemReader,
			ItemProcessor<BranchDetailCSVModel,BranchDetail> itemProcessor,
			ItemWriter<BranchDetail> itemWriter) {
		
		Step step = new StepBuilder("place-csv-load")
				.repository(jobRepository)
				.transactionManager(transactionManager)
				.<BranchDetailCSVModel,BranchDetail>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter).build();
		
		return new JobBuilder("place-profile-init")
				.repository(jobRepository)
				.preventRestart()
				.start(step).build();
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("/org/springframework/batch/core/schema-hsqldb.sql")
				.generateUniqueName(true).build();
	}

	@Bean
	public JdbcTransactionManager transactionManager(DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}

	
	@Bean
	public FlatFileItemReader<BranchDetailCSVModel> itemReader(@Value("${csv.input.file}") Resource resource){
		
		FlatFileItemReader<BranchDetailCSVModel> flatFileItemReader = new FlatFileItemReader<BranchDetailCSVModel>();
		flatFileItemReader.setStrict(false);
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("tourism-place-Csv-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	private LineMapper<BranchDetailCSVModel> lineMapper() {
		
		DefaultLineMapper<BranchDetailCSVModel> defaultLineMapper = new DefaultLineMapper<BranchDetailCSVModel>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("branchId","branchName","website","contactNo","email","places");
		
		BeanWrapperFieldSetMapper<BranchDetailCSVModel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(BranchDetailCSVModel.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		
		return defaultLineMapper;
	}
	
}
