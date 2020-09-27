---
#CountDownLatch 闭锁  
>一个线程等待其他线程执行完成之后再执行 加强版的join
>await 主任务等待 可以不止一个地方等待
>countDown 执行计数器-1 初始化闭锁的计数器也可以不等于线程数 只要计数器被扣减到0 await的线程都可以工作
#CyclicBarrier 循环栅栏
>一组线程到达某个屏障 先到达的被阻塞 一直到最后一个线程到达的时候 全部开放栅栏 解除阻塞
---
#CountDownLatch 和 CyclicBarrier的区别
CountDownLatch 是由外部线程来进行控制放行
CyclicBarrier 是线程自己来处理 满足条件全部放行 协调一组线程到达同步点
---
Semaphore 信号标
控制同时访问某个特定资源的线程数量
构造函数 permits 限制有多少个线程来访问
acquire获取
release释放
tryAcquire尝试获取
availablePermits可用的数量
hasQueuedThreads是否有线程等待获取信号量
getQueueLength获取等待线程的数量
---
Exchanger 两个线程间的数据交换  
到exchange方法处等待 两个线程都到达之后开始交换
---
Callable, Future, FutureTask
![FutureTask][1]
#Future
get方法是阻塞的 返回callable的计算结果  
isDone 正常结束, 异常结束 或者是取消都会返回True   
isCancelled 任务完成前被取消返回True  
cancel(mayInterruptIfRunning):
    
    1.任务还没开始, 返回false 任务也不会执行
    2.任务已经开始 Boolean是true的时候尝试中断 中断成功返回true,如果是false不回去中断任务 返回false
    3.任务已经结束 返回false

[1]:../images/FutureTask.jpg
