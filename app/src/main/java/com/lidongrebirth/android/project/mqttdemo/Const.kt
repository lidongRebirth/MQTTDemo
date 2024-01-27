package com.lidongrebirth.android.project.mqttdemo


/**
 * @Author LD
 * @Time 2020/9/1 16:52
 * @Describe 全局各种常量汇总
 * @Modify
 */
interface Const {

    /**
     * MQTT地址配置
     */
    interface Url {
        companion object {

            //填写服务端地址，如果是本地服务端测试，请写具体IP而不要写127.0.0.1
            const val SERVER_URL = "tcp://192.168.14.57:1883"
        }
    }
    /**
     * 订阅信息
     */
    interface Subscribe{
        companion object{
            //订阅主题
            const val mTopic="topicName"
        }
    }






}