#更多并发容器

##跳表相关的数据结构 concurrentSkipListMap 和concurrentSkipListSet

----
![跳表][1]  
每插入一个元素 有随机概率在上层建立索引 索引之间跳过元素来实现快速查找区间  

##concurrentLinkedQueue 并发安全的LinkedList
##写事复制容器
CopyOnWriteArrayList  
CopyOnWriteArraySet  

    在写入新元素的时候, 新建一份原来数据的拷贝, 写入新元素, 把旧的数据的引用指向新的数据  
    使用场景是读多写少
##blockingQueue接口
![阻塞队列][2]  
put 和 take是阻塞式的   
![常用的阻塞队列][3]  
有界的含义是不会因为大小而阻塞  
PBQ 底层实现是堆
DelayedQueue 超时之后才能take 见demo  
SynchronousQueue 存一个元素必须等取出之后才能存  (不直接存储 来一个必须消费一个)
LinkedTransferQueue 当有消费者等待消费的时候 生产者直接把元素给消费者而不经过队列  
LinkedBlockingDeque 双向阻塞队列 工作密取模式


[1]:../images/skipList.jpg
[2]:../images/blockingQueue.jpg
[3]:../images/bq.jpg