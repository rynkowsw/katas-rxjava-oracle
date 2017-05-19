package answerQuestion;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by wojciech on 08.05.17.
 */
public class Answers {

    private static final String[] answers = {
            "Insanity: doing the same thing over and over again and expecting different results",
            "A person who never made a mistake never tried anything new.",
            "Gravitation is not responsible for people falling in love.",
            "Look deep into nature, and then you will understand everything better."
    };

    public static String[] answers(){
        return answers;
    }

    public static Single<String> randomAnswer(){

        int randomAnswerNumber = new Random().nextInt(answers.length);

        return allPossibleAnswers()
                .repeat(2)
                .skip (randomAnswerNumber)
                .firstOrError();
    }

    public static Boolean randomBooleanAnswer(){
        Random random = new Random();

        return random.nextBoolean();
    }


    private static Observable<String> allPossibleAnswers(){
        return Observable.fromArray(answers);

    }

}