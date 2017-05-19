package answerQuestion.queanQuestions.errorProvider;

import answerQuestion.queanQuestions.ErrorProvider;

/**
 * Created by wojciech on 18.05.17.
 */
public class ErrorAlwaysProvider implements ErrorProvider {

    @Override
    public void provideError() {
        throw new RuntimeException(MESSAGE);
    }
}
