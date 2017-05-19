package answerQuestion.utils

import io.reactivex.Observable
import spock.lang.Specification

/**
 * Created by wojciech on 17.05.17.
 */
class QuestionObservableTest extends Specification {

    Question quest, quest1, quest2, quest3
    List<Question> questionList;
    Observable<Question> source1, source2

    def setup(){
        quest = new Question();
        quest1 = new Question()
        quest2 = new Question()
        quest3 = new Question()

        questionList = Arrays.asList(quest,quest1,quest2,quest3)

        source1 = Observable.just(quest, quest1)
        source2 = Observable.just(quest2, quest3)
    }

    def "Merge too question streams into one"() {
        when:
            Observable<Question> result = QuestionObservable.mergeQuestionsObservable(source1,source2)
        then:
            List<Question> questionResultList = result.toList().blockingGet()
            questionResultList.containsAll(questionList)
    }

    def "First event from first source  is first in result"() {
        when:
            Observable<Question> result = QuestionObservable.mergeQuestionsObservable(source1,source2)
            Question question = result.blockingFirst()
        then:
            question == quest
    }

    def "Last event from second source  is last in result, when there is not event sheduler"() {
        when:
            Observable<Question> result = QuestionObservable.mergeQuestionsObservable(source1,source2)
            Question lastQuestionInResult = result.blockingLast()
        then:
            lastQuestionInResult == quest3
    }
}
