package com.dailyinspirationalquotes.lifequotes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalQuotesModel {

    @SerializedName("general")
    @Expose
    private Integer general;

    @SerializedName("attitude")
    @Expose
    private Integer attitude;

    @SerializedName("beauty")
    @Expose
    private Integer beauty;

    @SerializedName("best")
    @Expose
    private Integer best;

    @SerializedName("marriage")
    @Expose
    private Integer marriage;

    @SerializedName("medical")
    @Expose
    private Integer medical;

    @SerializedName("men")
    @Expose
    private Integer men;

    @SerializedName("mom")
    @Expose
    private Integer mom;

    @SerializedName("money")
    @Expose
    private Integer money;

    @SerializedName("morning")
    @Expose
    private Integer morning;
    @SerializedName("motivational")
    @Expose
    private Integer motivational;
    @SerializedName("movies")
    @Expose
    private Integer movies;
    @SerializedName("music")
    @Expose
    private Integer music;
    @SerializedName("nature")
    @Expose
    private Integer nature;
    @SerializedName("parenting")
    @Expose
    private Integer parenting;
    @SerializedName("patience")
    @Expose
    private Integer patience;
    @SerializedName("patriotism")
    @Expose
    private Integer patriotism;
    @SerializedName("peace")
    @Expose
    private Integer peace;

    public TotalQuotesModel() {
    }

    public Integer getGeneral() {
        return general;
    }

    public void setGeneral(Integer general) {
        this.general = general;
    }

    public Integer getAttitude() {
        return attitude;
    }

    public void setAttitude(Integer attitude) {
        this.attitude = attitude;
    }

    public Integer getBeauty() {
        return beauty;
    }

    public void setBeauty(Integer beauty) {
        this.beauty = beauty;
    }

    public Integer getBest() {
        return best;
    }

    public void setBest(Integer best) {
        this.best = best;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getMedical() {
        return medical;
    }

    public void setMedical(Integer medical) {
        this.medical = medical;
    }

    public Integer getMen() {
        return men;
    }

    public void setMen(Integer men) {
        this.men = men;
    }

    public Integer getMom() {
        return mom;
    }

    public void setMom(Integer mom) {
        this.mom = mom;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMorning() {
        return morning;
    }

    public void setMorning(Integer morning) {
        this.morning = morning;
    }

    public Integer getMotivational() {
        return motivational;
    }

    public void setMotivational(Integer motivational) {
        this.motivational = motivational;
    }

    public Integer getMovies() {
        return movies;
    }

    public void setMovies(Integer movies) {
        this.movies = movies;
    }

    public Integer getMusic() {
        return music;
    }

    public void setMusic(Integer music) {
        this.music = music;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getParenting() {
        return parenting;
    }

    public void setParenting(Integer parenting) {
        this.parenting = parenting;
    }

    public Integer getPatience() {
        return patience;
    }

    public void setPatience(Integer patience) {
        this.patience = patience;
    }

    public Integer getPatriotism() {
        return patriotism;
    }

    public void setPatriotism(Integer patriotism) {
        this.patriotism = patriotism;
    }

    public Integer getPeace() {
        return peace;
    }

    public void setPeace(Integer peace) {
        this.peace = peace;
    }

}
