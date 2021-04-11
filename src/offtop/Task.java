package offtop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    private static class Pair {
        private final int track;
        private final int index;

        public Pair(int track, int index) {
            this.track = track;
            this.index = index;
        }

        public int getTrack() {
            return track;
        }

        public int getIndex() {
            return index;
        }
    }

    private static int getNOD(int a, int b) {
        while (a != 0 && b != 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
        }

        return a + b;
    }

    private static int getValidIndex (ArrayList<Pair> arr, int index) {
        if (index >= arr.size()) return 0;
        return index;
    }

    public static void main(String[] args) throws IOException {
        Scanner reader = new Scanner(System.in);
        int testsNum = reader.nextInt();
        for (int i = 0; i < testsNum; i++) {
            int n = reader.nextInt();
            ArrayList<Pair> arr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                arr.add(new Pair(reader.nextInt(), j));
            }
            int size = n;
            int l = 0, r = 1;
            int corrTracks = 0;
            ArrayList<Integer> removed = new ArrayList<>();
            while (corrTracks != size) {
                l = getValidIndex(arr, l);
                r = getValidIndex(arr, r);
                Pair pair1 = arr.get(l);
                Pair pair2 = arr.get(r);
                int track1 = pair1.getTrack();
                int track2 = pair2.getTrack();
                int nod = getNOD(track1, track2);
                if (nod > 1) {
                    corrTracks++;
                } else {
                    corrTracks = 0;
                    size--;
                    removed.add(pair2.getIndex() + 1);
                    arr.remove(r);
                }
                if (size > 0) {
                    l = r;
                    r++;
                }
            }
            System.out.print(removed.size() + " ");
            for (int track : removed) {
                System.out.print(track + " ");
            }
            System.out.println();
        }
    }
}
