---
原子操作 Atomic 不可分割的  
synchronized 是阻塞的锁机制
* 问题
    1. 被阻塞的线程优先级很高
    2. 资源占用问题 拿到锁的线程一直不释放锁
    3. 大量的竞争导致cpu被消耗 同时带来死锁或者其他线程安全问题  
---
CAS原理  
如果地址上的值V和期望的值A相等 就给地址赋新值B
如果不是 就不做任何操作 在循环里自旋  
---
ABA问题  
解决方案：  
版本号
---
开销问题
CAS操作长期不成功 一直自旋 浪费cpu资源  
---
只能保证一个共享变量的原子操作  
原因是内存地址上的操作 
解决方案  
atomicReference把多个变量包装盛一个变量  
---