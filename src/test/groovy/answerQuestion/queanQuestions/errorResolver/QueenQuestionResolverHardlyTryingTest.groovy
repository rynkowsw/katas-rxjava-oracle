package answerQuestion.queanQuestions.errorResolver

import io.reactivex.Observable
import spock.lang.Specification

class QueenQuestionResolverHardlyTryingTest extends Specification {

    private String ERROR_MESSAGE = "Some planed error message"

    private Boolean errorGenerator(Boolean value){
        throw new RuntimeException(ERROR_MESSAGE)
    }


    def 'resolver retry sequence 10 times and then throw error' (){
        given:
            Observable<Boolean> firstObservable = Observable.just(true,true,true,true)
                    .map({ input -> errorGenerator(input) })

            Observable<Boolean> testedObservableWithErrorsEvents = Observable.concat(
                    firstObservable,
                    Observable.just(false)
            )

            QueenQuestionResolverHardlyTrying queenResolver = new QueenQuestionResolverHardlyTrying()
        when:
            Observable<Boolean> givenObservable = queenResolver.resolveProblem(testedObservableWithErrorsEvents)
        and:
            givenObservable.blockingLast()
        then:
            Exception exception = thrown()
        and:
            exception.message == ERROR_MESSAGE
    }
}