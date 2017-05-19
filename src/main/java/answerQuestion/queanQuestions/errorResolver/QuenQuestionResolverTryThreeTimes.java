package answerQuestion.queanQuestions.errorResolver;

import answerQuestion.queanQuestions.QuenQuestionResolver;
import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public class QuenQuestionResolverTryThreeTimes implements QuenQuestionResolver {

    @Override
    public Observable<Boolean> resolveProblem(Observable<Boolean> answers) {
        return answers
                .retry(3)
                .onErrorReturn(error -> false);
    }
}
