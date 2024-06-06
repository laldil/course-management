package kz.edu.astanait.notificationservice.service;

import kz.edu.astanait.notificationservice.client.IdentityClient;
import kz.edu.astanait.notificationservice.dto.LinkTgRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aldi
 * @since 06.06.2024
 */
@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final IdentityClient identityClient;

    @Value("${telegram.name}")
    private String name;

    public TelegramBot(@Value("${telegram.token}") String token, IdentityClient identityClient) {
        super(token);
        this.identityClient = identityClient;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        boolean hasText = update.hasMessage() && update.getMessage().hasText();
        if (hasText) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                handleStartCommand(chatId, update.getMessage().getChat().getFirstName());
            } else if (messageText.startsWith("/link")) {
                handleLinkCommand(chatId, update.getMessage());
            } else if (messageText.startsWith("/code")) {
                handleCodeCommand(chatId, update.getMessage());
            } else {
                sendMessage(chatId, "Command not recognized");
            }
        }
    }

    private void handleStartCommand(Long chatId, String firstName) {
        String answer = "Hello, " + firstName;
        sendMessage(chatId, answer);
    }

    private void handleLinkCommand(Long chatId, Message message) {
        String[] messageText = message.getText().split(" ");

        if (messageText.length < 2) {
            sendMessage(chatId, "Please write an email after the /link command");
            return;
        }

        String email = messageText[1];
        if (!isValidEmail(email)) {
            sendMessage(chatId, "Invalid email. Please enter a valid email.");
            return;
        }

        try {
            identityClient.linkTg(chatId, email);
            sendMessage(chatId, "Confirmation code sent to email");
        } catch (Exception e) {
            sendMessage(chatId, "User not found");
            log.error(e.getMessage());
        }
    }

    private void handleCodeCommand(Long chatId, Message message) {
        String[] messageText = message.getText().split(" ");
        if (messageText.length < 2) {
            sendMessage(chatId, "Please specify the code after the /code command");
            return;
        }

        String code = messageText[1];
        try {
            identityClient.confirmLinkTg(new LinkTgRequest(
                    chatId,
                    code,
                    message.getChat().getUserName(),
                    message.getChat().getFirstName(),
                    message.getChat().getLastName()
            ));
            sendMessage(chatId, "Done!");
        } catch (Exception e) {
            sendMessage(chatId, e.getMessage());
            log.error(e.getMessage());
        }
    }
    /*
        HELPERS
     */

    public void sendMessage(Long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}

