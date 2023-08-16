package blackjack.view;

import java.util.Scanner;

public class InputView {

    public static String[] readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine().trim().split(",");
    }

    public static double readMoney(final String name) {
        System.out.println(name + "의 배팅 금액은?");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextDouble();
    }
}
