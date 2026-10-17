/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe.part.pkg1;

import javax.swing.J0ptionPane;

/**
 *
 * @author Student
 */
public class QuickChat {

    public static void main(String[] args) {

        // Requirement 1: only logged-in users can send messages
        Login login = new Login();
        login.register();
        if (!login.login()) {
            return;
        }

        // Requirement 2
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        // Requirement 5: user sets how many messages they will enter
        int maxMessages = askForInt("How many messages do you wish to enter?");
        if (maxMessages < 0) {
            return;
        }
        int messagesEntered = 0;

        // Requirement 4: run until the user selects Quit
        boolean running = true;
        while (running) {
            String menu = "Choose an option:\n"
                    + "1) Send Messages\n"
                    + "2) Show recently sent messages\n"
                    + "3) Quit";
            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                break;
            }

            switch (input.trim()) {
                case "1":
                    if (messagesEntered >= maxMessages) {
                        JOptionPane.showMessageDialog(null,
                                "You have reached the number of messages you set (" + maxMessages + ").");
                        break;
                    }
                    if (sendOneMessage(messagesEntered)) {
                        messagesEntered++;
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please enter 1, 2 or 3.");
            }
        }

        JOptionPane.showMessageDialog(null,
                "Total messages sent: " + Message.returnTotalMessages()
                + "\n\n" + Message.printMessages());
    }

    // Returns true if a message was captured (sent, stored or disregarded)
    private static boolean sendOneMessage(int messageNumber) {
        String recipient = JOptionPane.showInputDialog("Enter recipient cell number (e.g. +27718693002):");
        if (recipient == null) {
            return false;
        }

        // Check the recipient number first
        Message check = new Message(recipient, "check", messageNumber);
        String cellResult = check.checkRecipientCell();
        JOptionPane.showMessageDialog(null, cellResult);
        if (!cellResult.startsWith("Cell phone number successfully")) {
            return false;
        }

        String text = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        String lengthResult = Message.checkMessageLength(text);
        if (!lengthResult.equals("Message sent")) {
            JOptionPane.showMessageDialog(null, lengthResult);
            return false;
        }

        Message message = new Message(recipient, text, messageNumber);
        JOptionPane.showMessageDialog(null, "Message ID generated: " + message.getMessageID()
                + "\nMessage Hash: " + message.getMessageHash());

        String result = message.sentMessage();
        JOptionPane.showMessageDialog(null, result);

        if (result.startsWith("Message successfully sent")) {
            JOptionPane.showMessageDialog(null, message.details());
        }
        return true;
    }

    private static int askForInt(String prompt) {
        while (true) {
            String s = JOptionPane.showInputDialog(prompt);
            if (s == null) {
                return -1;
            }
            try {
                int n = Integer.parseInt(s.trim());
                if (n > 0) {
                    return n;
                }
            } catch (NumberFormatException ignored) { }
            JOptionPane.showMessageDialog(null, "Please enter a whole number greater than 0.");
        }
    }
}



