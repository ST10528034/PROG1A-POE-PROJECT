/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package poe.part.pkg1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {

    private static final String TEXT =
            "Hi Mike, can you join us for dinner tonight?";
    private static final String NUMBER = "+27718693002";

    @Before
    public void setUp() {
        Message.resetAll();
    }

    @Test
    public void testRecipientCorrectlyFormatted() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);

        assertEquals(
                "Cell phone number successfully captured.",
                m.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message m = new Message(
                "0012345678", "071869", TEXT, 0
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the number "
                + "and try again.",
                m.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientLocalNumberAccepted() {
        Message m = new Message("0012345678", "0817421062", TEXT, 0);

        assertTrue(Message.checkLocalRecipient("0817421062"));
        assertEquals(
                "Cell phone number successfully captured.",
                m.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientInternationalNumberAccepted() {
        assertTrue(Message.checkInternationalRecipient("+27817421062"));
        assertFalse(Message.checkInternationalRecipient("0817421062"));
        assertFalse(Message.checkLocalRecipient("+27817421062"));
    }

    @Test
    public void testMessageHashCorrect() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);

        assertEquals("00:0:HITONIGHT", m.createMessageHash());
    }

    @Test
    public void testMessageHashesInLoop() {
        String[] texts = {
            "Hi, thanks",
            "Hi Mike, can you join us for dinner tonight?",
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        };

        String[] expected = {
            "00:0:HITHANKS",
            "00:1:HITONIGHT",
            "00:2:WHERETIME",
            "00:3:OKYOU"
        };

        for (int i = 0; i < texts.length; i++) {
            Message m = new Message(
                    "0012345678", NUMBER, texts[i], i
            );

            assertEquals(expected[i], m.createMessageHash());
        }
    }

    @Test
    public void testMessageIDCreated() {
        Message m = new Message(NUMBER, TEXT, 0);

        assertNotNull(m.getMessageID());
        assertEquals(10, m.getMessageID().length());
        assertTrue(m.checkMessageID());

        System.out.println(
                "Message ID generated: " + m.getMessageID()
        );
    }

    @Test
    public void testMessageIDTooLong() {
        Message m = new Message("12345678901", NUMBER, TEXT, 0);

        assertFalse(m.checkMessageID());
    }

    @Test
    public void testMessageLengthTooLong() {
        String longText = "a".repeat(251);

        assertEquals(
                "Please enter a message of less than 250 characters.",
                Message.checkMessageLength(longText)
        );
    }

    @Test
    public void testMessageLengthOk() {
        assertEquals(
                "Message sent",
                Message.checkMessageLength(TEXT)
        );
    }

    @Test
    public void testSendMessage() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);

        assertEquals(
                "Message successfully sent.",
                m.sentMessage(1)
        );

        assertEquals(1, Message.returnTotalMessages());
    }

    @Test
    public void testDisregardMessage() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);

        assertEquals(
                "Press 0 to delete the message.",
                m.sentMessage(2)
        );

        assertEquals(0, Message.returnTotalMessages());
    }

    @Test
    public void testStoreMessage() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);

        assertEquals(
                "Message successfully stored.",
                m.sentMessage(3)
        );

        assertEquals(0, Message.returnTotalMessages());
    }

    @Test
    public void testReturnTotalMessages() {
        new Message("0012345678", NUMBER, TEXT, 0)
                .sentMessage(1);

        new Message("0112345678", NUMBER, "Hi, thanks", 1)
                .sentMessage(1);

        assertEquals(2, Message.returnTotalMessages());
    }

    @Test
    public void testPrintMessages() {
        Message m = new Message("0012345678", NUMBER, TEXT, 0);
        m.sentMessage(1);

        String printed = m.printMessages();

        assertTrue(printed.contains("0012345678"));
        assertTrue(printed.contains("00:0:HITONIGHT"));
        assertTrue(printed.contains(NUMBER));
        assertTrue(printed.contains(TEXT));
    }
}