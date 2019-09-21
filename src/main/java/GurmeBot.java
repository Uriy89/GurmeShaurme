import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class GurmeBot extends TelegramLongPollingBot {
    private long chat_id;
    private ReplyKeyboardMarkup replykeyboardMarkup = new ReplyKeyboardMarkup();
    private static InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
    private final YamlMenu yaml = new YamlMenu("menu.yaml");

    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        this.chat_id = update.getMessage().getChatId();
        String message_text = update.getMessage().getText();
        sendMessage.setReplyMarkup(replykeyboardMarkup);
        String user_first_name = update.getMessage().getChat().getFirstName();
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        String hello = EmojiParser.parseToUnicode(user_first_name + ",  ознакомьтесь с нашем меню, закажите вкусную шаврему:yum:");
        if (update.hasMessage() && update.getMessage().hasText()) {
            keyBoard(chat_id, hello);
            if (message_text.equals(EmojiParser.parseToUnicode("Меню:book:"))) {
                shaverma(chat_id);
            } else if (message_text.equals("Шаверма в пите")) {
                shaverma(chat_id);
                shavermaInPita(chat_id);
            } else if (message_text.equals("Шаверма в лаваше")) {
                shaverma(chat_id);
                shavermaInLavash(chat_id);
            } else if (message_text.equals("На тарелке")) {
                shaverma(chat_id);
                shavermaOnPlate(chat_id);
            } else if (message_text.equals("Шаверма классика")) {
                shaverma(chat_id);
                shavermaClassic(chat_id);
            } else if (message_text.equals("Шаверма по-турецки")) {
                shaverma(chat_id);
                shavermaTurkish(chat_id);
            } else if (message_text.equals("Шаверма буррито")) {
                shaverma(chat_id);
                shavermaBurrito(chat_id);
            } else if (message_text.equals("Веган Шаверма")) {
                shaverma(chat_id);
                shavermaVegan(chat_id);
            } else if (message_text.equals("Корзина")) {
                basket(answer.getText());
            }

        }
    }

    /**
     * Основное меню
     * @param chat_id
     */
    private void keyBoard(final long chat_id, String msg) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(msg);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        this.replykeyboardMarkup.setSelective(true);
        this.replykeyboardMarkup.setResizeKeyboard(true);
        row.add(EmojiParser.parseToUnicode("Меню:book:"));
        row.add("Корзина");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("О Нас");
        keyboard.add(row);
        replykeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(replykeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выбор шавермы
     * @param chat_id
     */
    private void shaverma(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText("Наше меню\nhttps://sun9-4.userapi.com/c841539/v841539500/5c868/W-eFYH2vZK8.jpg");
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        this.replykeyboardMarkup.setSelective(true);
        this.replykeyboardMarkup.setResizeKeyboard(true);
        row.add("Шаверма в пите");
        row.add("Шаверма в лаваше");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("На тарелке");
        row.add("Шаверма классика");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Шаверма буррито");
        row.add("Шаверма по-турецки");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Веган Шаверма");
        row.add("Корзина");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(EmojiParser.parseToUnicode(":house:Главное меню"));
        keyboard.add(row);
        replykeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(replykeyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Шаверма в пите
     * @param chat_id
     */
    public void shavermaInPita(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.pita"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.pita.chiken")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.pita.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.pita.venison")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Шаверма в лаваше
     * @param chat_id
     */
    public void shavermaInLavash(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.lavash"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.lavash.chicken")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.lavash.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.lavash.venison")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.lavash.mutton")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * Шаверма на тарелке
     * @param chat_id
     */
    public void shavermaOnPlate(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.plate"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.plate.chicken")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.plate.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.plate.vension")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Классическая шаверма
     * @param chat_id
     */
    public void shavermaClassic(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.classic"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.classic.chicken")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.classic.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.classic.vension")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Шаверма по Турецки
     * @param chat_id
     */
    public void shavermaTurkish(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.turkish"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.turkish.chicken")).setCallbackData("tur260"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.turkish.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.turkish.vension")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Шаверма Буррито
     * @param chat_id
     */
    public void shavermaBurrito(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.burrito"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.burrito.chicken")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.burrito.beef")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.burrito.vension")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Веган шаверма
     * @param chat_id
     */
    public void shavermaVegan(final long chat_id) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(getAnswer("shaverma.vegan"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.vegan.mushrooms")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getAnswer("shaverma.vegan.falafel")).setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(String ... strings ){
        StringBuilder text = new StringBuilder();
        for (String var : strings) {
            text.append(yaml.getVariable(var));
            text.append(System.lineSeparator());
        }
        return text.toString();
    }

    private String getPrace(String msg) {
        int sale = Integer.parseInt(msg.replaceAll("[\\D]", ""));
        String sr = Integer.toString(sale);
        return sr;
    }

    public void basket(String callbackId) {
        SendMessage message = new SendMessage().setChatId(chat_id).setText(callbackId);
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setShowAlert(true);
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(getPrace(callbackId)).setCallbackData("update_msg_text"));
        try {
            execute(answer);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private int result(String str) {
        int res = Integer.parseInt(str.replaceAll("\\D+",""));
        return res;
    }

    @Override
    public String getBotUsername() {
        return "Gurme_shaurme_bot";
    }

    @Override
    public String getBotToken() {
        return "868461647:AAE7Pipi7JjM-Q0qtGMvSt16lfgZXoClVhs";
    }
}