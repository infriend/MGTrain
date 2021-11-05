'''
创建一个函数，接受两个字符串作为参数，
返回两个字符串字符集合的并集
例如：接受的两个字符串为"abc"和"bcd"，返回
set([‘a’, ’b’, ’c’ , ’d’])
'''


def str_intersection(str1, str2):
    s1 = set(str1)
    s2 = set(str2)
    return s1 & s2


str01 = "daefgifd124~"
str02 = "orgidfjelod13~!"

print(str_intersection(str01, str02))
