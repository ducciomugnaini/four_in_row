package com.softly.utilities.json;

import com.google.gson.annotations.SerializedName;

public class Configuration {

    @SerializedName("AuthorizeUrl")
    private String authorizeUrl;

    @SerializedName("MoveRequestUrl")
    private String moveRequestUrl;

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public String getMoveRequestUrl() {
        return moveRequestUrl;
    }

    public void setMoveRequestUrl(String moveRequestUrl) {
        this.moveRequestUrl = moveRequestUrl;
    }
}
