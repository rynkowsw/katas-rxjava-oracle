package kingTimeout

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import spock.lang.Specification

class KingTaxesTimeoutWaitResolverTest extends Specification {

    def 'resolver do not have a timeout' (){
        given:
            Boolean LAST_RECORD = false

            Observable<Boolean> testObservable = Observable.concat(
                    Observable.just(true,true,true,true).delay(25, TimeUnit.SECONDS),
                    Observable.just(LAST_RECORD)
            )

            KingTaxesTimeoutWaitResolver kingTaxesTimeoutWaitResolver = new KingTaxesTimeoutWaitResolver()

            Observable<Boolean> givenObservable = kingTaxesTimeoutWaitResolver.resolveTimeout(testObservable)
        when:
            def result = givenObservable.blockingLast()
        then:
            result == LAST_RECORD
    }

}
