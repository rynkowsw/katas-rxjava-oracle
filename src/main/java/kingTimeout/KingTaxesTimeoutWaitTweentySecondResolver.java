package kingTimeout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public class KingTaxesTimeoutWaitTweentySecondResolver implements KingTaxesTimeoutResolver {

    private static String message = "King can not wait longer for answer";

    public static String messageTimeout() {
        return message;
    }

    @Override
    public Observable<Boolean> resolveTimeout(Observable<Boolean> answers) {
        int DELAY_TIMEOUT = 20;
        return answers
                .timeout(DELAY_TIMEOUT, TimeUnit.SECONDS, io.reactivex.Observable.error(new RuntimeException(message)));
    }
}
