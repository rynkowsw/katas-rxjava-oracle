package kingTimeout;

import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public class KingTaxesTimeoutWaitResolver implements KingTaxesTimeoutResolver {

    @Override
    public Observable<Boolean> resolveTimeout(Observable<Boolean> answers) {
        return answers;
    }
}
