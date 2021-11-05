'''
给定一个英文数字字符串，打印相应阿拉伯
数字字符串
'''
s = input('str:')
d0 = {
    'one': 1, 'two': 2, 'three': 3, 'four': 4, 'five': 5,
    'six': 6, 'seven': 7, 'eight': 8, 'nine': 9, 'zero': 0
}
list = s.split('-')
for i in list:
    print(d0[i], end='')
