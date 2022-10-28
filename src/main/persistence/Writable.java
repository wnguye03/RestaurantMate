package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

//Modeled after JsonSerializationDemo from phase 2 module

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJsonObj();

    // EFFECTS: returns this as JSONArray object
    JSONArray toJsonArray();
}
