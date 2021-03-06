'''
编写一个推导式，生成包含100以内
所有勾股数(i,j,k)的列表
'''

'''
a和b分别是奇数和偶数（偶数和奇数），斜边c只能是奇数。
4． 勾股数具有以下特性：
斜边与偶数边之差是奇数,这个奇数只能是某奇数的平方数, 例1,9,25,49,……
斜边与奇数边之差是偶数,这个偶数只能是某偶数平方数的一半, 2,8,18,32,……
5． 由以上定义我们推导出勾股公式：
           a = p2 + pq
           b = q2/ 2 + pq
           c =p2+ q2/ 2 + pq
'''

g = [(i ** 2 + i * j, j ** 2 / 2 + i * j, i ** 2 + j ** 2 / 2 + i * j) for i in range(1, 101, 2) for j in
     range(2, 101, 2) if i ** 2 + j ** 2 / 2 + i * j <= 100 and i ** 2 + i * j <= 100 and j ** 2 / 2 + i * j <= 100]
for i in g:
    print(i)