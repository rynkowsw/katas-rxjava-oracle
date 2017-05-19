package kingTimeout

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import spock.lang.Specification

class KingTaxesTimeoutWaitTweentySecondResolverTest extends Specification {

    def 'resolver on timeout throw exception' (){
        given:
            Observable<Boolean> testObservable = Observable.concat(
                    Observable.just(true,true,true,true).delay(30, TimeUnit.SECONDS),
                    Observable.just(true)
            )


            KingTaxesTimeoutWaitTweentySecondResolver kingTaxesTimeoutWaitTweentySecondResolver =
                    new KingTaxesTimeoutWaitTweentySecondResolver()


            Observable<Boolean> givenObservable =
                    kingTaxesTimeoutWaitTweentySecondResolver.resolveTimeout(testObservable)
        when:

            givenObservable.blockingLast()
        then:
            Exception exception = thrown()
        and:
            exception.getMessage() == KingTaxesTimeoutWaitTweentySecondResolver.messageTimeout()
    }

    def 'resolver on delay, wait 20sec for results' (){
        given:
            Boolean LAST_RECORD = false
            Integer TIME_LOWER_THAN_20 = 19

            Observable<Boolean> testObservable = Observable.concat(
                    Observable.just(true,true,true,true).delay(TIME_LOWER_THAN_20, TimeUnit.SECONDS),
                    Observable.just(LAST_RECORD)
            )


            KingTaxesTimeoutWaitTweentySecondResolver kingTaxesTimeoutWaitTweentySecondResolver =
                    new KingTaxesTimeoutWaitTweentySecondResolver()


            Observable<Boolean> givenObservable =
                    kingTaxesTimeoutWaitTweentySecondResolver.resolveTimeout(testObservable)
        when:

            def result = givenObservable.blockingLast()
        then:
            result == LAST_RECORD
    }

}
