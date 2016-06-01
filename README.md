# flazr-jmeter
jmeter flazr push and pull live stream request sampler

## 目的
本小项目主要为了方便JMeter发送rtmp请求, 使用sample模拟用户推流和拉流之用.
结果JMeter强大的http/https能力, 可以将LeanCloud REST API结合上rtmp的推拉流, 从而模仿更多真实的直播推送和收看行为.

## 使用方法
* git clone出项目;
* 导入Eclipse(也可参考ant脚本,自己编译)
* Export为Runnable Jar File
* 将导出的jar包放入%JMETER_HOME%/lib/ext目录
* 根据提示, 你可能也需要将运行需要的jar包放到%JMETER_HOME%/lib/ext目录
* 之后按照JMeter的Java Request Sampler用法进行.

## 说明
提供了参考的JMeter项目文件: ./jmeter/rtmprequest.jmx
