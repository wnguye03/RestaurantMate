package persistence;

import model.MenuForRestaurant;
import model.Restaurent;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

//Modeled after JsonSerializationDemo from phase 2 module


// Represents a writer that writes JSON representation of restaurant to file
public class JsonWriter {
    protected PrintWriter writer;
    protected String dest;
//    private Restaurent restaurent;

    // EFFECTS: constructs a new writer to write to destination file
    public JsonWriter(String dest) {
        this.dest = dest;
//        this.restaurent = restaurent;
    }

    // MODIFIES: this
    // EFFECTS: if destination file cannot be opened for writing throw FileNotFoundException,
    //          otherwise opens writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(dest));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of target to file
    public void write(Restaurent restaurent) {
        JSONObject ja = restaurent.toJsonObj();
        saveToFile(ja.toString());
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        writer.print(json);
    }


}
