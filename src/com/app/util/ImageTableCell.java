package com.app.util;
import java.io.ByteArrayInputStream;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell<S, T> extends TableCell<S, T> {

    private final ImageView imageView = new ImageView();
    
    public ImageTableCell() {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        setGraphic(imageView);
    }
    
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        
        if (empty || item == null) {
            setGraphic(null);
        } else {
            if (item instanceof String) {
                String imagePath = (String) item;
                Image image = new Image("file:" + imagePath);
                imageView.setImage(image);
                setGraphic(imageView);
            } else if (item instanceof byte[]) {
                byte[] imageBytes = (byte[]) item;
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                imageView.setImage(image);
                setGraphic(imageView);
            }
        }
    }
}
