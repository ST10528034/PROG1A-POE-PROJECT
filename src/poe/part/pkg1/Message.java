/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Represents one QuickChat message and keeps track of every message
 * sent or stored while the program is running.
 */
public final class Message {

    // Lists of SENT messages (all lists stay in sync, one index per message)
    static ArrayList<String> messageIdsList = new ArrayList<>();
    static ArrayList<String> recipientNumber = new ArrayList<>();
    static ArrayList<String> messages = new ArrayList<>();
    static ArrayList<String> msgHash = new ArrayList<>();
    static ArrayList<String> messageStatus = new ArrayList<>();

    // Stored messages (written to messages.json)
    static ArrayList<String> storedJsonEntries = new ArrayList<>();

    // Every ID generated so far, so that IDs are unique
    static HashSet<String> usedIds = new HashSet<>();

    static int numMessageSent = 0;

    private static final Random RANDOM = new Random();

    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String text;
    private final String hash;

    // Creates a message with an auto-generated ten-digit ID and hash
    public Message(String recipient, String text, int messageNumber) {
        this(generateMessageID(), recipient, text, messageNumber);
    }

    // Creates a message with a known ID (used for testing)
    public Message(String messageID, String recipient, String text, int messageNumber) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.text = text;
        this.hash = createMessageHash();
    }

    // Clears all static data (used between unit tests)
    public static void resetAll() {
        messageIdsList.clear();
        recipientNumber.clear();
        messages.clear();
        msgHash.clear();
        messageStatus.clear();
        storedJsonEntries.clear();
        usedIds.clear();
        numMessageSent = 0;
    }

    // Random, unused ten-digit ID (may start with zeros, e.g. 0012345678)
    public static String generateMessageID() {
        String id;
        do {
            long number = (long) (RANDOM.nextDouble() * 10_000_000_000L);
            id = String.format("%010d", number);
        } while (usedIds.contains(id));
        usedIds.add(id);
        return id;
    }

    // Message ID must be no more than ten characters
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // Checks the recipient stored in this message
    public String checkRecipientCell() {
        return checkRecipientCell(recipient);
    }

    // Local number without an international code: 0 followed by nine digits, e.g. 0817421062
    public static boolean checkLocalRecipient(String cellNumber) {
        return cellNumber != null
                && cellNumber.matches("^0[0-9]{9}$");
    }

    // International number: + and a 1-3 digit country code, then up to ten digits, e.g. +27718693002
    public static boolean checkInternationalRecipient(String cellNumber) {
        return cellNumber != null
                && cellNumber.matches("^\\+[0-9]{1,3}[0-9]{1,10}$");
    }

    // Accepts either a local number or an international number
    public static String checkRecipientCell(String cellNumber) {
        if (checkLocalRecipient(cellNumber) || checkInternationalRecipient(cellNumber)) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an "
                + "international code. Please correct the number and try again.";
    }

    // Message must not exceed 250 characters
    public static String checkMessageLength(String messageText) {
        if (messageText != null && messageText.length() <= 250) {
            return "Message sent";
        }
        return "Please enter a message of less than 250 characters.";
    }

    // Format: first two ID digits : message number : FIRSTWORD + LASTWORD (all caps)
    public String createMessageHash() {
        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = text.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^A-Za-z0-9]", "");
        String lastWord = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "");

        return (firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Same as sentMessage (name used in the brief)
    public String SentMessage(int choice) {
        return sentMessage(choice);
    }

    // 1 = send, 2 = disregard, 3 = store
    public String sentMessage(int choice) {
        switch (choice) {
            case 1 -> {
                messageIdsList.add(messageID);
                recipientNumber.add(recipient);
                messages.add(text);
                msgHash.add(hash);
                messageStatus.add("Sent");
                numMessageSent++;
                return "Message successfully sent.";
            }
            case 2 -> {
                return "Press 0 to delete the message.";
            }
            case 3 -> {
                storeMessage();
                return "Message successfully stored.";
            }
            default -> {
                return "Invalid option.";
            }
        }
    }

    // Returns every message sent while the program is running
    public String printMessages() {
        StringBuilder all = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            all.append("Message ").append(i + 1)
                    .append("\nMessage ID: ").append(messageIdsList.get(i))
                    .append("\nMessage Hash: ").append(msgHash.get(i))
                    .append("\nRecipient: ").append(recipientNumber.get(i))
                    .append("\nMessage: ").append(messages.get(i))
                    .append("\nStatus: ").append(messageStatus.get(i))
                    .append("\n\n");
        }
        return all.toString();
    }

    public static int returnTotalMessages() {
        return numMessageSent;
    }

    /*
     * Stores the message in messages.json (a JSON array of objects).
     * Attribution: JSON structure follows the format described at https://www.json.org ;
     * file writing uses FileWriter inside try-with-resources as documented at
     * https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileWriter.html
     */
    public void storeMessage() {
        String entry = "  {\n"
                + "    \"messageID\": \"" + escape(messageID) + "\",\n"
                + "    \"messageNumber\": " + messageNumber + ",\n"
                + "    \"recipient\": \"" + escape(recipient) + "\",\n"
                + "    \"message\": \"" + escape(text) + "\",\n"
                + "    \"messageHash\": \"" + escape(hash) + "\"\n"
                + "  }";
        storedJsonEntries.add(entry);

        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write("[\n" + String.join(",\n", storedJsonEntries) + "\n]");
        } catch (IOException e) {
            System.out.println("Could not write messages.json: " + e.getMessage());
        }
    }

    // Escapes characters that are not allowed raw inside a JSON string
    private static String escape(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return hash;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}