package org.prison.silicon.population;

import org.prison.silicon.SecurityRating;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InmateLoader {
    private Path dataFilePath;

    public InmateLoader(String dataFilePath) {
        this.dataFilePath = Path.of(dataFilePath);
    }

    public List<Inmate> load() throws IOException {
        List<Inmate> result = new ArrayList<>();

        Files.lines(dataFilePath).forEach( line -> {
        String[] tokens = line.split(",");

        int id = Integer.parseInt(tokens[0]);
        //int id = parsedId.intValue();
        String name = tokens[1];
        boolean gangLeader = Boolean.parseBoolean(tokens[2]);
        SecurityRating rating = SecurityRating.valueOf(tokens[3]);

        switch (rating) {
            case LOW:
                result.add(new LowSecurityInmate(id, name, gangLeader, rating));
                break;
            case MEDIUM:
                result.add(new MediumSecurityInmate(id, name, gangLeader, rating));
                break;
            case HIGH:
                result.add(new HighSecurityInmate(id, name, gangLeader, rating));
                break;
        }
        });
        return result;
    }
}