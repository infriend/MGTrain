'''
编写一个生成器函数，能够生成斐波
那契数列
def fib():
….
for fn in fib():
print (fn)
if fn>1000:
break
'''


def fib():
    old = 1
    yield old
    new = 1
    yield new
    while 1:
        temp = new
        new = new + old
        old = temp
        yield new


for fn in fib():
    print(fn)
    if fn > 1000:
        break
