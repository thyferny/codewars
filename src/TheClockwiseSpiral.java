public class TheClockwiseSpiral {

    private static boolean isJActive = true;

    public static int[][] createSpiral(int n) {
        int[][] spiral = new int[n][n];

        int[] position = {0, 0};

        for (int number = 1; number <= n * n; number++) {
            spiral[position[0]][position[1]] = number;

            position = TheClockwiseSpiral.updateArrayPosition(position, n, spiral);
        }

        return spiral;
    }

    private static boolean canSetNumberToArray(int i, int j, int n, int[][] spiral) {
        return 0 <= i && i < n && 0 <= j && j < n && spiral[i][j] == 0;
    }

    private static int[] updateArrayPosition(int[] position, int n, int[][] spiral) {
        if (TheClockwiseSpiral.isJActive) {
            if (TheClockwiseSpiral.canSetNumberToArray(position[0], position[1] + 1, n, spiral)) {
                position[1] += 1;
            } else if(TheClockwiseSpiral.canSetNumberToArray(position[0], position[1] - 1, n, spiral)) {
                position[1] -= 1;
            } else {
                TheClockwiseSpiral.isJActive = false;
            }
        }

        if (!TheClockwiseSpiral.isJActive) {
            if(TheClockwiseSpiral.canSetNumberToArray(position[0] + 1, position[1], n, spiral)) {
                position[0] += 1;
            } else if(TheClockwiseSpiral.canSetNumberToArray(position[0] - 1, position[1], n, spiral)) {
                position[0] -= 1;
            } else {
                TheClockwiseSpiral.isJActive = true;

                if (TheClockwiseSpiral.canSetNumberToArray(position[0], position[1] + 1, n, spiral)) {
                    position[1] += 1;
                } else if(TheClockwiseSpiral.canSetNumberToArray(position[0], position[1] - 1, n, spiral)) {
                    position[1] -= 1;
                }
            }
        }

        return position;
    }

}