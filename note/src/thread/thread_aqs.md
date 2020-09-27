---
LockSupport  

* 阻塞一个线程
* 唤醒一个线程 
* 构造同步组件的工具

park 停车  
unpack 唤醒  
---
AQS 抽象队列同步器 
    
    模版方法设计模式 见demo
---
其中的模版方法  
获取
* 独占式  
    1. acquire
    2. acquireInterruptibly
    3. tryAcquireNanos
* 共享式
    1. acquireShared
    2. acquireSharedInterruptibly
    3. tryAcquireSharedNanos  

释放
* 独占式
    1. release
* 共享式
    2. releaseShared

要覆盖的抽象方法或者抛出异常的方法
* 独占式
    1. tryAcquire 获取
    2. tryRelease 释放
* 共享式
    1. tryAcquireShared
    2. tryReleaseShared
    3. isHeldExclusively 表明这个同步器是否处于独占模式  
    
同步状态 state volatile 线程之间可见性  
getState 不需要 同步 （volatile关键字）  
setState cas来保证原子性  
![AQS实现锁][1]

|nodeField|解释|
|:---|:---|
|canceled|线程等待超时或者被中断 需要从队列中移除|
|signal|后续的节点等待状态，当前节点通知后续节点去运行|
|condition|当前队列处于等待队列|
|propagate|共享表示状态要往后传播|
|0|不是以上的状态|
|waitStatus|以上5状态|
|nextWaiter|等待队列 等待唤醒机制|
---
独占式同步状态的获取和释放
![独占式][2]
---
Condition 分析  
![等待队列][3]  
![操作][4]  
 

[1]:../images/利用AQS实现锁.png
[2]:../images/exclusivelyAcquireAndRelease.png
[3]:../images/condition_1.png
[4]:../images/condition_2.png