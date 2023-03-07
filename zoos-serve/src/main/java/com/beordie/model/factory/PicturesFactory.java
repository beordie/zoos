package com.beordie.model.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beordie.model.entity.Pictures;
import com.beordie.model.entity.User;
import com.beordie.model.request.AnimalPicture;
import net.sf.jsqlparser.statement.select.Pivot;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description //TODO
 * @Version 1
 * @Date 2023/3/3 14:54
 * @User beordie
 */
public class PicturesFactory {
    public static QueryWrapper<Pictures> buildQueryRotated() {
        QueryWrapper<Pictures> wrapper = new QueryWrapper<>();
        wrapper.eq("rotated", 1);
        return wrapper;
    }

    public static Pictures buildUpdateRotated(int pictureId, int rotated) {
        Pictures pictures = new Pictures();
        pictures.setRotated(rotated);
        pictures.setId(pictureId);
        return pictures;
    }

    public static QueryWrapper<Pictures> buildQuerySmall(int animalId) {
        QueryWrapper<Pictures> wrapper = new QueryWrapper<>();
        wrapper.eq("main_picture", 1);
        wrapper.eq("aid", animalId);
        return wrapper;
    }

    public static List<Pictures> buildNewAnimalPicture(AnimalPicture animalPicture) {
        List<Pictures> pictures = new ArrayList<>();
        if (animalPicture.getFirst() != null && animalPicture.getFirst().length() != 0) {
            pictures.add(new Pictures(animalPicture.getFirst(), animalPicture.getAnimalName(), animalPicture.getAnimalId()));
        }
        if (animalPicture.getSecond() != null && animalPicture.getSecond().length() != 0) {
            pictures.add(new Pictures(animalPicture.getSecond(), animalPicture.getAnimalName(), animalPicture.getAnimalId()));
        }
        if (animalPicture.getThird() != null && animalPicture.getThird().length() != 0) {
            pictures.add(new Pictures(animalPicture.getThird(), animalPicture.getAnimalName(), animalPicture.getAnimalId()));
        }
        return pictures;
    }
}
