package com.lib.model;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private Integer farm;
    @SerializedName("title")
    private String title;
    @SerializedName("ispublic")
    private Integer ispublic;
    @SerializedName("isfriend")
    private Integer isfriend;
    @SerializedName("isfamily")
    private Integer isfamily;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return The server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return The farm
     */
    public Integer getFarm() {
        return farm;
    }

    /**
     * @param farm The farm
     */
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The ispublic
     */
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     * @param ispublic The ispublic
     */
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     * @return The isfriend
     */
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     * @param isfriend The isfriend
     */
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     * @return The isfamily
     */
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     * @param isfamily The isfamily
     */
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

}