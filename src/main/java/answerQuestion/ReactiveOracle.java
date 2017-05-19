package answerQuestion;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import answerQuestion.queanQuestions.errorProvider.ErrorAlwaysProvider;
import answerQuestion.queanQuestions.ErrorProvider;
import answerQuestion.queanQuestions.QuenQuestionResolver;
import answerQuestion.utils.Question;
import answerQuestion.utils.QuestionObservable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ReactiveOracle {

    private final long DELAY = 3000;

    private ErrorProvider errorProvider;

    private QuenQuestionResolver quenQuestionResolver;

    public  ReactiveOracle(){}

    public  ReactiveOracle(ErrorProvider errorProvider, QuenQuestionResolver quenQuestionResolver){
        this.errorProvider = errorProvider;
        this.quenQuestionResolver = quenQuestionResolver;
    }



    public Observable<String> answerQuestion() {
        return Answers.randomAnswer()
                .delay(DELAY, TimeUnit.MILLISECONDS)
                .toObservable();
    }

    private Observable<Single<String>> answerQuestions(Observable<Question> questions) {

        return questions.map(q -> Answers.randomAnswer() );
    }

    Observable <Boolean> shallTheTaxesToBeRaise(Observable<Question> questions) {

        return questions.map(q -> Answers.randomBooleanAnswer() );
    }


    Observable<String> answearsQuestionsSequent(List<Question> questions){

        Iterator questionIterator = questions.iterator();

        Observable<String> answers = null;
        while(questionIterator.hasNext())
        {
            questionIterator.next();
            answers = Observable.concat( answers , answerQuestion());
        }
        return answers;

    }


    Observable<Single<String>> answerQuestionsParallel(List<Question> questions){

        Iterator<Question> questionIterator = questions.iterator();

        Observable<Question> mergedQuestionsObservable = null;
        while(questionIterator.hasNext())
        {
            Observable<Question> question = Single.just(questionIterator.next()).toObservable();
            mergedQuestionsObservable = QuestionObservable.mergeQuestionsObservable( mergedQuestionsObservable , question);
        }
        return answerQuestions(mergedQuestionsObservable);

    }

    // Queen answer
    private Boolean answerForBeautyQueen(Question question){
        Boolean answer = Answers.randomBooleanAnswer();
        errorProvider.provideError();
        return answer;
    }

    public Observable <Boolean> answerQueenQuestion(Observable<Question> questions) {

        Observable <Boolean> oracleAnswers = questions.map(this::answerForBeautyQueen);

        return quenQuestionResolver.resolveProblem(oracleAnswers);

    }





}
