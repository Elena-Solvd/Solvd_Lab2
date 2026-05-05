package com.solvd.booking2.dao;

import com.solvd.booking2.models.Photo;

import java.util.List;

public interface IPhotoDAO extends IBaseDAO<Photo> {

    List<Photo> findPhotosByPropertyId(Long propertyId);
}