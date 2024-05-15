package io.gigabyte.labs.solid.liskov.violation;

import java.util.List;

abstract class Media {
    public abstract void display();
    public abstract void resize(int width, int height);
}

class Image extends Media {
    @Override
    public void display() {
        System.out.println("Displaying image");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Resizing image to width: " + width + ", height: " + height);
    }
}

class Video extends Media {
    @Override
    public void display() {
        System.out.println("Playing video");
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Changing video resolution to width: " + width + ", height: " + height);
    }
}

//Cuando se introduce esta clase
class Audio extends Media {
    @Override
    public void display() {
        System.out.println("Playing audio");
    }

    @Override
    public void resize(int width, int height) {
        throw new UnsupportedOperationException("Cannot resize audio");
    }
}



class Player{
    public static void main(String[] args) {
        List<Media> medias = List.of(new Image(), new Video(), new Audio());
        medias.stream().forEach(Media::display);
        medias.stream().forEach(media -> media.resize(100,100));
    }
}

public class LiskovNonCompliant {
}
