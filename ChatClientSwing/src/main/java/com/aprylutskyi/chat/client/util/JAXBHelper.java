package com.aprylutskyi.chat.client.util;

import com.aprylutskyi.chat.client.dto.*;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JAXBHelper {

    private final static Logger LOGGER = Logger.getLogger(JAXBHelper.class);

    private static JAXBContext jaxbContext;

    private static Class<?>[] MAPPED_CLASSES = {MessageDto.class, MessagesListDto.class,
            UserDto.class, UsersListDto.class,
            ErrorDto.class};



    static {
        try {
            jaxbContext = JAXBContext.newInstance(MAPPED_CLASSES);
        } catch (JAXBException e) {
            LOGGER.error("Error occurred     while loading JAXBContext");
            System.exit(-1);
        }
    }

    private JAXBHelper() {
    }

    public static JAXBContext getJaxbContext() {
        return jaxbContext;
    }

}
