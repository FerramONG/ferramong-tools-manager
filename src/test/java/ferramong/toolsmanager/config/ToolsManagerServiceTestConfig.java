package ferramong.toolsmanager.config;

import ferramong.toolsmanager.repositories.ToolsRepository;
import ferramong.toolsmanager.services.ToolsManagerService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ToolsManagerServiceTestConfig {
    @MockBean private ToolsRepository toolsRepository;

    @Bean
    public ToolsManagerService toolsManagerService() {
        return new ToolsManagerService(toolsRepository);
    }
}