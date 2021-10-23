## 简介  

u2-lock提供了基于redis,zookeeper的分布式锁，防重提交，在不同的只需要引入相应的starter，进行简单的配置便可使用，满足了不同
场景下分布式系统出现数据不一致问题。

## 组件列表

#### 1.基于Redisson分布式锁：   
基于Redisson实现的分布式锁，提供了(single)**单机模式**，(master-slave)**主从模式**，(sentinel)**哨兵模式**，(cluster)**集群模式**，云托管模式，支持**可重入**（REENTRANT_LOCK），**联锁**（MultiLock），**读写锁**（ReadWriteLock），**公平锁**（Fair Lock），**红锁**（RedLock）  
#### 2.基于Zookeeper Curator分布式锁:  
基于Zookeeper Curator的分布式锁提供了可重入锁，联锁，读写锁。  
#### 3.基于Zookeeper分布式锁原理实现：  
讲解了zookeeper实现分布式锁的原理，可用于学习。  
#### 4.基于redis分布式锁原理实现： 
使用redis实现的分布式锁
#### 5.基于Redis的防重提交
基于Redis实现的防重提交，可进行灵活的参数配置，





