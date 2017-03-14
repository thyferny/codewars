import java.util.SortedSet;
import java.util.TreeSet;

import static java.lang.Long.parseLong;

public class ToSmallest {
    public static long[] smallest(long n) {
        SortedSet<Item> items = allItemsFrom(n);

        Item min = items.first();
        return new long[]{min.item, min.from, min.to};
    }

    private static SortedSet<Item> allItemsFrom(long n) {
        String digits = String.valueOf(n);
        SortedSet<Item> items = new TreeSet<>();

        StringBuilder sb = new StringBuilder(digits);
        for (int i = 0; i < digits.length(); i++) {
            for (int j = 0; j < digits.length(); j++) {
                if (j != i) {
                    move(sb, i, j);
                    items.add(new Item(parseLong(sb.toString()), i, j));
                    move(sb, j, i);
                }
            }
        }

        return items;
    }

    private static void move(StringBuilder sb, int from, int to) {
        char moved = sb.charAt(from);
        sb.deleteCharAt(from);
        sb.insert(to, moved);
    }

    private static class Item implements Comparable<Item> {
        long item;
        int from;
        int to;

        public Item(long item, int from, int to) {
            this.item = item;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Item o) {
            int itemsDiff = Long.compare(item, o.item);
            if (itemsDiff != 0) {
                return itemsDiff;
            }
            int fromDiff = Integer.compare(from, o.from);
            if (fromDiff != 0) {
                return fromDiff;
            }
            return Integer.compare(to, o.to);
        }
    }
}