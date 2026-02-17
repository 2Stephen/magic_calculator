[TOC]

# 2026央视春晚计算器魔术同款计算器APP

## 1. 原理

+ 设target为当前时间，如2171015（2月17日10点15分）
+ 设用户A输入四位随机数为numA，如1835
+ 设用户B输入五位随机数为numB，如37409
+ 此时背过去需要输入的数字就是target - numA - numB
+ 秘密就在背过去输入这一步，无论用户点什么，最终输入都是target - numA - numB
+ 最后求和，自然而然就是target了

## 2. 安装步骤

+ 有Android Studio等安卓开发环境的同学可以直接自己打包。
+ 无开发环境同学可以点击下载Archive[魔法计算器下载](https://github.com/2Stephen/magic_calculator/releases/download/v1.0.0/magic_calculator.apk)，但是安装时候需要自己在手机上勾选已知风险，因为该程序只是Demo未备案。

## 3. 其他

+ 这个源码原本就是正常的计算器，自己学习Android开发时候做的，看见春晚节目，于是魔改了一下
+ 如果想将这个源码替换为正常计算器，只需要在`app/src/main/java/com/example/calculator/MainActivity.java`内的`onCreate`注释取消掉，并将后面的`onCreate`方法注释或删除。