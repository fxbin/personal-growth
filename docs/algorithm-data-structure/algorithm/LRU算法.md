## Least Recently Used算法

* Get操作用于获取相应的数据；
* Remove操作将一个数据从缓存中删除；
* Set操作用于将相应的数据存放在缓存中，如果缓存空间并未满，则将数据直接存入缓存；如果缓存空间已满，则调用Remove操作将最旧的数据从缓存中删除，然后再将新数据存放在缓存中。
