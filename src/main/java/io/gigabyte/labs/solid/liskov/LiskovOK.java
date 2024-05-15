package io.gigabyte.labs.solid.liskov;

import java.util.List;

interface Displayable {
    void display();
}

interface Resizable {
    void resize(int width, int height);
}

interface Bouncable {
    void bounce();
}

class Image implements Displayable, Resizable  {
    public void display() {
        System.out.println("Displaying image");
    }

    public void resize(int width, int height) {
        System.out.println("Resizing image to width: " + width + ", height: " + height);
    }
}

class Video implements Displayable, Resizable {
    public void display() {
        System.out.println("Playing video");
    }

    public void resize(int width, int height) {
        System.out.println("Changing video resolution to width: " + width + ", height: " + height);
    }
}

class Audio implements Displayable {
    public void display() {
        System.out.println("Playing audio");
    }
}



public class LiskovOK {
    public static void main(String[] args) {
        List<Displayable> displayables = List.of(new Image(), new Video(), new Audio());
        displayables.stream().forEach(Displayable::display);
        List<Resizable> resizables = List.of(new Video(), new Image());
        resizables.stream().forEach(resizable -> resizable.resize(56, 45));
    }
}

