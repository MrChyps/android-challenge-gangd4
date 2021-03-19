package helloandroid.m2dl.gangd4_android_challenge_mobe.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Objects;
import java.util.UUID;

@IgnoreExtraProperties
public class Score implements Comparable<Score> {
    /**
     * UUID of the score
     */
    private String id;

    /**
     * Long score value
     */
    private Long score;

    public Score() {
        // Default constructor required to call DataSnapshot.getValue(Score.class)
    }

    /**
     * Builds a new score
     * @param score score's value
     */
    public Score(Long score) {
        if (score == null) {
            score = 0L;
        }
        this.score = score;
        id = UUID.randomUUID().toString();
    }

    /**
     * Get the score's UUID
     * @return score's UUID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the score's value
     * @return score's value
     */
    public Long getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Score (id: "+id+",val : "+ score + ')';
    }

    @Override
    public int compareTo(Score o) {
        return o.score.compareTo(this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return id.equals(score.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
