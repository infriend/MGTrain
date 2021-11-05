'''
列表、元组基本操作
+, *, len(), [], in
列表、元组高级操作
mylist=[1,2,3,4,5]
切片：获得[2,3,4]，获得[3,4,5]，获得[3,2,1]，
获得[1,3,5]
mytpl=(1,2,3,4,5)同上操作
t='Mike and Tom'
split拆分、join合成为'Mike/and/Tom'
'''

mylist = [1, 2, 3, 4, 5]
mytpl = (1, 2, 3, 4, 5)

print([1] + [2])
print([1] * 3)
print(len([1, 2, 3, 4]))
print([1, 2, 3][1])
print(1 in [1, 2, 3])
print(mylist[1:-1])
print(mylist[2:])
print(mylist[-3::-1])
print(mylist[::2])
print(mytpl[1:-1])
print(mytpl[2:])
print(mytpl[-3::-1])
print(mytpl[::2])
t = 'Mike and Tom'
print(t.split())
print('/'.join(t.split()))

'''
集合基本操作
a=set([1,2,3,4,5])
b=set([2,4,6,8,10])
并、交、差、异或、子集
添加、删除、是否空集
字典基本操作
mydict={1:'Mon', 'line1':3332}
添加、删除、是否空字典
取字典所有的key／value
判断key是否存在
'''
a = set([1,2,3,4,5])
b = set([2,4,6,8,10])
print(a | b)
print(a & b)
print(a - b)
print(a ^ b)
print(a < b)
a.add(6)
print(a)
a.discard(3)
print(a)
for i in a:
    print(i, end=' ')
print(' ')
print(not a)

mydict = {1: 'Mon', 'line1': 3332}
mydict.update({3: "111"})
print(mydict)
mydict.pop(3)
print(mydict)
print(not mydict)
print(mydict.keys())
print(mydict.values())
print("line1" in mydict.keys())
