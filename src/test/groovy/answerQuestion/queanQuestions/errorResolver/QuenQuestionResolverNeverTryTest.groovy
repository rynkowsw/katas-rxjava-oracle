package answerQuestion.queanQuestions.errorResolver

import io.reactivex.Observable
import spock.lang.Specification

/**
 * Created by wojciech on 24.05.17.
 */
class QuenQuestionResolverNeverTryTest extends Specification {

    private String ERROR_MESSAGE = "Some planed error message"

    private Boolean errorGenerator(Boolean value){
        throw new RuntimeException(ERROR_MESSAGE)
    }


    def 'resolver on error always return false' (){
        given:
            Observable<Boolean> testedObservableWithErrorsEvents = Observable.just(true)
                    .map({ input -> errorGenerator(input) })

            QuenQuestionResolverNeverTry queenResolver = new QuenQuestionResolverNeverTry()
        when:
            Observable<Boolean> givenObservable = queenResolver.resolveProblem(testedObservableWithErrorsEvents)
        and:
            def result = givenObservable.blockingLast()
        then:
            result == false
    }
}
