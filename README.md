This is my ReadMe

# QuickChat – PROG5121 POE Part 2

A console-based messaging application written in Java. Users register, log in, and then send, store or disregard short messages from a numeric menu. Stored messages are saved to a JSON file.

**Student:** FORTUNATE KUTLWANO MALOPE  
**Student number:** ST10528034  
**Module:** PROG5121 – Programming 1A  
**Branch used for this task:** Master

## Features

- Register with a username, password and cell number, then log in (messages can only be sent after a successful login).
- Displays `Welcome to QuickChat.` after login.
- Numeric menu that runs until the user chooses Quit:
  1. Send Messages
  2. Show recently sent messages (displays `Coming Soon.`)
  3. Quit
- The user chooses how many messages they want to enter when the application starts, and can only enter that many.
- Each message has:
  - a randomly generated, unique ten-digit **Message ID**
  - a **message number** (starts at 0)
  - a **recipient** cell number (international code such as `+27718693002`, or a local number such as `0817421062`)
  - a **message** of no more than 250 characters
  - an auto-generated **Message Hash**, e.g. `00:0:HITONIGHT` (first two digits of the ID, message number, first and last words of the message, all in capitals)
- For each message the user can **send**, **disregard** or **store** it.
- Stored messages are saved in `messages.json` using the Gson library.

## Project structure

```
src/poe/part/pkg1/
    Login.java        – registration and login checks
    Message.java      – message ID, recipient check, hash, send/store, JSON storage
    QuickChat.java    – main program and menu
test/poe/part/pkg1/
    LoginTest.java    – unit tests for Login
    MessageTest.java  – unit tests for Message
.github/workflows/
    TestJava.yml      – GitHub Actions workflow that runs the unit tests
```

## Requirements

- Java 17 (or newer)
- NetBeans IDE
- JUnit 4.13 and Hamcrest (for the unit tests)
- Gson 2.10.1 (for storing messages as JSON)

## How to run

1. Clone the repository: `git clone https://github.com/ST10528034/YOUR-REPO-NAME.git`
2. Open the project in NetBeans (File → Open Project).
3. Add the Gson jar under Project Properties → Libraries → Compile → Add JAR/Folder.
4. Right-click `QuickChat.java` and choose **Run File**.

Example input when asked for a recipient: `+27718693002`

## How to run the unit tests

1. Make sure JUnit 4.13 and Hamcrest are added under Project Properties → Libraries → Compile Tests.
2. Right-click the project and choose **Test** (or right-click a test class and choose **Test File**).

The tests use the data from the question paper:

| Item | Value |
|------|-------|
| Recipient | `+27718693002` |
| Message | `Hi Mike, can you join us for dinner tonight?` |
| Expected hash | `00:0:HITONIGHT` |
| Send / disregard / store | `Message successfully sent.` / `Press 0 to delete the message.` / `Message successfully stored.` |

They also check message length (over 250 characters is rejected), the recipient number formats, the message ID, the total number of messages sent, and the loop of message hashes.

## Continuous integration

Every push to `main` or `master`, and every pull request to `main`, runs the unit tests automatically with GitHub Actions (`.github/workflows/TestJava.yml`). The result is on the repository's **Actions** tab.

## JSON storage

Choosing "Store Message to send later" adds the message to `messages.json`:

```json
[
  {
    "messageID": "4863562820",
    "messageNumber": 0,
    "recipient": "+27718693002",
    "message": "Hi Mike, can you join us for dinner tonight?",
    "messageHash": "48:0:HITONIGHT"
  }
]
```

## References

- Google Gson. (n.d.). *Gson*. https://github.com/google/gson
- Google Gson. (n.d.). *Gson User Guide*. https://github.com/google/gson/blob/main/UserGuide.md
