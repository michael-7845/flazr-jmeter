# flazr-jmeter
jmeter flazr push and pull live stream request sampler

## 目的
本项目很简单: 为方便JMeter发送rtmp请求, 封装了Flazr的live发布和加压模式, 以达到使用sample模拟用户推流和拉流.
结合JMeter强大的http/https能力, 可以使LeanCloud REST API配合上rtmp的推拉流, 从而模仿更多真实的直播推送和收看行为.

## 使用方法
* git clone出项目;
* 导入Eclipse(也可参考ant脚本,自己编译)
* Export为Runnable Jar File
* 将导出的jar包放入%JMETER_HOME%/lib/ext目录
* 根据提示, 你可能也需要将运行需要的jar包放到%JMETER_HOME%/lib/ext目录
* 之后按照JMeter的Java Request Sampler用法进行.

## 说明
* 提供了参考的JMeter项目文件: ./jmeter/rtmprequest.jmx
* 为了进行推流, 你可能需要准备一些你想要推送的视频文件到%JMETER_HOME%/bin目录.
