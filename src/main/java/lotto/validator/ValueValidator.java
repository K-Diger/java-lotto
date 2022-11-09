package lotto.validator;

import java.util.List;
import java.util.Stack;

public class ValueValidator {

    public static void validateLottoPurchaseMoney(String lottoPurchaseMoney) {
        try {
            Integer.parseInt(lottoPurchaseMoney);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 최대 2,147,483,647 까지 입니다.");
        }
        if (Integer.parseInt(lottoPurchaseMoney) % 1000 != 0 || Integer.parseInt(lottoPurchaseMoney) == 0) {
            throw new IllegalArgumentException("[ERROR] 구매 금액은 1000으로 나누어 떨어지는 단위만 가능합니다.");
        }
    }

    public static void validateInputLottoNumber(List<Integer> numbers) {
        validateLottoNumbersLength(numbers);
        validateLottoNumbersOverlap(numbers);
        for (Integer number : numbers) {
            validateLottoNumbersRange(number);
        }
    }

    public static void validateLottoNumbersRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public static void validateLottoNumbersOverlap(List<Integer> numbers) {
        Stack<Integer> stack = new Stack<>();
        for (int numberIndex = 0; numberIndex < numbers.size() - 1; numberIndex++) {
            stack.push(numbers.get(numberIndex));
            if (stack.contains(numbers.get(numberIndex + 1))) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
        }
    }

    public static void validateLottoNumbersLength(List<Integer> numbers) {
        if (numbers.size() > 6) {
            throw new IllegalArgumentException("[ERROR] 보너스 숫자를 포함하지 않은 로또 번호 갯수는 최대 6개입니다.");
        }
    }

    public static void validateLottoNumbersLengthContainBonusNumber(List<Integer> numbers) {
        if (numbers.size() > 7) {
            throw new IllegalArgumentException("[ERROR] 보너스 숫자를 포함한 로또 번호 갯수는 최대 7개입니다.");
        }
    }
}
