package com.bonnysid.bloom.model.enums;

public enum LinksTypes {
    INSTAGRAM("github"),
    VK("vk"),
    GITHUB("github"),
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    WEBSITE("website"),
    YOUTUBE("youtube"),
    MAIN_LINK("mainLink");

    private final String site;

    LinksTypes(String site) {
        this.site = site;
    }

    public String getSite() {
        return site;
    }
}
