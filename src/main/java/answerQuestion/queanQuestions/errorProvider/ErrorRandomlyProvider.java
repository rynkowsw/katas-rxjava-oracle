package answerQuestion.queanQuestions.errorProvider;

import java.util.Random;

import answerQuestion.queanQuestions.ErrorProvider;

/**
 * Created by wojciech on 18.05.17.
 */
public class ErrorRandomlyProvider implements ErrorProvider {

    @Override
    public void provideError() {

        Random random = new Random();

        if(random.nextBoolean()) {
            throw new RuntimeException(MESSAGE);
        }
    }
}
