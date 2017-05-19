package answerQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import answerQuestion.utils.Question;
import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by wojciech on 09.03.17.
 */
public class Pilgrim {

    private ReactiveOracle reactiveOracle;

    public Pilgrim(){
        reactiveOracle = new ReactiveOracle();
    }


    // 2 kata
    public Observable<String> askThreeQuestionSequently(){
        List<Question> questions = new ArrayList<>( Arrays.asList( new Question(), new Question(), new Question() ));
        return reactiveOracle.answearsQuestionsSequent(questions);
    }

    // 3 kata
    public  Observable<Single<String>> askThreeQuestionInTheSameTime(){
        List<Question> questions = new ArrayList<>( Arrays.asList( new Question(), new Question(), new Question() ));
        return reactiveOracle.answerQuestionsParallel(questions);
    }

}
