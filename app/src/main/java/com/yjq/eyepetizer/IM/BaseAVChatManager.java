package com.yjq.eyepetizer.IM;

import java.util.List;

/**
 * 文件： BaseAVChatManager
 * 描述：
 * 作者： YangJunQuan   2018-9-12.
 */


enum AVChatType {
    AUDIO, VIDEO
}


enum ControlCommand {

}

/**
 * 网络通话回调接口
 */
interface AVChatCallback<T> {

    void onSuccess(T t);

    void onFailed(int code);

    void onException(Throwable exception);

}


public abstract class BaseAVChatManager {


    //发起单人会话，通知到对方
    abstract void call2(String account, AVChatType avChatType, AVChatCallback avChatCallback);


    //发起多人会话
    abstract void call2(List<String> accountList, AVChatType avChatType, AVChatCallback avChatCallback);


    //接听会话
    abstract void accept2(long chatId, AVChatCallback avChatCallback);


    //挂断会话
    abstract void hangup2(long chatId, AVChatCallback avChatCallback);


    //通话过程 控制命令的发送
    abstract void sendControlCommand(long chatId, ControlCommand command, AVChatCallback avChatCallback);
}


