import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(msg);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        //this.replykeyboardMarkup.setKeyboard(keyboard);
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Наше меню\nhttps://sun9-4.userapi.com/c841539/v841539500/5c868/W-eFYH2vZK8.jpg");
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
     * Прайс пельменей
     * @param chat_id
     */
    protected void pelmeni(final long chat_id) throws TelegramApiException {
        SendPhoto photo = new SendPhoto()
                .setChatId(chat_id)
                .setPhoto("https://vk.com/albums-103273523?z=photo-103273523_456239033%2Fphotos-103273523");
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("10 шт. 190 руб.").setCallbackData("update_msg_text"));
        rowInline.add(new InlineKeyboardButton().setText("1 кг. 1000 руб.").setCallbackData("update_msg_text"));
        rowInline.add(new InlineKeyboardButton().setText("Корзина").setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        photo.setReplyMarkup(markupInline);
        try {
            execute(photo);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * Шаверма в пите
     * @param chat_id
     */
    public void shavermaInPita(final long chat_id) {
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма в пите (мясо, огурец, помидор, корейская капуста - морковь, лук и соус в пите обжаренной на гриле)\nhttps://sun9-54.userapi.com/c636324/v636324086/41fdd/wfFPfF_WMwE.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С курицей 230 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С говядиной 310 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С олениной 360 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(getAnswer("shinlavash"));
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText(getAnswer("shinlavashchicken"))
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText(getAnswer("shinlavashbeef"))
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText(getAnswer("shinlavashvenison"))
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText(getAnswer("shinlavashmutton"))
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма на тарелке (мясо, картофель фри, огурец, помидор, лук, капуста, морковь, соус подается с обжаренной на гриле питой)\nhttps://sun9-26.userapi.com/c636324/v636324086/41fe7/yS5FynNnjts.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С курицей 280 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С говядиной 360 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С олениной 410 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма классика (мясо, огурец, помидор, лук и соус в хрустящем лаваше)\nhttps://sun9-21.userapi.com/c636324/v636324086/41fd3/6mnMYH4k314.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С курицей 280 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С говядиной 360 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С олениной 410 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма по-турецки (мясо, картофель фри, огурец, помидор, корейская капуста - морковь, лук и соус в хрустящем лаваше)\n https://sun9-53.userapi.com/c636324/v636324086/41fc9/WYp29EqhrFs.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С курицей 260 руб.")
                .setCallbackData("tur260"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С говядиной 320 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С олениной 370 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма Буррито (мясо, корейская капуста - морковь, помидор, огурец, фасоль, кукуруза и острый томатный соус в хрустящем лаваше\nhttps://sun9-47.userapi.com/c637331/v637331086/2c654/BbWyglMTVks.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С курицей 210 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С говядиной 290 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С олениной 350 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText("Шаверма с грибами (шампиньоны обжаренные, огурец, помидор, сыр, корейская капуста - морковь, лук и соус в хрустящем лаваше)\nШаверма с фалафелем (фалафель, помидор, огурец, корейская капуста-морковь, лук и соус в хрустящем \n" +
                        "лаваше) https://sun9-15.userapi.com/c637331/v637331086/2c643/oggCnh8fglQ.jpg");
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С грибами 240 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("С фалафелем 220 руб.")
                .setCallbackData("update_msg_text"));
        rowsInline.add(rowInline);
        rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText("Корзина")
                .setCallbackData("update_msg_text"));
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
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(callbackId);
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setShowAlert(true);
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton()
                .setText(getPrace(callbackId))
                .setCallbackData("update_msg_text"));
        try {
            execute(answer);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
