package fr.manu.picom_api;

public enum AdvertisementType {
    IMAGE(1, "image"),
    HTML(2, "html");

    private Integer id;

    private String title;

    AdvertisementType(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
