package com.example.summerShop.util;

public class MailUtils {

    public static final String SUBJECT_VERIFYING = "Активуйте, будь ласка, аккаунт на сайті Summer Shop";

    private static final String formatMessageForVerifying =
            """
                    Вітаємо вас, %s, в інтернет магазині Summer Shop.
                    Для завершення реєстрації необхідно лише натиснути на посилання нижче:
                    http://%s/api/v1/auth/activation/%s
                    Навсе добре і гарних покупок""";

    public static String createMessageForVerifying(String username, String hostName, String activationCode) {
           return String.format(formatMessageForVerifying,
                   username, hostName, activationCode);
    }
}
