package answerQuestion.queanQuestions.errorResolver;

import answerQuestion.queanQuestions.QuenQuestionResolver;
import io.reactivex.Observable;

public class QueenQuestionResolverHardlyTrying implements QuenQuestionResolver {

    @Override
    public Observable<Boolean> resolveProblem(Observable<Boolean> answers) {
        return answers.retry(10);
    }
}
