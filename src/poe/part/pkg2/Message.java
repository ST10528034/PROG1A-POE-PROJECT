/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Message {
    
     ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> messageIdsList = new ArrayList<>();
    HashSet<String> messageIds = new HashSet<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>();

    Random random = new Random();
    Scanner input = new Scanner(System.in);

    int numMessageSent = 0;

    public void sendingMessages() {

        System.out.print("How many messages do you want to send? ");
        int numberOfMessages = input.nextInt();

        input.nextLine();

        for (int i = 1; i <= numberOfMessages; i++) {

            System.out.println("\n==============================");
            System.out.println("MESSAGE " + i);
            System.out.println("==============================");

            String messageID = generateMessageID();

            System.out.print("Enter recipient cell number: ");
            String recipient = input.nextLine();

            String recipientResult = checkRecipientCell(recipient);

            if (!recipientResult.equals("Valid number")) {
                System.out.println(recipientResult);
                i--;
                continue;
            }

            System.out.print("Enter your message: ");
            String messageText = input.nextLine();

            if (messageText.length() > 250 || messageText.trim().isEmpty()) {
                System.out.println("Please enter a message of 1 to 250 characters.");
                i--;
                continue;
            }

            int messageNumber = messages.size() + 1;
            
            String messageHash = createMessageHash(messageID, messageNumber, messageText);

            msgHash.add(messageHash);
            messages.add(messageText);
            messageIdsList.add(messageID);
            messageIds.add(messageID);
            recipientNumber.add(recipient);

            String status = SentMessage();

            if (status.equals("Sent")) {
                messageStatus.add(status);
                numMessageSent++;
            }
        }

        printMessages();

        System.out.println("Total messages sent: " + returnTotalMessages());

    }

    public int returnTotalMessages() {

        return numMessageSent;
    }

    public String createMessageHash(String messageID, int messageNumber, String messageText) {

        String firstTwoNumbers = messageID.substring(0, 2);

        String cleanMessage = messageText.trim();

        String[] words = cleanMessage.split("\\s+");

        String firstWord = words[0];

        String lastWord = words[words.length - 1];

        String hash = firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

    public String generateMessageID() {

        String messageID;

        do {

            long number = 1000000000L + random.nextLong(9000000000L);

            messageID = String.valueOf(number);

        } while (messageIds.contains(messageID));

        return messageID;
    }

    public String checkRecipientCell(String cellphone) {

        if (cellphone.matches("^\\+27[0-9]{9}$") || cellphone.matches("^0[0-9]{9}$")) {

            return "Valid number";

        } else {

            return "Invalid number";
        }
    }

    public String SentMessage() {

        System.out.println("\nChoose what you want to do with this message!");

        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");

        System.out.println("Enter your choice: ");
        int choice = input.nextInt();
        
        input.nextLine();

        switch (choice) {

            case 1 -> {
                System.out.println("Message successfully sent");
                return "Sent";
            }

            case 2 -> {
                System.out.println("Press 0 to delete the message");
                int deleteChoice = input.nextInt();

                input.nextLine();

                if (deleteChoice == 0) {
                    System.out.println("Message deleted");

                    int lastIndex = messages.size() - 1;

                    messageIds.remove(lastIndex);
                    recipientNumber.remove(lastIndex);
                    messages.remove(lastIndex);
                    msgHash.remove(lastIndex);

                    return "Disregarded";
                } else {
                    System.out.println("Invalid option!");
                    return "Disregarded";
                }
            }

            case 3 -> {
                System.out.println("Message successfully stored");
                return "Stored";

            }

            default -> {
                System.out.println("Invalid option");
                return "Disregarded";
            }

        }
    }

    public String printMessages() {

        StringBuilder allMessages = new StringBuilder();

        System.out.println("\n===== All Messages =====");

        for (int i = 0; i < messages.size(); i++) {
            int messageNumber = i + 1;
            allMessages.append("\nMessage Number: ").append(messageNumber);
            allMessages.append("\nMessage ID: ").append(messageIdsList.get(i));
            allMessages.append("\nRecipient: ").append(recipientNumber.get(i));
            allMessages.append("\nMessage: ").append(messages.get(i));
            allMessages.append("\nMessage Hash: ").append(msgHash.get(i));
            allMessages.append("\nStatus: ").append(messageStatus.get(i));

            System.out.println("\n==============================");

            System.out.println("\nMessage Number: " + (messageNumber));
            System.out.println("Message ID: " + messageIdsList.get(i));
            System.out.println("Recipient: " + recipientNumber.get(i));
            System.out.println("Message: " + messages.get(i));
            System.out.println("Message Hash: " + msgHash.get(i));
            System.out.println("Status: " + messageStatus.get(i));
        }

        return allMessages.toString();
    }


    
}
