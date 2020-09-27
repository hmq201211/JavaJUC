***
并行 同一时刻处理事情的能力  
并发 一段时间内系统处理问题的能力  
---
Thread stop suspend resume  已经被废弃 线程不会释放资源 死锁问题  
interrupt() isInterrupted() static interrupted()  
>interrupt() Java线程之间是协作式的 并不是抢占式的 把一个中断标志位设为True  
>isInterrupted() 判断当前线程是否是处于中断状态  
>static interrupted() 判断当前线程是否处于中断状态 并把中断标志位置为false  
---
     注意 如果抛出interruptedexception 那么中断标志位会被重置为false 也就是说中断无效  
     需要在catch里面调用线程实例的interrupt方法
---
线程流程图
![线程流程图][1]
***
sleep和yield(时间片到期) 举例:  
微波炉热饭sleep是热到一般睡了一觉 cpu不会在sleep期间调度到线程
yield是热到一半让出执行权 参与到下次线程的争抢  
---
线程优先级 默认为5 范围为1-10 实例的setPriority方法来进行设置
---
守护线程  
共死 依赖的线程中止以后 被setDaemon(true)的线程也会中止  
此时如果线程内部使用了try finally 语句块 finally里面不一定会执行  
---
    synchronized关键字
    类锁 🔒的是Class对象
    对象锁 🔒的是实例  
---
    volatile关键字 
    禁止指令重排序 同时保证可见性  
    常用场景: 一个线程写 多个线程读  
---
    ThreadLocal 保证每个线程使用自己的那份拷贝 
    实际使用的是一个map key为当前线程对象 值为资源的副本拷贝 
    可以override initialValue方法来设置初始值
    使用get set来获取和更改值
---
#线程间协作
    1.轮询
    难以保证及时性  
    资源开销很大
    2.等待和通知
    wait() notify() notifyAll() 对象上的方法
    notify 随机唤醒一个等待的线程
    notifyAll 唤醒所有等待的线程
    等待方获取对象锁 在循环里面判断条件是否满足 不满足则调用wait() 条件满足则执行业务
    通知方获取对象锁 改变条件 通知所有等待的线程
    3.等待超时模式
---
join 虽然处于运行状态  
但是交出执行权 等join的线程执行完之后才回执行

yield 不释放锁  
sleep 不释放锁  
wait 调用之前必须持有锁 运行之后释放锁 当wait结束以后重新持有锁  
notify notifyAll 必须是在所在的代码块全部结束以后才会释放锁
---
ForkJoin 一种多线程解决方案  分治法  
![ForkJoin][2]
RecursiveAction无返回值, 异步用法    
RecursiveTask有返回值   
---
##工作密取
work stealing  
当Worker完成自己的工作后 从其他的Worker的工作队列尾端获取工作




[1]:../images/线程流程图.jpg
[2]:../images/ForkJoin.png