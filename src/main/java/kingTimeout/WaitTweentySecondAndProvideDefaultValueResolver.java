package kingTimeout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public class WaitTweentySecondAndProvideDefaultValueResolver implements KingTaxesTimeoutResolver {

    @Override
    public Observable<Boolean> resolveTimeout(Observable<Boolean> answers) {
        int DELAY_TIMEOUT = 20;
        return answers
                .timeout(DELAY_TIMEOUT, TimeUnit.SECONDS)
                .onErrorReturn(error -> false);
    }
}
