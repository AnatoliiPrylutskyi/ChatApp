package com.aprylutskyi.chat.server.configuration;

import com.aprylutskyi.chat.server.dto.ConfigurationDto;
import org.junit.Test;

import static com.aprylutskyi.chat.server.constants.ConfigConstants.*;
import static org.junit.Assert.assertEquals;

public class ConfigurerTest {

    public final int portFromFile = 5000;
    public final int maxUsersFromFile = 10;
    public final int historySizeFromFile = 10;
    public final String logFileNameFromFile = "logs/serverLogsTest.log";
    public final String fileNameWithErrors = getClass().getResource("/settingsWithErrors.properties").getFile();
    public final String fileNameWithGoodProps = getClass().getResource("/settingsForTest.properties").getFile();
    private final Configurer configurer = new Configurer();

    @Test
    public void goodPropertiesTest() {

        ConfigurationDto config = configurer.getConfig(fileNameWithGoodProps);
        assertEquals(portFromFile, config.getPort());
        assertEquals(maxUsersFromFile, config.getMaxUsers());
        assertEquals(historySizeFromFile, config.getMessageHistorySize());
        assertEquals(logFileNameFromFile, config.getLogFilePath());
    }

    @Test
    public void badPropertiesTest() {
        ConfigurationDto config = configurer.getConfig(fileNameWithErrors);

        assertEquals(DEFAULT_PORT, config.getPort());
        assertEquals(DEFAULT_MAX_USERS, config.getMaxUsers());
        assertEquals(DEFAULT_MESSAGE_HISTORY_SIZE, config.getMessageHistorySize());
        assertEquals(DEFAULT_LOG_FILE_PATH, config.getLogFilePath());
    }

    @Test
    public void noPropertiesFileTest() {
        ConfigurationDto config = configurer.getConfig("noSuchFile.properties");

        assertEquals(DEFAULT_PORT, config.getPort());
        assertEquals(DEFAULT_MAX_USERS, config.getMaxUsers());
        assertEquals(DEFAULT_MESSAGE_HISTORY_SIZE, config.getMessageHistorySize());
        assertEquals(DEFAULT_LOG_FILE_PATH, config.getLogFilePath());
    }
}
