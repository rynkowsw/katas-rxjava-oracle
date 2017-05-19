package answerQuestion.queanQuestions.errorResolver;

import answerQuestion.queanQuestions.QuenQuestionResolver;
import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public class QuenQuestionResolverNeverTry implements QuenQuestionResolver {

    @Override
    public Observable<Boolean> resolveProblem(Observable<Boolean> answers) {
        return answers.onErrorReturn(error -> false);
    }
}
