#线程安全
1. 线程封闭 
    1. ad-hoc 使用者来维护线程封闭 对象封闭在一个线程里面
    2. 栈封闭 局部变量 方法中的变量
    3. ThreadLocal 每个线程里面都有一份拷贝
2. 无状态的类  
就是说类没有成语变量
3. 不可变的类
    1. 使用final关键字修饰 如果是引用变量需要加锁
    2. 不提供改变内容的方法
#安全发布
引用类型传递到类的外部  
Array 可以使用Collections.synchronizedArray来包装成线程安全  
如果不是final的引用 可以extends来包装成线程安全的  
如果是final的引用 可以delegate成线程安全的  

    servlet 不是线程安全的 因为是单线程处理请求 
    如果是在controller里面统计访问次数， 可以使用AtomicInteger来处理
    spring在初始化bean的时候 使用的hashmap 并且使用了synchronized关键字 
    这是因为只在初始化的时候使用了一次

#deadLock 见demo
1. 多个线程M（》2）同时争夺N（》2）个资源 M>=N
2. 争夺的顺序有问题

动态死锁 对象引用导致了synchronized取锁顺序出现问题 解决方法  
1. hash 加第三把锁来强制设定取锁顺序
2. 使用显示锁 tryLock来尝试取锁 取不到第二把的时候第一把也会释放 注意错开取锁时间 否则可能会导致活锁  
#活锁 
![1]
#减少锁的竞争的措施
1. 缩小锁的范围( hashmap取string再判断正则 可以只在hashmap上加锁)
2. 减少锁的粒度 一个类有两个hashmap 可以分别加锁
3. 锁分段 concurrentHashMap
4. 替代独占锁 使用读写锁 或者CAS操作

[1]:../images/活锁.png