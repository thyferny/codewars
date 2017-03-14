import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecretDetective {

    List<Character> lettersPool;
    List<Pair<Character, Character>> relations;

    public String recoverSecret(char[][] triplets) {
        init(triplets);

        String result = "";
        while (lettersPool.size() > 0){
            char next = findLeftmostLetter();
            result += next;
            removePairsWithKey(next);
        }

        return result;
    }

    private void init(char[][] triplets) {
        lettersPool = new ArrayList<>();
        relations = new ArrayList<>();

        for (char[] triplet : triplets) {
            for (int i = 0; i < 3; i++) {
                if (!lettersPool.contains(triplet[i])) {
                    lettersPool.add(triplet[i]);
                }
            }

            relations.add(new Pair<>(triplet[0], triplet[1]));
            relations.add(new Pair<>(triplet[0], triplet[2]));
            relations.add(new Pair<>(triplet[1], triplet[2]));
        }
    }

    private void removePairsWithKey(Character c){
        relations.removeIf(x -> x.getKey().equals(c));
        lettersPool.remove(c);
    }

    private Character findLeftmostLetter() {
        List<Character> pool = new ArrayList<>(lettersPool);
        Collections.copy(pool, lettersPool);

        for (Pair<Character, Character> pair : relations) {
            pool.remove(pair.getValue());
        }

        return pool.get(0);
    }

}