package kingTimeout;

import io.reactivex.Observable;

/**
 * Created by wojciech on 18.05.17.
 */
public interface KingTaxesTimeoutResolver {
    Observable<Boolean> resolveTimeout( Observable<Boolean> answerObservable);
}
