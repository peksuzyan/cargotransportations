package com.tsystems.cargotransportations.presentation.grids;

/**
 * Container is for passing validation results as attribute to a response.
 */
public class Message {

    /**
     * Type of the message: success, error, failure, passed and etc.
     */
    private String type;

    /**
     * Text of the message.
     */
    private String entry;

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
