'''
创建一个函数，接受一个参数n(n>=100)，
判断这个数是否为水仙花数
水仙花数：满足如果这个数为m位数，则每个位上的
数字的m次幂之和等于它本身
例如：1^3+5^3+3^3=153, 1^4+6^4+3^4+4^4=1634
〉 结果返回True或者False
创建一个函数，接受一个参数max(max
>= 1000)，调用上题编写的判断函数，求
100到max之间的水仙花数
'''


def digit_count(num):
    res = 0
    while num != 0:
        res += 1
        num = (num - num % 10) // 10
    return res


def is_narc(n0):
    n = n0
    d = digit_count(n)
    temp = 0
    for i in range(d):
        temp += (n % 10) ** d
        n = (n - n % 10) // 10
    if temp == n0:
        return True
    else:
        return False


def search_narc(n_max):
    for i in range(100, n_max + 1):
        if is_narc(i):
            print(i, end=' ')
    return


search_narc(9794)
