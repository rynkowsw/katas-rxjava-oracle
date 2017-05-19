package answerQuestion.queanQuestions.errorResolver

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import spock.lang.Specification

class QuenQuestionResolverTryThreeTimesTest extends Specification {

    private String ERROR_MESSAGE = "Some planed error message"

    private int RETRY_NUMBER_WITH_ERROR_LOWER_THAN_THREE = 2

    private int RETRY_NUMBER_WITH_ERROR_BIGGER_THAN_THREE = 4

    def 'resolver on error retry 3 time before return false for error'() {
        given:
            TestErrorGenerator errorGenerator = new TestErrorGenerator()

            Observable<Boolean> testedObservableWithErrorsEvents = Observable.just(true, true, true, false)
                    .map({ input -> errorGenerator.errorGenerator(input, RETRY_NUMBER_WITH_ERROR_LOWER_THAN_THREE) })

            QuenQuestionResolverTryThreeTimes queenResolver = new QuenQuestionResolverTryThreeTimes()

            TestObserver observer = new TestObserver()
        when:
            Observable<Boolean> givenObservable = queenResolver.resolveProblem(testedObservableWithErrorsEvents)
            givenObservable.subscribe(observer)
        then:
            observer.assertResult(true, true, true, false)
    }

    def 'resolver on error retry 3 time then if error occur return false for error and finish observable'() {
        given:
            TestErrorGenerator errorGenerator = new TestErrorGenerator()

            Observable<Boolean> testedObservableWithErrorsEvents = Observable.just(true, true, true, false)
                    .map({ input -> errorGenerator.errorGenerator(input, RETRY_NUMBER_WITH_ERROR_BIGGER_THAN_THREE) })

            QuenQuestionResolverTryThreeTimes queenResolver = new QuenQuestionResolverTryThreeTimes()

            TestObserver observer = new TestObserver()
        when:
            Observable<Boolean> givenObservable = queenResolver.resolveProblem(testedObservableWithErrorsEvents)
            givenObservable.subscribe(observer)
        then:
            observer.assertResult(false)
    }

    private class TestErrorGenerator {

        int tryCounter = 0

        Boolean errorGenerator(Boolean value, int tryNumber) {
            if (tryCounter < tryNumber){
                tryCounter++
                throw new RuntimeException(ERROR_MESSAGE)
            }
            else {
                return value
            }
        }
    }

}
