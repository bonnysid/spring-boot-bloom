package com.bonnysid.bloom.model;

public class UserContacts {
    private String instagram;
    private String vk;
    private String github;
    private String twitter;
    private String website;
    private String youtube;
    private String mainLink;

    public UserContacts() {
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getMainLink() {
        return mainLink;
    }

    public void setMainLink(String mainLink) {
        this.mainLink = mainLink;
    }

    @Override
    public String toString() {
        return "UserContacts{" +
                "instagram='" + instagram + '\'' +
                ", vk='" + vk + '\'' +
                ", github='" + github + '\'' +
                ", twitter='" + twitter + '\'' +
                ", website='" + website + '\'' +
                ", youtube='" + youtube + '\'' +
                ", mainLink='" + mainLink + '\'' +
                '}';
    }
}
