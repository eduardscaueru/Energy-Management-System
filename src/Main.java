import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import reader.InputGame;
import writer.Write;

import java.io.File;

public final class Main {
    private Main() {

    }

    /**
     * Se realizeaza citirea, simularea jocului si scrierea in fisier.
     *
     * @param args      numele celor 2 fisiere de input, respectiv output
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        String fin = args[0];
        String fout = args[1];

        ObjectMapper objectMapper = new ObjectMapper();
        InputGame inputGame = objectMapper.readValue(new File(fin), InputGame.class);

        Game game = Game.getInstance();
        game.fillInputData(inputGame);

        Write output = game.start();
        objectMapper.writeValue(new File(fout), output);
    }
}
