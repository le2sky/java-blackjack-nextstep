package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String READ_PLAYER_NAME_MESSAGE =
            "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String PLAYER_NAME_DELIMITER = ",";
    private static final String READ_MONEY_TEMPLATE = "%s 의 배팅 금액은?";

    public static String[] readPlayerNames() {
        System.out.println(READ_PLAYER_NAME_MESSAGE);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine()
                .trim()
                .split(PLAYER_NAME_DELIMITER);
    }

    public static double readMoney(final String name) {
        System.out.format(READ_MONEY_TEMPLATE, name);
        Scanner scanner = new Scanner(System.in);

        return scanner.nextDouble();
    }
}
