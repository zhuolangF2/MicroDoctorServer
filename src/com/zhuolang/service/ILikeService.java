package com.zhuolang.service;

import com.zhuolang.model.Like;

import java.util.List;

/**
 * Created by hzg on 2016/10/14.
 */
public interface ILikeService {
    void addLike(Like like);
    void updateLike(Like like);
    List<Like> findBySLId(int sendId,int likesId);
    List<Like> findLikeById(int id);
    List<Like> findAllLike();
    void deleteLike(List<Like> findLike);
}
