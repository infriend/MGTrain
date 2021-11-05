'''
蒙特卡罗方法求圆周率
〉 蒙特卡罗方法原理
通过大量随机样本，去了解一个系统，进而得到所
要计算的值
〉 用蒙特卡罗方法计算圆周率π
正方形内部有一个相切的圆
在这个正方形内部，随机产生10000个点，计算它们
与中心点的距离，从而判断是否落在圆的内部
如果这些点均匀分布，那么圆内的点应该占到所有
点的 π/4
〉 用matplotlib可视化
'''

import matplotlib.pyplot as plt
import numpy as np
import random

x = np.linspace(-3, 3, 1000)
y1 = np.sqrt(9 - x ** 2)
y2 = -y1
plt.plot(x, y1, color='Blue')
plt.plot(x, y2, color='Blue')
plt.plot(np.array([3]*1000), x, color='Red')
plt.plot(np.array([-3]*1000), x, color='Red')
plt.plot(x, np.array([3]*1000), color='Red')
plt.plot(x, np.array([-3]*1000), color='Red')

count = 0
x_set = []
y_set = []
rx_set = []
ry_set = []
for i in range(10000):
    rx = random.uniform(-3, 3)
    ry = random.uniform(-3, 3)
    if rx**2 + ry**2 <= 9:
        count += 1
        rx_set.append(rx)
        ry_set.append(ry)
    else:
        x_set.append(rx)
        y_set.append(ry)

plt.scatter(np.array(rx_set), np.array(ry_set), s=1, color='green')
plt.scatter(np.array(x_set), np.array(y_set), s=1, color='black')

print('Pi = %f' % (count/2500))

plt.show()


