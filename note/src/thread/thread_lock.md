Lock 和 Synchronized区别  
Synchronized 代码简洁  
Lock 获取锁可以被中断 可以超时获取锁 可以尝试获取锁  
```java
   public class LockDemo{
        private Lock lock = new ReentrantLock();
        private int count;
        public void increment(){
            lock.lock();
            try{
                count++;
            }finally{
                lock.unlock();
                }
            }
   }
```
---
ReentrantLock 可重入锁  
持有锁可以进入多次  
公平锁和非公平锁 非公平锁运行效率更高  
如果在时间上先对锁获取的请求一定先被满足 那么就是公平锁  
反之就是非公平锁
非公平锁  线程可以插队获取锁  
公平锁 线程没有获取到锁会被挂起(挂起 线程被从cpu寄存器或者内存中移除) 然后恢复之后获取锁
---
ReadWriteLock 读写锁接口

ReentrantLock和Synchronized都是排他锁(同一时刻只允许一个线程访问)  
ReentrantReadWriteLock 支持同时多个读线程 或者一个写线程(其他线程阻塞)  

    读多写少的的时候读写锁提升明显
---
Condition 一个锁可以new出不同的condition来  
condition await signal 对应 Object wait notify