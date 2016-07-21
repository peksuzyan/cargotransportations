package com.tsystems.cargotransportations.presentation.messages;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Util class in order to get message in convenience representation.
 */
public final class MessageUtil {

    /**
     * Converts message type, code and request locale to a Message object.
     * @param type type
     * @param messageCode message code
     * @param locale locale
     * @param source takes message with internalization supporting
     * @return message object
     */
    public static Message getMessage(
            String type, String messageCode, MessageSource source, Locale locale) {
        return new Message(type,
                source.getMessage(messageCode, new Object[]{}, locale));
    }
}
