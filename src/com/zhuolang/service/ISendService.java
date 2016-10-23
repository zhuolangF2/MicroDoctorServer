package com.zhuolang.service;

import com.zhuolang.dto.SendDto;
import com.zhuolang.model.Send;

import java.util.List;

/**
 * Created by hzg on 2016/10/13.
 */
public interface ISendService {
    void addSend(Send send);
    void updateSend(Send send);
    List<SendDto> findSendDto();
    List<Send> findSend();
    SendDto findSendDtoById(int id);
    List<Send> findSendById(int id);
    void deleteSend(List<Send> findSend);
//    int deleteSendById(int id);

}
