package com.example.tugasbesar;

class News {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private final int imageResource;

    public News(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource; }

    public int getImageResource() {
        return imageResource;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }
}
