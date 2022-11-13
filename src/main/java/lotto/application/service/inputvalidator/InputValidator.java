package lotto.application.service.inputvalidator;

import java.util.List;

public interface InputValidator {

    void validatePurchaseMoneyIsInteger(String lottoPurchaseMoney);

    void validatePurchaseMoneyDividable(String lottoPurchaseMoney);


    void validateWinningNumbersAreInteger(String[] inputValue);

    void validateWinningNumbersAreOverlapped(List<Integer> numbers);

    void validateWinningNumbersLengthIsSix(List<Integer> numbers);

    void winningNumbersAreNumeric(String winningNumbers);

    void validateWinningNumbersRange(int number);

    void validateBonusNumberIsInteger(String inputValue);

    void validateBonusNumberIsOverlapped(List<Integer> numbers, int bonusNumber);

    void validateBonusNumberRange(int bonusNumber);

    void validateLottoPurchaseMoney(String lottoPurchaseMoney);

    void validateInputLottoNumber(List<Integer> numbers);

    void validateInputBonusLottoNumber(String bonusNumber, List<Integer> numbers);
}
