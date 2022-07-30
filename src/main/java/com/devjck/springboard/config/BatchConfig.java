package com.devjck.springboard.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {
//    log
//    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextJob() {
        return jobBuilderFactory.get("stepNextJob")
                .start(firstStep())
                .next(secondStep())
                .next(thirdStep())
                .build();
    }

    @Bean
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("First Step!");
                    return RepeatStatus.FINISHED;
                }).build();

    }

    @Bean
    public Step secondStep() {
        return stepBuilderFactory.get("secondStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Second Step!");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step thirdStep() {
        return stepBuilderFactory.get("secondStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("third Step!");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
