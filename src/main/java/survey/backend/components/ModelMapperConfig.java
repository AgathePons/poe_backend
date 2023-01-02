package survey.backend.components;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    // custom model mapping here
    return new ModelMapper();
  }
}
