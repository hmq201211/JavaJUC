#线程池 

    1.降低资源消耗 new需要资源 run需要资源
    2.提高响应速度 创建线程的时间 运行的时间 销毁的时间(资源复用)
    3.提高线程可管理性 
![线程池执行流程][1] 
 
ThreadPoolExecutor构造函数参数  
corePoolSize 核心执行线程数  
maximumPoolSize 最大执行线程数  
keepAliveTime  线程空闲下来的存活时间  
unit  单位  
workQueue 任务队列  

1 创建执行线程知道到达corePoolSize  
2 如果还有新的任务 加入阻塞队列  
3 如果阻塞队列满了 新建执行线程直到maximumPoolSize  
4 如果还有新的任务 则调用执行处理策略  
![拒绝策略][2]  
1 丢弃最老的任务  
2 缺省策略(抛出异常)  
3 谁调用execute谁来执行  
4 直接丢弃  

![AOP][3]
---

    
1. 执行前   
2. 执行后  
3. 线程池结束

---
![4]  
有返回值的时候使用submit方式来提交任务  
无返回值的时候使用execute来提交任务  
---
线程池关闭:  
1. shutdown 中断没有在执行任务的线程  
2. shutdownNow 中断所有的线程  
---
计算密集型 线程数设置为n+1(交换页的存在) 

    cpu把一条线程切换到长期不用的状态 那么这个线程的内存部分可能会被
    存储到硬盘的一部分上(交换页), 等线程激活的时候 从硬盘上切换回内存 
IO密集型可以简单得设置为2n  
或者参考计算公式  
![8]
---

与定义的线程池
1. FixedThreadPool 固定线程数量的线程池
![5]
空闲线程会被立刻回收 并且虽然queue有界 但是是int max 
2. SingleThreadExecutor 
保证任务顺序执行
3. CachedThreadPool
![6]
来一个任务创建一个线程 
4. WorkingStealingPool (工作密取)
![7]
5. ScheduledThreadPoolExecutor 定时任务 多个
6. SingleThreadScheduledExecutor 定时任务 单个 串行

    
    schedule.schedule (callable/runnable)只执行一次
    schedule.scheduleWithFixedDelay end到start之间固定时长
    schedule.scheduleAtFixedRate start到start 如果超时 等前一个任务结束后立即执行
    如果没有超时 则等待时间间隔结束再执行
    如果抛出异常 则下次任务不会执行 * 坑-不出现异常提示
    如果处理了异常 则会继续执行
---
CompletionService
        
    包装了一个线程池 线程池中的任务有返回值的情况下先结束的结果会线入队列
    省去了常规情况下如果前一个任务没有执行完时被阻塞的时间


[1]:../images/线程池执行流程.jpg
[2]:../images/线程池拒绝策略.jpg
[3]:../images/线程池AOP.jpg
[4]:../images/线程池有无返回值执行方法.jpg
[5]:../images/FixedThreadPool.png
[6]:../images/CashedThreadPool.png
[7]:../images/WorkingStealingPool.png
[8]:../images/IO密集型线程数计算公式.png