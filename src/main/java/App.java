/**
 * Created on 27/02/2017.
 */
public class App {

    public static void main(String[] args) {
            ReactiveOracle reactiveOracle = new ReactiveOracle();


            String answer = reactiveOracle
                    .getAnswer()
                    .blockingGet();

            System.out.println("First king answer is: " + answer);


            Pilgrim pilgrim = new Pilgrim();
            pilgrim.printThreeAnswers();
        }
}
