package answerQuestion.utils;

import io.reactivex.Observable;

/**
 * Created by wojciech on 17.05.17.
 */
public class QuestionObservable {


    public static Observable<Question> mergeQuestionsObservable(Observable<Question> source1, Observable<Question> source2){

        Observable<Question> result = null;

        boolean source1OnlyProvided = source1 != null && source2 == null;

        boolean source2OnlyProvided = source1 == null && source2 != null;

        boolean bothSourceAreProvided = source1 != null && source2 != null;

        if( source1OnlyProvided ) {
            result = source1;
        }
        else if( source2OnlyProvided ) {
            result = source2;
        }
        else if( bothSourceAreProvided ) {
            result = Observable.merge( source1 , source2);
        }

        return result;
    }

}
