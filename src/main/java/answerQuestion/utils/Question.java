
package answerQuestion.utils;

import java.util.UUID;

/**
 * Created by wojciech on 11.05.17.
 *
 * Tis is only object what help in understanding process
 */
public class Question {

    private String uniqueID ;

    public Question() {
        uniqueID = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return uniqueID != null ? uniqueID.equals(question.uniqueID) : question.uniqueID == null;
    }

    @Override
    public int hashCode() {
        return uniqueID != null ? uniqueID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Question{" +
                "uniqueID='" + uniqueID + '\'' +
                '}';
    }
}
