# 剑网三Q群机器人

## 介绍
本项目是根据[simbot](https://github.com/ForteScarlet/simpler-robot) 制作的剑网三Q群机器人
部分接口数据由[Jx3Api](https://jx3api.com/#/?id=explain )提供支持
目前项目已经停止维护，有兴趣的朋友可以作为参考

## 参考
> https://gitee.com/yqingfeng/jx3robot
> 
> https://gitee.com/LSE_QX/lse-qbot

## 配置机器人
> yml文件中添加jx3sp的账号和密码，使用jx3sp需要登录账号
> 
> resources/simbot-bots/下的文件填入qq账号
> 
> 使用init下的脚本初始化数据库 | h2数据库的网页控制台 localhost:8080/h2-console
## 目前已有功能
> 查询日常、金价、花价、沙盘、宏、奇穴、开服、随机骚话、官方公告、随机瑟图
> 
> 消息防撤回
> 
> 剑三盆栽蹲号查询
> 
## 使用命令
> 日常 ｛服务器名称｝| 日常
>
> 金价 ｛服务器名称｝
>
> 花价 {花名}（在群未绑定服务器的情况下默认为双梦）|查询其他服务器｛server｝ 花价 ｛花名｝
> 
> 以上例举几个例子，其他的功能类似，详情见代码：package com.lin.jx3_bot.listener.group;#GroupListener
## TODO
- [ ] 开团工具
- [ ] 后台管理
- [ ] websocket的使用
