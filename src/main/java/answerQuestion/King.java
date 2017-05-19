package answerQuestion;

import java.util.concurrent.TimeUnit;

import answerQuestion.utils.Question;
import answerQuestion.utils.TaxDecisionCalculator;
import io.reactivex.Observable;
import kingTimeout.KingTaxesTimeoutResolver;
import kingTimeout.KingTaxesTimeoutWaitResolver;

/**
 * Created by wojciech on 13.04.17.
 */
public class King {

    private ReactiveOracle firstOracle, secondOracle, thirdOracle;

    private KingTaxesTimeoutResolver kingTaxesTimeoutResolver;

    private int DELAY_TIMEOUT = 10;

    public King (){
        firstOracle = new ReactiveOracle();
        secondOracle = new ReactiveOracle();
        thirdOracle = new ReactiveOracle();
        kingTaxesTimeoutResolver = new KingTaxesTimeoutWaitResolver();
    }

    public Observable<Boolean> shallRaiseTaxes( Observable<Question> questions ) {

        Observable<Boolean> raiseTaxesFirstOracleAnswears = firstOracle.shallTheTaxesToBeRaise(questions);
        Observable<Boolean> raiseTaxesSecondOracleAnswears = secondOracle.shallTheTaxesToBeRaise(questions);
        Observable<Boolean> raiseTaxesThirdOracleAnswears = thirdOracle.shallTheTaxesToBeRaise(questions);

        Observable<Boolean> calculatedResult = TaxDecisionCalculator.calculateAnswer(
                raiseTaxesFirstOracleAnswears,
                raiseTaxesSecondOracleAnswears,
                raiseTaxesThirdOracleAnswears
        );

        calculatedResult = kingTaxesTimeoutResolver.resolveTimeout(calculatedResult);

        return calculatedResult;
    }


    public void raiseTaxes(  ) {
        Observable<Question> questions = Observable.just( new Question(), new Question(), new Question() );
        shallRaiseTaxes(questions);
    }

}
