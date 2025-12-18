package com.cain.green.javafxlogindashboard.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**

 * will load and manages AidShip object from the csv file

 */
public class AidShipManager {

    private final List<AidShip> ships = new ArrayList<>();

    public List<AidShip> getShips() {
        return ships;
    }

    /**
     * Load ships from a CSV resource in the classpath.
     * Expected columns

     *   0 = name
     *   1 = reg
     *   2 = location

     *   3 = supportType
     * Any extra columns are ignored. missing ones are filled with defualt values

     */
    public void loadFromCSV(String resourcePath) throws IOException {
        ships.clear();

        try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(is))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split(",");
                    if (parts.length < 2) {
                        System.err.println("Bad line in aid_ships.csv: " + line);
                        continue;
                    }

                    String name        = parts[0].trim();
                    String reg         = parts[1].trim();
                    String location    = parts.length > 2 ? parts[2].trim() : "Unknown";
                    String supportType = parts.length > 3 ? parts[3].trim() : "General";

                    ships.add(new AidShip(name, reg, location, supportType));
                }
            }
        }
    }
}
