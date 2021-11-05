'''
给定字符串s和数字n，打印把字符串s向右
移动n位的新字符串
'''

s = input("s:")
n = int(input("n:"))
step = n % len(s)
print(s[-step:], end='')
print(s[:-step])

