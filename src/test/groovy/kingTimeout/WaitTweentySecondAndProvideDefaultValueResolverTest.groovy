package kingTimeout

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import spock.lang.Specification

/**
 * Created by wojciech on 23.05.17.
 */
class WaitTweentySecondAndProvideDefaultValueResolverTest extends Specification {

    def 'resolver return correct value when delay is lower than 20 sec' (){
        given:
            Boolean LAST_RECORD = true

            Observable<Boolean> testObservable = Observable.concat(
                    Observable.just(true,true,true,true).delay(10, TimeUnit.SECONDS),
                    Observable.just(LAST_RECORD)
            )

            WaitTweentySecondAndProvideDefaultValueResolver kingTaxesTimeoutWaitResolver = new WaitTweentySecondAndProvideDefaultValueResolver()

            Observable<Boolean> givenObservable = kingTaxesTimeoutWaitResolver.resolveTimeout(testObservable)
        when:
            def result = givenObservable.blockingLast()
        then:
            result == LAST_RECORD
    }

    def 'resolver return false when delay is bigger than 20 sec' (){
        given:
            Boolean LAST_RECORD = true

            Observable<Boolean> testObservable = Observable.concat(
                    Observable.just(true,true,true,true).delay(21, TimeUnit.SECONDS),
                    Observable.just(LAST_RECORD)
            )

            WaitTweentySecondAndProvideDefaultValueResolver kingTaxesTimeoutWaitResolver = new WaitTweentySecondAndProvideDefaultValueResolver()

            Observable<Boolean> givenObservable = kingTaxesTimeoutWaitResolver.resolveTimeout(testObservable)
        when:
            def result = givenObservable.blockingLast()
        then:
            result != LAST_RECORD
        and:
            result == false
    }
}
