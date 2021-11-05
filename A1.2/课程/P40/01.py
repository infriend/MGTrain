'''
看看阶乘累加（n=1~100）各需要多长时间？
'''
import math
import time

st_time = time.time()
res = 0
for i in range(1, 101):
    res += math.factorial(i)
fin_time = time.time()
print("Time cost: %f ms" % ((fin_time - st_time)*1000.0))