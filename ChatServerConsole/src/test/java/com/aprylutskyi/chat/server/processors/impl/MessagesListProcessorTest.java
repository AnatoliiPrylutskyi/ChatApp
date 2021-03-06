package com.aprylutskyi.chat.server.processors.impl;

import com.aprylutskyi.chat.server.constants.DataConstants;
import com.aprylutskyi.chat.server.dto.MessagesListDto;
import com.aprylutskyi.chat.server.util.JAXBHelper;
import org.junit.Test;
import org.xml.sax.InputSource;

import javax.xml.bind.Unmarshaller;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessagesListProcessorTest extends AbstractProcessorTest {

    private final String testListFilePath = getPathFromClassPath("/testMessagesList.xml");

    private final MessagesListProcessor listProcessor = new MessagesListProcessor();

    @Test
    public void sendDateTest() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        listProcessor.sendData(getTestDataFromFile(testListFilePath), dataOutputStream);
        String rawData = byteArrayOutputStream.toString();
        ByteArrayInputStream inputStreamRaw = new ByteArrayInputStream(rawData.getBytes());
        DataInputStream dataInputStream = new DataInputStream(inputStreamRaw);
        String testData = dataInputStream.readUTF();

        assertTrue(testData.startsWith(DataConstants.MESSAGE_HISTORY_TAG));

        testData = testData.replaceFirst(DataConstants.MESSAGE_HISTORY_TAG + System.lineSeparator(), "");
        Unmarshaller unmarshaller = JAXBHelper.getJaxbContext().createUnmarshaller();
        MessagesListDto messagesFromProcessor = (MessagesListDto) unmarshaller.unmarshal(new InputSource(new StringReader(testData)));
        MessagesListDto messagesFromFile = (MessagesListDto) unmarshaller.unmarshal(new File(testListFilePath));

        assertEquals(messagesFromProcessor, messagesFromFile);
    }

}
