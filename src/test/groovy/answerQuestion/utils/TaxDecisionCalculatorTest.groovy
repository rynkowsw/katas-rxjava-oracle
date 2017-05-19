package answerQuestion.utils

import io.reactivex.Observable
import spock.lang.Specification

/**
 * Created by wojciech on 18.05.17.
 */
class TaxDecisionCalculatorTest extends Specification {

    Observable<Boolean> sourceFirst, sourceSecond, sourceThird

    Boolean TRUE_VALUE = true
    Boolean FALSE_VALUE = false

    def setup(){
        sourceFirst = Observable.empty()
        sourceSecond = Observable.empty()
        sourceThird = Observable.empty()
    }

    def "it return empty observable when one of source is empty"() {
        given:
            sourceFirst = Observable.just(TRUE_VALUE)
            sourceSecond = Observable.just(TRUE_VALUE)
        when:
            Observable<Boolean> result = TaxDecisionCalculator.calculateAnswer(sourceFirst,sourceSecond,sourceThird)
            Boolean isResultObservableEmpty = result.isEmpty().blockingGet()
        then:
            isResultObservableEmpty == TRUE_VALUE
    }

    def "it return true when at least 2 of source event are true"() {
        given:
            sourceFirst = Observable.just(TRUE_VALUE)
            sourceSecond = Observable.just(TRUE_VALUE)
            sourceThird = Observable.just(FALSE_VALUE)
        when:
            Observable<Boolean> result = TaxDecisionCalculator.calculateAnswer(sourceFirst,sourceSecond,sourceThird)
            Boolean booleanResult = result.blockingFirst()
        then:
            booleanResult == TRUE_VALUE

    }


    def "it return false when at least 2 of source event are false"() {
        given:
            sourceFirst = Observable.just(TRUE_VALUE)
            sourceSecond = Observable.just(FALSE_VALUE)
            sourceThird = Observable.just(FALSE_VALUE)
        when:
            Observable<Boolean> result = TaxDecisionCalculator.calculateAnswer(sourceFirst,sourceSecond,sourceThird)
            Boolean booleanResult = result.blockingFirst()
        then:
            booleanResult == FALSE_VALUE

    }
}
