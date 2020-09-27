#concurrentHashMap17  
![1]  
##HashEntry结构中 要不然就是被final修饰 要不然就是volatile关键字修饰 保证了可见性
---
1.7死循环分析: 
    
    2个线程并发扩容 1个线程完成了并发操作 1个线程被挂起了 造成了 1个线程改变了next的引用, 另一个线程新建newTable的时候造成了元素next元素成环
---
putIfAbsent 在当前元素有的时候不存入 无的时候存入  
get: 1定位到segment 2 定位到table 3 从链表中查找元素(不加锁)
![2]
put: 获取锁 之后再进行操作 可能会rehash  
remove: 获取锁之后再进行操作  
size和containsValues不建议使用 因为可能导致所有的segments都上锁 影响并发使用  
---
#concurrentHashMap18

使用了数组 链表 和红黑树结构  使用了CAS操作和synchronized关键字  
当链表长度大于8的时候 链表转红黑树 当树的大小小于6的时候树转化为链表  
  
[1]:../images/17chm.jpg
[2]:../images/17chmput拿锁.jpg