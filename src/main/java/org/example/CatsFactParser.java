package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;

public class CatsFactParser {
    private final String _id;
    private final String text;
    private final String type;
    private final JSONObject user;
    private final int upvotes;
    private final String userUpvoted;

    public CatsFactParser(
            @JsonProperty("_id") String _id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") JSONObject user,
            @JsonProperty("upvotes") int upvotes,
            @JsonProperty("userUpvoted") String userUpvoted) {

        this._id = _id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
        this.userUpvoted = userUpvoted;
    }

    public String get_id() {
        return _id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public JSONObject getUser() {
        return user;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public String getUserUpvoted() {
        return userUpvoted;
    }

    @Override
    public String toString() {
        return "\n Upvotes: " + upvotes + " Fact: " + text;
    }
}