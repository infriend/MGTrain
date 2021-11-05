'''
〉 给定n，计算1+2!+3!+…+n!的值
'''

import math

n = input('n=')
n = int(n)
res = 0
for i in range(1, n+1):
    res += math.factorial(i)
print(res)
