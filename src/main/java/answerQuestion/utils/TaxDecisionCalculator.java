package answerQuestion.utils;

import io.reactivex.Observable;

/**
 * Created by wojciech on 15.05.17.
 */
public class TaxDecisionCalculator {

    public static Observable<Boolean> calculateAnswer(
            Observable<Boolean> first,
            Observable<Boolean> second,
            Observable<Boolean> third ){


        Observable<Boolean> middleResult1 = io.reactivex.Observable.zip (
                first,
                second,
                (Boolean answer1, Boolean  answer2) -> (answer1 && answer2)
        );

        Observable<Boolean> middleResult2 = io.reactivex.Observable.zip (
                second,
                third,
                (Boolean answer1, Boolean  answer2) -> (answer1 && answer2)
        );

        return io.reactivex.Observable.zip(
                middleResult1,
                middleResult2,
                (Boolean answer1, Boolean  answer2) -> (answer1  ||  answer2)
        );
    }
}
