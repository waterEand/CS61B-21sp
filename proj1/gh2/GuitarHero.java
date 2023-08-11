package gh2;
import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> strboard = new ArrayDeque<>();
        for (int i = 0; i < keyboard.length(); i += 1) {
            double hertz = 440.0 * Math.pow(2, (i - 24) / 12.0);
            GuitarString strSound = new GuitarString(hertz);
            strboard.addLast(strSound);
        }
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index_key = keyboard.indexOf(key);
                if (strboard.get(index_key) == null)
                    continue;
                strboard.get(index_key).pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i += 1) {
                sample += strboard.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < strboard.size(); i += 1) {
                strboard.get(i).tic();
            }
        }
    }

}
