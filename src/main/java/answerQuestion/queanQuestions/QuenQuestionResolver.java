package answerQuestion.queanQuestions;

import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public interface QuenQuestionResolver {
    Observable<Boolean> resolveProblem(Observable <Boolean> answers);
}
