package answerQuestion

import io.reactivex.Single
import spock.lang.Specification

/**
 * Created by wojciech on 16.05.17.
 */
class AnswersTest extends Specification {

    def "it returns all defined answears as Observable sequence" () {
        when:
            Single<String> randomAnswer = Answers.randomAnswer()
            String[] allPosibleAnswers = Answers.answers()
        then:
            allPosibleAnswers.contains(randomAnswer.blockingGet())
    }

    def "test test" () {
        when:
            Single<String> randomAnswer = Answers.randomAnswer()
            String[] allPosibleAnswers = Answers.answers()
        then:
            allPosibleAnswers.contains("")
    }

}
