通过并发工具类CountDwonLatch完成线程间的协作，主要用到
1.countDown()方法和await()方法，
其中countDown()是使锁存器的计数减1
await()当锁存器的计数为0时，会激活所有非中断的线程