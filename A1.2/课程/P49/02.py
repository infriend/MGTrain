'''
〉 创建一个mylist类，继承自内置数据
类型list（列表）
增加一个方法“累乘”product
def product(self):
返回所有数据项的乘积
'''

class mylist(list):
    def product(self):
        res = 1
        for i in self:
            res *= i
        return res

a = mylist(range(2,6))
print(a.product())
