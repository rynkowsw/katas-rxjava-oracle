package answerQuestion;

import answerQuestion.queanQuestions.ErrorProvider;
import answerQuestion.queanQuestions.QuenQuestionResolver;
import answerQuestion.queanQuestions.errorProvider.ErrorRandomlyProvider;
import answerQuestion.queanQuestions.errorResolver.QuenQuestionResolverTryThreeTimes;
import answerQuestion.utils.Question;
import io.reactivex.Observable;

public class Queen {

    private ReactiveOracle oracle;

    public Queen (){

        ErrorProvider errorProvider = new ErrorRandomlyProvider();
        QuenQuestionResolver quenQuestionResolver = new QuenQuestionResolverTryThreeTimes();

        this.oracle = new ReactiveOracle(errorProvider, quenQuestionResolver);
    }

    public Queen (ErrorProvider errorProvider, QuenQuestionResolver quenQuestionResolver ){
        this.oracle = new ReactiveOracle(errorProvider, quenQuestionResolver);
    }


    public Observable<Boolean> answerForMyQuestion() {

        Observable<Question> questions = Observable.just( new Question(), new Question(), new Question() );

        return oracle.answerQueenQuestion(questions);
    }

}
