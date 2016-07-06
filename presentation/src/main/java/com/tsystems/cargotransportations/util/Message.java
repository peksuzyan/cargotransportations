package com.tsystems.cargotransportations.util;

/**
 * Container is for passing validation results as attribute to a response.
 */
public class Message {
    /**
     * Text of the message.
     */
    private String entry;

    /**
     * Type of the message: success or error (may would be something else).
     */
    private String type;

    /**
     * Single constructor.
     * @param entry text
     * @param type type
     */
    public Message(String type, String entry) {
        this.entry = entry;
        this.type = type;
    }

    /**
     * Gets entry.
     *
     * @return entry entry
     */
    public String getEntry() {
        return entry;
    }

    /**
     * Gets type.
     *
     * @return type type
     */
    public String getType() {
        return type;
    }
}
