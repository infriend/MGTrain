'''
数值基本运算：33和7
+, -, *, /, //, %, **
hex(), oct(), bin()
'''

a = 33
b = 7
print(a + b)
print(a - b)
print(a * b)
print(a / b)
print(a // b)
print(a % b)
print(a**b)
print(hex(b))
print(oct(b))
print(bin(b))

'''
类型转换
1，0，
'abc', None, 1.2, False, ''
str(), bool(), int(), float()
is None, ==, !=
'''
print(str(1))
print(str(None))
print(str(1.2))
print(str(False))
print(bool(1))
print(bool(0))
print(bool('abc'))
print(bool(None))
print(bool(1.2))
print(int(1.2))
print(float(1))
print(1 is None)
print(1 == 0)
print(1 == False)
print(1.2 != 0)

'''
字符串基本操作
+, *, len(), [], in
ord(), chr()
含有中文的字符串
'''

str1 = "asaDD12!"
str2 = "一二三aa3D)"
print(str1+str2)
print(str1*2)
print(len(str1))
print(str1[-2])
print('s' in str1)
print(ord('A'))
print(chr(65))
print(ord("一"))

'''
s='abcdefg12345'
切片：获得defg12，获得fg12345，获得54321，
获得aceg2
t='Mike and Tom'
split拆分
upper/lower/swapcase修改大小写
ljust/center/rjust排版30位宽度左中右对齐
replace将Mike替换为Jerry
'''
s='abcdefg12345'
print(s[3:-3])
print(s[5:])
print(s[-1:-6:-1])
print(s[0:-4:1])
t='Mike and Tom'
print(t.split())
print(t.upper())
print(t.lower())
print(t.swapcase())
print(t.ljust(30))
print(t.center(30))
print(t.rjust(30))
print(t.replace('Mike', 'Jerry'))
