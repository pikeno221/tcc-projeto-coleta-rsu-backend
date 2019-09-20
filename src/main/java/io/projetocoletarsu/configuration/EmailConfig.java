package io.projetocoletarsu.configuration;

import io.projetocoletarsu.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public SmtpEmailService emailService() {
        return new SmtpEmailService();

    }
}
