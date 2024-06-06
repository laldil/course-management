package kz.edu.astanait.notificationservice.config;

import kz.edu.astanait.notificationservice.service.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aldi
 * @since 06.06.2024
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class TelegramBotConfig {
    private final TelegramBot telegramBot;

    @EventListener({ContextRefreshedEvent.class})
    private void init() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            List<BotCommand> botCommands = new ArrayList<>();
            botCommands.add(new BotCommand("/start", "Start the bot"));
            botCommands.add(new BotCommand("/link", "Link your account (use /link *your_email*)"));
            botCommands.add(new BotCommand("/code", "Confirm email (use /code *code*)"));
            telegramBot.execute(
                    new SetMyCommands(botCommands, new BotCommandScopeDefault(), null)
            );

            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}