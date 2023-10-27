package io.github.haname.service.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * @author SanseYooyea
 */
public enum ImageUtils {
    INSTANCE;

    public BufferedImage flipHorizontal(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage flippedImage = new BufferedImage(width, height, image.getType());
        AffineTransform at = new AffineTransform(-1, 0, 0, 1, width, 0);

        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        op.filter(image, flippedImage);

        return flippedImage;
    }
}
